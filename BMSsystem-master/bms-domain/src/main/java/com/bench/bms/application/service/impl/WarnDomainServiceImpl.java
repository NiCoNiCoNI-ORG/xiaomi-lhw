package com.bench.bms.application.service.impl;

import com.bench.bms.domain.model.WarnDo;
import com.bench.bms.domain.service.WarnDomainService;
import com.bench.bms.infra.repository.WarnRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @Author bench
 * @Date 2024/06/19 13:32
 **/

@Service
public class WarnDomainServiceImpl implements WarnDomainService {

    @Resource
    private WarnRepository warnRepository;

    @Override
    public int saveWarn(WarnDo warnDo) {
        return warnRepository.saveWarn(warnDo);
    }
}
