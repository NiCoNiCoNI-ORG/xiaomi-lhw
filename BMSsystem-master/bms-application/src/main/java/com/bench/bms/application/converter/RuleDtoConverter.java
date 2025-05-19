package com.bench.bms.application.converter;

import com.bench.bms.application.model.RuleDto;
import com.bench.bms.domain.model.RuleDo;
import org.mapstruct.Mapper;

/**
 * @Author bench
 * @Date 2024/06/18 13:38
 **/

@Mapper(componentModel = "spring")
public interface RuleDtoConverter {

    RuleDto toDto(RuleDo ruleDo);

    RuleDo toDo(RuleDto ruleDto);

}
