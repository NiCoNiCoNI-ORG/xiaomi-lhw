package com.bench.bms.infra.repository.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

/**
 * @Author bench
 * @Date 2024/06/21 14:31
 **/

@Component
public class TimeMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Long.class, System.currentTimeMillis());
        this.strictInsertFill(metaObject, "updateTime", Long.class, System.currentTimeMillis());
        this.strictInsertFill(metaObject, "createUser", String.class, "creator");
        this.strictInsertFill(metaObject, "updateUser", String.class, "updater");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updateTime", Long.class, System.currentTimeMillis());
        this.strictInsertFill(metaObject, "updateUser", String.class, "updater");
    }
}
