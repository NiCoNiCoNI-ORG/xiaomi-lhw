package com.xiaom.bms.Interceptor;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Properties;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
@Component
public class TableShardingInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        String sql = statementHandler.getBoundSql().getSql();

        // 检测并处理特定表的分表逻辑
        if (sql.contains("battery_warn")) {
            // 根据分表规则修改表名
            sql = sql.replace("battery_warn", getShardingTableName());
            statementHandler.getBoundSql().setSql(sql);
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

    private String getShardingTableName() {
        // 这里实现具体的分表规则
        return "battery_warn_" + System.currentTimeMillis() % 10;
    }
}