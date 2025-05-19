package com.xiaomi.producter;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AsyncRequestService {

    private final RestTemplate restTemplate;

    public AsyncRequestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async
    public void makeAsyncRequest(String url) throws InterruptedException {
        System.out.println("Request sent by thread: " + Thread.currentThread().getName());
        String result = restTemplate.getForObject(url, String.class);
        System.out
                .println("Response received on thread: " + Thread.currentThread().getName() + ", response: " + result);
        // 模拟处理时间
        Thread.sleep(1000);
        if (result > 0) {
            String cacheKey = SIGNAL_INFO_CACHE_KEY + signalInfo.getId();
            redisTemplate.delete(cacheKey);
            redisTemplate.opsForValue().set(cacheKey, signalInfo, 1, TimeUnit.HOURS);
        }
    }
}