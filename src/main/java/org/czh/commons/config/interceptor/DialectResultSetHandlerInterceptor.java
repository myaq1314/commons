package org.czh.commons.config.interceptor;

import org.apache.ibatis.executor.resultset.DefaultResultSetHandler;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.RowBounds;
import org.czh.commons_core.utils.FieldUtil;

import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author : czh
 * description : 分页结果处理器
 * date : 2021-06-18
 * email 916419307@qq.com
 */
@SuppressWarnings("unused")
@Intercepts({@Signature(type = ResultSetHandler.class, method = "handleResultSets", args = {Statement.class})})
public class DialectResultSetHandlerInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        DefaultResultSetHandler resultSet = (DefaultResultSetHandler) invocation.getTarget();
        Field field = FieldUtil.getField(DefaultResultSetHandler.class, "rowBounds");
        RowBounds rowBounds = FieldUtil.readField(resultSet, field);
        assert rowBounds != null;
        if (rowBounds.getLimit() > 0 && rowBounds.getLimit() < RowBounds.NO_ROW_LIMIT) {
            FieldUtil.writeField(resultSet, field, new RowBounds());
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}