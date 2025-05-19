package com.bench.bms.infra.repository.impl;

import com.bench.bms.domain.model.WarnDo;
import com.bench.bms.infra.repository.WarnRepository;
import com.bench.bms.infra.repository.converter.WarnPoConverter;
import com.bench.bms.infra.repository.entity.WarnPo;
import com.bench.bms.infra.repository.mapper.WarnPoMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Author bench
 * @Date 2024/06/18 14:33
 **/

@Service
public class WarnRepositoryImpl implements WarnRepository {

    @Resource
    private WarnPoConverter warnPoConverter;

    @Resource
    private WarnPoMapper warnPoMapper;

    @Override
    public int saveWarn(WarnDo warnDo) {

        WarnPo warnPo = warnPoConverter.toPo(warnDo);

//        return warnPoMapper.save(warnPo);
        return warnPoMapper.insert(warnPo);
    }
}
