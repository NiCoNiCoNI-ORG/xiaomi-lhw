package com.bench.bms.api.converter;

import com.bench.bms.api.model.res.WarnVo;
import com.bench.bms.application.model.WarnDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * @Author bench
 * @Date 2024/06/18 13:40
 **/

@Mapper(componentModel = "spring")
public interface WarnVoConverter {

    @Mapping(source = "warnLevel", target = "warnLevel", qualifiedByName = "mapWarnLevel")
    WarnVo toVo(WarnDto warnDto);

    @Named("mapWarnLevel")
    default String mapWarnLevel(Integer warnLevel) {
        if (warnLevel == -1) {
            return "不报警";
        } else {
            return warnLevel.toString();
        }
    }
}
