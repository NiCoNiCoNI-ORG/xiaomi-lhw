package com.xiaom.bms.task;

import com.xiaom.bms.Service.SignalInfoService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class BatterySignalScanTask {

    com.xiaom.bms.Service.SignalInfoService SignalInfoService;
    @Scheduled(fixedRate = 5000)
    public void scanBatterySignals() {
        try {
            // 异步获取电池信号数据
            CompletableFuture.supplyAsync(() -> SignalInfoService.getAllSignalInfos())
                .thenAccept(signals -> {
                })
                .exceptionally(ex -> {
                    return null;
                });
        } catch (Exception e) {
        }
    }
}