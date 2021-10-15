package org.czh.commons.utils.sql;

import org.czh.commons.entity.eo.BaseCommonEO;
import org.czh.commons.utils.sql.annotations.Column;
import org.czh.commons.utils.sql.base.TableUtil;
import org.czh.commons.utils.sql.eo.SqlParamEO;
import org.czh.commons_core.asserts.EmptyAssert;
import org.czh.commons_core.utils.FieldUtil;
import org.czh.commons_core.validate.EmptyValidate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : czh
 * description : 拼装 插入 SQL 语句
 * date : 2021/9/29
 * email 916419307@qq.com
 */
@SuppressWarnings({"unused", "DuplicatedCode"})
public final class InsertSplitUtil {

    /*
      -----------------------------拼装 完整 Insert SQL（数据来源实体）-------------------------------
     */

    /**
     * 单条实体记录插入
     */
    public static <EO extends BaseCommonEO> SqlParamEO insertEOSql(EO entity) {
        EmptyAssert.isNotNull(entity);

        // 查找所有标记 Column 注解 的属性
        List<Field> fieldList = FieldUtil.queryFieldList(entity.getClass(),
                                                         field -> field.getAnnotation(Column.class) != null
        );
        EmptyAssert.isNotEmpty(fieldList);

        // 拼装 要插入数据的 字段（属性值不为空字段）
        // insert into `tb_example_mysql_info` ( %s ) values (?, ?)
        // %s 号占位符处填入：`name`, `sex`
        StringBuilder columnBuilder = new StringBuilder();
        // 拼装 参数占位符
        // insert into `tb_example_mysql_info` (`name`, `sex`) values ( %s )
        // %s 号占位符处填入：?, ?
        StringBuilder symbolBuilder = new StringBuilder();
        // 汇总所有属性值不为空的字段
        List<Object> params = new ArrayList<>(fieldList.size());
        for (Field field : fieldList) {
            Object fieldValue = FieldUtil.readField(entity, field);
            if (EmptyValidate.isNotNull(fieldValue)) {
                columnBuilder.append("`").append(field.getAnnotation(Column.class).name()).append("`").append(",");
                symbolBuilder.append("?").append(",");
                params.add(fieldValue);
            }
        }
        EmptyAssert.isNotEmpty(params);

        // 完整SQL：insert into `tb_example_mysql_info` (`name`, `sex`) values (?, ?)
        String sql = String.format(" INSERT INTO %s (%s) VALUES (%s) ", // INSERT INTO %s (%s) VALUES (%s)
                                   TableUtil.split(entity), // `tb_example_mysql_info`
                                   columnBuilder.substring(0, columnBuilder.length() - 1),  // `name`, `sex`
                                   symbolBuilder.substring(0, symbolBuilder.length() - 1)  // ?, ?
        );
        return new SqlParamEO(sql, params);
    }

    /**
     * 单条实体记录插入，如果主键或唯一键存在，那么更新
     */
    public static <EO extends BaseCommonEO> SqlParamEO insertExistUpdateEOSql(EO entity) {
        EmptyAssert.isNotNull(entity);

        // 查找所有标记 Column 注解 的属性
        List<Field> fieldList = FieldUtil.queryFieldList(entity.getClass(),
                                                         field -> field.getAnnotation(Column.class) != null
        );
        EmptyAssert.isNotEmpty(fieldList);

        // 拼装 要插入数据的 字段（属性值不为空字段）
        // insert into `tb_example_mysql_info` ( %s ) values (?, ?) on duplicate key update `name` = values(`name`), `sex` = values(`sex`)
        // %s 号占位符处填入：`name`, `sex`
        StringBuilder columnBuilder = new StringBuilder();
        // 拼装 参数占位符
        // insert into `tb_example_mysql_info` (`name`, `sex`) values ( %s ) on duplicate key update `name` = values(`name`), `sex` = values(`sex`)
        // %s 号占位符处填入：?, ?
        StringBuilder symbolBuilder = new StringBuilder();
        // 拼装 要更新数据的 字段（属性值不为空字段）
        // insert into `tb_example_mysql_info` (`name`, `sex`) values (?, ?) on duplicate key update %s
        // %s 号占位符处填入：`name` = values(`name`), `sex` = values(`sex`)
        StringBuilder updateBuilder = new StringBuilder();
        // 汇总所有属性值不为空的字段
        List<Object> params = new ArrayList<>(fieldList.size());
        for (Field field : fieldList) {
            Object fieldValue = FieldUtil.readField(entity, field);
            if (EmptyValidate.isNotNull(fieldValue)) {
                String columnName = field.getAnnotation(Column.class).name();
                columnBuilder.append("`").append(columnName).append("`").append(",");
                symbolBuilder.append("?").append(",");
                updateBuilder.append("`")
                             .append(columnName)
                             .append("`")
                             .append(" = VALUES(")
                             .append(columnName)
                             .append("),");
                params.add(fieldValue);
            }
        }
        EmptyAssert.isNotEmpty(params);

        // 完整SQL：insert into `tb_example_mysql_info` (`name`, `sex`) values (?, ?) on duplicate key update `name` = values(`name`), `sex` = values(`sex`)
        String sql = String.format(" INSERT INTO %s (%s) VALUES (%s) ON DUPLICATE KEY UPDATE %s ",
                                   // INSERT INTO %s (%s) VALUES (%s) ON DUPLICATE KEY UPDATE %s
                                   TableUtil.split(entity),
                                   // `tb_example_mysql_info`
                                   columnBuilder.substring(0, columnBuilder.length() - 1),
                                   // `name`, `sex`
                                   symbolBuilder.substring(0, symbolBuilder.length() - 1),
                                   // ?, ?
                                   updateBuilder.substring(0, updateBuilder.length() - 1)
                                   // `name` = values(`name`), `sex` = values(`sex`)
        );
        return new SqlParamEO(sql, params);
    }

    /**
     * 多条实体记录插入
     */
    public static <EO extends BaseCommonEO> SqlParamEO batchInsertEOSql(final EO conditionEO,
                                                                        final List<EO> entityEOList) {
        EmptyAssert.isNotNull(conditionEO);
        EmptyAssert.isNotEmpty(entityEOList);

        // 查找所有标记 Column 注解 的属性
        List<Field> fieldList = FieldUtil.queryFieldList(conditionEO.getClass(),
                                                         field -> field.getAnnotation(Column.class) != null
        );
        EmptyAssert.isNotEmpty(fieldList);

        // 拼装 要插入数据的 字段（属性值不为空字段）
        // insert into `tb_example_mysql_info` ( %s ) values (?, ?), (?, ?)
        // %s 号占位符处填入：`name`, `sex`
        StringBuilder columnBuilder = new StringBuilder();
        // 根据样例实体，汇总所有属性值不为空的字段
        List<Field> notNullFieldList = new ArrayList<>(fieldList.size());
        for (Field field : fieldList) {
            Object fieldValue = FieldUtil.readField(conditionEO, field);
            if (EmptyValidate.isNotNull(fieldValue)) {
                columnBuilder.append(" `").append(field.getAnnotation(Column.class).name()).append("` ").append(",");
                notNullFieldList.add(field);
            }
        }
        EmptyAssert.isNotEmpty(notNullFieldList);

        // 拼装 参数占位符
        // insert into `tb_example_mysql_info` (`name`, `sex`) values %s
        // %s 号占位符处填入：(?, ?), (?, ?)
        StringBuilder symbolBuilder = new StringBuilder();
        // 汇总 所有入参
        List<Object> params = new ArrayList<>(notNullFieldList.size() * entityEOList.size());
        for (int i = 0; i < entityEOList.size(); i++) {
            EO entity = entityEOList.get(i);
            EmptyAssert.isNotNull(entity);
            symbolBuilder.append("(");
            for (int j = 0; j < notNullFieldList.size(); j++) {
                Object fieldValue = FieldUtil.readField(entity, notNullFieldList.get(j));
                EmptyAssert.isNotNull(fieldValue);
                symbolBuilder.append("?");
                params.add(fieldValue);
                if (j != notNullFieldList.size() - 1) {
                    symbolBuilder.append(",");
                }
            }
            symbolBuilder.append(")");
            if (i != entityEOList.size() - 1) {
                symbolBuilder.append(",");
            }
        }
        EmptyAssert.isNotEmpty(params);

        // 完整SQL：insert into `tb_example_mysql_info` (`name`, `sex`) values (?, ?), (?, ?)
        String sql = String.format(" INSERT INTO %s (%s) VALUES %s ", // INSERT INTO %s (%s) VALUES %s
                                   TableUtil.split(conditionEO), // `tb_example_mysql_info`
                                   columnBuilder.substring(0, columnBuilder.length() - 1), // `name`, `sex`
                                   symbolBuilder // (?, ?), (?, ?)
        );
        return new SqlParamEO(sql, params);
    }

    /**
     * 多条实体记录插入，如果主键或唯一键存在，那么更新
     */
    public static <EO extends BaseCommonEO> SqlParamEO batchInsertExistUpdateEOSql(final EO condition,
                                                                                   final List<EO> entityList) {
        EmptyAssert.isNotNull(condition);
        EmptyAssert.isNotEmpty(entityList);

        // 查找所有标记 Column 注解 的属性
        List<Field> fieldList = FieldUtil.queryFieldList(condition.getClass(),
                                                         field -> field.getAnnotation(Column.class) != null
        );
        EmptyAssert.isNotEmpty(fieldList);

        // 拼装 要插入数据的 字段（属性值不为空字段）
        // insert into `tb_example_mysql_info` ( %s ) values (?, ?), (?, ?) on duplicate key update `name` = values(`name`), `sex` = values(`sex`)
        // %s 号占位符处填入：`name`, `sex`
        StringBuilder columnBuilder = new StringBuilder();
        // 拼装 要更新数据的 字段（属性值不为空字段）
        // insert into `tb_example_mysql_info` (`name`, `sex`) values (?, ?), (?, ?) on duplicate key update %s
        // %s 号占位符处填入：`name` = values(`name`), `sex` = values(`sex`)
        StringBuilder updateBuilder = new StringBuilder();
        // 根据样例实体，汇总所有属性值不为空的字段
        List<Field> notNullFieldList = new ArrayList<>(fieldList.size());
        for (Field field : fieldList) {
            Object fieldValue = FieldUtil.readField(condition, field);
            if (EmptyValidate.isNotNull(fieldValue)) {
                String columnName = field.getAnnotation(Column.class).name();
                columnBuilder.append("`").append(columnName).append("`").append(",");
                updateBuilder.append("`")
                             .append(columnName)
                             .append("`")
                             .append(" = VALUES(")
                             .append(columnName)
                             .append("),");
                notNullFieldList.add(field);
            }
        }
        EmptyAssert.isNotEmpty(notNullFieldList);

        // 拼装 参数占位符
        // insert into `tb_example_mysql_info` (`name`, `sex`) values %s on duplicate key update `name` = values(`name`), `sex` = values(`sex`)
        // %s 号占位符处填入：(?, ?), (?, ?)
        StringBuilder symbolBuilder = new StringBuilder();
        // 汇总 所有入参
        List<Object> params = new ArrayList<>(notNullFieldList.size() * entityList.size());
        for (int i = 0; i < entityList.size(); i++) {
            EO entity = entityList.get(i);
            EmptyAssert.isNotNull(entity);
            symbolBuilder.append("(");
            for (int j = 0; j < notNullFieldList.size(); j++) {
                Object fieldValue = FieldUtil.readField(entity, notNullFieldList.get(j));
                EmptyAssert.isNotNull(fieldValue);
                symbolBuilder.append("?");
                params.add(fieldValue);
                if (j != notNullFieldList.size() - 1) {
                    symbolBuilder.append(",");
                }
            }
            symbolBuilder.append(")");
            if (i != entityList.size() - 1) {
                symbolBuilder.append(",");
            }
        }
        EmptyAssert.isNotEmpty(params);

        // 完整SQL：insert into `tb_example_mysql_info` (`name`, `sex`) values (?, ?), (?, ?) on duplicate key update `name` = values(`name`), `sex` = values(`sex`)
        String sql = String.format(" INSERT INTO %s (%s) VALUES %s ON DUPLICATE KEY UPDATE %s ",
                                   //  INSERT INTO %s (%s) VALUES %s ON DUPLICATE KEY UPDATE %s
                                   TableUtil.split(condition),
                                   // `tb_example_mysql_info`
                                   columnBuilder.substring(0, columnBuilder.length() - 1),
                                   // `name`, `sex`
                                   symbolBuilder,
                                   // (?, ?), (?, ?)
                                   updateBuilder.substring(0, updateBuilder.length() - 1)
                                   // `name` = values(`name`), `sex` = values(`sex`)
        );
        return new SqlParamEO(sql, params);
    }

    /*
      -----------------------------拼装 完整 Insert SQL（数据来源Map）-------------------------------
     */

    /**
     * 单条Map记录插入
     */
    public static SqlParamEO insertMapSql(String tableName, Map<String, Object> entityMap) {
        EmptyAssert.isNotBlank(tableName);
        EmptyAssert.isNotEmpty(entityMap);

        // 拼装 要插入数据的 字段（value值不为空字段）
        // insert into `tb_example_mysql_info` ( %s ) values ()
        // %s 号占位符处填入：`name`, `sex`
        StringBuilder columnBuilder = new StringBuilder();
        // 拼装 参数占位符
        // insert into `tb_example_mysql_info` (`name`, `sex`) values ( %s )
        // %s 号占位符处填入：?, ?
        StringBuilder symbolBuilder = new StringBuilder();
        // 汇总所有属性值不为空的key
        List<Object> params = new ArrayList<>(entityMap.size());
        for (Map.Entry<String, Object> entry : entityMap.entrySet()) {
            if (EmptyValidate.isNotNull(entry.getValue())) {
                columnBuilder.append("`").append(entry.getKey()).append("`").append(",");
                symbolBuilder.append("?").append(",");
                params.add(entry.getValue());
            }
        }
        EmptyAssert.isNotEmpty(params);

        // 完整SQL：insert into `tb_example_mysql_info` (`name`, `sex`) values (?, ?)
        String sql = String.format(" INSERT INTO %s (%s) VALUES (%s) ", // INSERT INTO %s (%s) VALUES (%s)
                                   TableUtil.split(tableName),  // `tb_example_mysql_info`
                                   columnBuilder.substring(0, columnBuilder.length() - 1),  // `name`, `sex`
                                   symbolBuilder.substring(0, symbolBuilder.length() - 1)  // ?, ?
        );
        return new SqlParamEO(sql, params);
    }

    /**
     * 单条Map记录插入，如果主键或唯一键存在，那么更新
     */
    public static SqlParamEO insertExistUpdateMapSql(String tableName, Map<String, Object> entityMap) {
        EmptyAssert.isNotBlank(tableName);
        EmptyAssert.isNotEmpty(entityMap);

        // 拼装 要插入数据的 字段（属性值不为空字段）
        // insert into `tb_example_mysql_info` ( %s ) values (?, ?) on duplicate key update `name` = values(`name`), `sex` = values(`sex`)
        // %s 号占位符处填入：`name`, `sex`
        StringBuilder columnBuilder = new StringBuilder();
        // 拼装 参数占位符
        // insert into `tb_example_mysql_info` (`name`, `sex`) values ( %s ) on duplicate key update `name` = values(`name`), `sex` = values(`sex`)
        // %s 号占位符处填入：?, ?
        StringBuilder symbolBuilder = new StringBuilder();
        // 拼装 要更新数据的 字段（属性值不为空字段）
        // insert into `tb_example_mysql_info` (`name`, `sex`) values (?, ?) on duplicate key update %s;
        // %s 号占位符处填入：`name` = values(`name`), `sex` = values(`sex`)
        StringBuilder updateBuilder = new StringBuilder();
        // 汇总所有属性值不为空的key
        List<Object> params = new ArrayList<>(entityMap.size());
        for (Map.Entry<String, Object> entry : entityMap.entrySet()) {
            if (EmptyValidate.isNotNull(entry.getValue())) {
                columnBuilder.append("`").append(entry.getKey()).append("`").append(",");
                symbolBuilder.append("?").append(",");
                updateBuilder.append("`")
                             .append(entry.getKey())
                             .append("`")
                             .append(" = VALUES(")
                             .append(entry.getKey())
                             .append("),");
                params.add(entry.getValue());
            }
        }
        EmptyAssert.isNotEmpty(params);

        // 完整SQL：insert into `tb_example_mysql_info` (`name`, `sex`) values (?, ?) on duplicate key update `name` = values(`name`), `sex` = values(`sex`)
        String sql = String.format(" INSERT INTO %s (%s) VALUES (%s) ON DUPLICATE KEY UPDATE %s ",
                                   // INSERT INTO %s (%s) VALUES (%s) ON DUPLICATE KEY UPDATE %s
                                   TableUtil.split(tableName),
                                   // `tb_example_mysql_info`
                                   columnBuilder.substring(0, columnBuilder.length() - 1),
                                   // `name`, `sex`
                                   symbolBuilder.substring(0, symbolBuilder.length() - 1),
                                   // ?, ?
                                   updateBuilder.substring(0, updateBuilder.length() - 1)
                                   // `name` = values(`name`), `sex` = values(`sex`)
        );
        return new SqlParamEO(sql, params);
    }

    /**
     * 多条Map记录插入
     */
    public static SqlParamEO batchInsertMapSql(final String tableName,
                                               final Map<String, Object> conditionMap,
                                               final List<Map<String, Object>> entityMapList) {
        EmptyAssert.isNotBlank(tableName);
        EmptyAssert.isNotEmpty(conditionMap);
        EmptyAssert.isNotEmpty(entityMapList);

        // 拼装 要插入数据的 字段（value值不为空字段）
        // insert into `tb_example_mysql_info` ( %s ) values (?, ?), (?, ?)
        // %s 号占位符处填入：`name`, `sex`
        StringBuilder columnBuilder = new StringBuilder();
        // 根据样例Map，汇总所有属性值不为空的key
        List<String> notNullFieldList = new ArrayList<>(conditionMap.size());
        for (Map.Entry<String, Object> entry : conditionMap.entrySet()) {
            if (EmptyValidate.isNotNull(entry.getValue())) {
                columnBuilder.append("`").append(entry.getKey()).append("`").append(",");
                notNullFieldList.add(entry.getKey());
            }
        }
        EmptyAssert.isNotEmpty(notNullFieldList);

        // 拼装 参数占位符
        // insert into `tb_example_mysql_info` (`name`, `sex`) values %s
        // %s 号占位符处填入：(?, ?), (?, ?)
        StringBuilder symbolBuilder = new StringBuilder();
        // 汇总 所有入参
        List<Object> params = new ArrayList<>(notNullFieldList.size() * entityMapList.size());
        for (int i = 0; i < entityMapList.size(); i++) {
            Map<String, Object> entityMap = entityMapList.get(i);
            EmptyAssert.isNotNull(entityMap);
            symbolBuilder.append("(");
            for (int j = 0; j < notNullFieldList.size(); j++) {
                Object entryValue = entityMap.get(notNullFieldList.get(j));
                EmptyAssert.isNotNull(entryValue);
                symbolBuilder.append("?");
                params.add(entryValue);
                if (j != notNullFieldList.size() - 1) {
                    symbolBuilder.append(",");
                }
            }
            symbolBuilder.append(")");
            if (i != entityMapList.size() - 1) {
                symbolBuilder.append(",");
            }
        }
        EmptyAssert.isNotEmpty(params);

        // 完整SQL：insert into `tb_example_mysql_info` (`name`, `sex`) values (?, ?), (?, ?)
        String sql = String.format(" INSERT INTO %s (%s) VALUES %s ", // INSERT INTO %s (%s) VALUES %s
                                   TableUtil.split(tableName), // `tb_example_mysql_info`
                                   columnBuilder.substring(0, columnBuilder.length() - 1), // `name`, `sex`
                                   symbolBuilder // (?, ?), (?, ?)
        );
        return new SqlParamEO(sql, params);
    }

    /**
     * 多条Map记录插入，如果主键或唯一键存在，那么更新
     */
    public static SqlParamEO batchInsertExistUpdateMapSql(final String tableName,
                                                          final Map<String, Object> conditionMap,
                                                          final List<Map<String, Object>> entityMapList) {
        EmptyAssert.isNotBlank(tableName);
        EmptyAssert.isNotEmpty(conditionMap);
        EmptyAssert.isNotEmpty(entityMapList);

        // 拼装 要插入数据的 字段（属性值不为空字段）
        // insert into `tb_example_mysql_info` ( %s ) values (?, ?), (?, ?) on duplicate key update `name` = values(`name`), `sex` = values(`sex`)
        // %s 号占位符处填入：`name`, `sex`
        StringBuilder columnBuilder = new StringBuilder();
        // 拼装 要更新数据的 字段（属性值不为空字段）
        // insert into `tb_example_mysql_info` (`name`, `sex`) values (?, ?), (?, ?) on duplicate key update %s;
        // %s 号占位符处填入：`name` = values(`name`), `sex` = values(`sex`)
        StringBuilder updateBuilder = new StringBuilder();
        // 根据样例Map，汇总所有属性值不为空的key
        List<String> notNullFieldList = new ArrayList<>(conditionMap.size());
        for (Map.Entry<String, Object> entry : conditionMap.entrySet()) {
            if (EmptyValidate.isNotNull(entry.getValue())) {
                columnBuilder.append("`").append(entry.getKey()).append("`").append(",");
                updateBuilder.append("`")
                             .append(entry.getKey())
                             .append("`")
                             .append(" = VALUES(")
                             .append(entry.getKey())
                             .append("),");
                notNullFieldList.add(entry.getKey());
            }
        }
        EmptyAssert.isNotEmpty(notNullFieldList);

        // 拼装 参数占位符
        // insert into `tb_example_mysql_info` (`name`, `sex`) values %s on duplicate key update `name` = values(`name`), `sex` = values(`sex`)
        // %s 号占位符处填入：(?, ?), (?, ?)
        StringBuilder symbolBuilder = new StringBuilder();
        // 汇总 所有入参
        List<Object> params = new ArrayList<>(notNullFieldList.size() * entityMapList.size());
        for (int i = 0; i < entityMapList.size(); i++) {
            Map<String, Object> entityMap = entityMapList.get(i);
            EmptyAssert.isNotNull(entityMap);
            symbolBuilder.append("(");
            for (int j = 0; j < notNullFieldList.size(); j++) {
                Object entryValue = entityMap.get(notNullFieldList.get(j));
                EmptyAssert.isNotNull(entryValue);
                symbolBuilder.append("?");
                params.add(entryValue);
                if (j != notNullFieldList.size() - 1) {
                    symbolBuilder.append(",");
                }
            }
            symbolBuilder.append(")");
            if (i != entityMapList.size() - 1) {
                symbolBuilder.append(",");
            }
        }
        EmptyAssert.isNotEmpty(params);

        // 完整SQL：insert into `tb_example_mysql_info` (`name`, `sex`) values (?, ?), (?, ?) on duplicate key update `name` = values(`name`), `sex` = values(`sex`)
        String sql = String.format(" INSERT INTO %s (%s) VALUES %s ON DUPLICATE KEY UPDATE %s ",
                                   //  INSERT INTO %s (%s) VALUES %s ON DUPLICATE KEY UPDATE %s
                                   TableUtil.split(tableName),
                                   // `tb_example_mysql_info`
                                   columnBuilder.substring(0, columnBuilder.length() - 1),
                                   // `name`, `sex`
                                   symbolBuilder,
                                   // (?, ?), (?, ?)
                                   updateBuilder.substring(0, updateBuilder.length() - 1)
                                   // `name` = values(`name`), `sex` = values(`sex`)
        );
        return new SqlParamEO(sql, params);
    }
}
