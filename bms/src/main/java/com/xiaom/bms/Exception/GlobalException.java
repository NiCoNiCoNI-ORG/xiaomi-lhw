package com.xiaom.bms.Exception;

import com.xiaom.bms.common.ResponseResultType;
import lombok.Data;

@Data
public class GlobalException extends RuntimeException {
    private final ResponseResultType type;

    public GlobalException(ResponseResultType type) {
        super(type.getDefaultMessage());
        this.type = type;
    }

    public GlobalException(ResponseResultType type, String message) {
        super(message);
        this.type = type;
    }

    public ResponseResultType getType() {
        return type;
    }
}