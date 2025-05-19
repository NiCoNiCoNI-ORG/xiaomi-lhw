package com.bench.bms.infra.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * @Author bench
 * @Date 2024/06/24 13:14
 **/

    @Getter
    @AllArgsConstructor
    public enum WarnId {
        MX_MI(1),
        IX_II(2);

        private final Integer value;
    }
