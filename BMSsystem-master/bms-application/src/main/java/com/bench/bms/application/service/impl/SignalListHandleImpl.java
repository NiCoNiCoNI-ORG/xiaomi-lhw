package com.bench.bms.application.service.impl;

import com.bench.bms.application.model.SignalDto;
import com.bench.bms.application.service.SignalListHandle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author bench
 * @Date 2024/06/19 20:42
 **/

@Service
public class SignalListHandleImpl implements SignalListHandle {

    @Override
    public List<SignalDto> signalHandle(List<SignalDto> signalDtoList) {

        List<SignalDto> newSignalDtoList = new ArrayList<SignalDto>();

        for (SignalDto signalDto : signalDtoList){
            if (signalDto.getWarnId() == null){

                Map<String, Double> signal = signalDto.getSignal();

                Map<String, Double> signal1 = new HashMap<>();
                signal1.put("Mx", signal.get("Mx"));
                signal1.put("Mi", signal.get("Mi"));

                Map<String, Double> signal2 = new HashMap<>();
                signal2.put("Ix", signal.get("Ix"));
                signal2.put("Ii", signal.get("Ii"));

                SignalDto signalDto1 = new SignalDto(signalDto.getCarId(), 1, signal1);
                SignalDto signalDto2 = new SignalDto(signalDto.getCarId(), 2, signal2);

                newSignalDtoList.add(signalDto1);
                newSignalDtoList.add(signalDto2);

            } else {
                newSignalDtoList.add(signalDto);
            }
        }
        return newSignalDtoList;
    }

}
