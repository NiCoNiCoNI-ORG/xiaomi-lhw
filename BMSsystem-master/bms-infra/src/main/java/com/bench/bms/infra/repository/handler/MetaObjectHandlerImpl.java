package com.bench.bms.infra.repository.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author bench
 * @Date 2024/06/21 14:31
 **/

@Component
public class MetaObjectHandlerImpl implements MetaObjectHandler {

    @Value("${creator.name}")
    private String defaultUser;

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Long.class, System.currentTimeMillis());
        this.strictInsertFill(metaObject, "updateTime", Long.class, System.currentTimeMillis());
        this.strictInsertFill(metaObject, "createUser", String.class, defaultUser);
        this.strictInsertFill(metaObject, "updateUser", String.class, defaultUser);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updateTime", Long.class, System.currentTimeMillis());
        this.strictInsertFill(metaObject, "updateUser", String.class, defaultUser);
    }
}
