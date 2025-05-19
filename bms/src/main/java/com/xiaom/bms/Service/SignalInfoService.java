package com.xiaom.bms.Service;

import com.xiaom.bms.model.SignalInfo;

import java.util.List;

public interface SignalInfoService {
    int createSignalInfo(SignalInfo signalInfo);
    int updateSignalInfo(SignalInfo signalInfo);
    int deleteSignalInfo(Long id);
    SignalInfo getSignalInfoById(Long id);
    List<SignalInfo> getAllSignalInfos();
}