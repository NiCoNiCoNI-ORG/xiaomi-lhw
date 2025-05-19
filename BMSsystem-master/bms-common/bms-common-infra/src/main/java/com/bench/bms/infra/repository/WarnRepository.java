package com.bench.bms.infra.repository;

import com.bench.bms.domain.model.WarnDo;

/**
 * @Author bench
 * @Date 2024/06/17 15:20
 **/
public interface WarnRepository {

//    保存警告信息
    int saveWarn(WarnDo warnDo);

}
