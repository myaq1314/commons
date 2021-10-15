package org.czh.commons.config.interceptor;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.PreparedStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.czh.commons.entity.Page;
import org.czh.commons.entity.PageHelper;
import org.czh.commons_core.asserts.EmptyAssert;
import org.czh.commons_core.utils.FieldUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

/**
 * @author : czh
 * description : 分页处理器
 * date : 2021-06-18
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class DialectStatementHandlerInterceptor implements Interceptor {

    public Object intercept(Invocation invocation) throws Throwable {
        if (PageHelper.pageEnable()) {
            Page<?> page = PageHelper.getZeroPage();
            EmptyAssert.isNotNull(page);

            RoutingStatementHandler statement = (RoutingStatementHandler) invocation.getTarget();
            PreparedStatementHandler delegate = FieldUtil.readField(statement, "delegate");
            MappedStatement mappedStatement = FieldUtil.readField(delegate, "mappedStatement");
            Connection conn = (Connection) invocation.getArgs()[0];
            BoundSql boundSql = statement.getBoundSql();

            // 拼接 查询 数量 SQL
            String sql = boundSql.getSql(); // 获取原始SQL
            String sqlCount = String.format("SELECT COUNT(1) AS rowCount FROM ( %s ) AS originalSqlTempTable", sql);

            // 分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
            Object parameterObject = boundSql.getParameterObject();
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(),
                                            sqlCount,
                                            boundSql.getParameterMappings(),
                                            parameterObject
            );
            MetaObject metaParameters = FieldUtil.readField(boundSql, "metaParameters");
            FieldUtil.writeField(countBS, "metaParameters", metaParameters);

            // 通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象
            ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, countBS);
            PreparedStatement preparedStatement = conn.prepareStatement(sqlCount);
            parameterHandler.setParameters(preparedStatement);

            // 设置查询参数
            // setParameters(preparedStatement, mappedStatement, countBS, parameterObject);

            // 执行记录总数查询语句
            ResultSet resultSet = preparedStatement.executeQuery();
            // 获得记录总数
            int totalResult = 0;
            if (resultSet.next()) {
                totalResult = resultSet.getInt(1);
            }
            resultSet.close();
            preparedStatement.close();

            // 将记录总数设置到翻页参数中
            page.setTotalResult(totalResult);
            sql = String.format("%s limit %d , %d", sql, page.getCurrentResult(), page.getPageSize());
            FieldUtil.writeField(boundSql, "sql", sql);

            //执行一次sql后，关闭分页拦截
            PageHelper.pageDisabled();
        }
        return invocation.proceed();
    }

    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {
    }
}