package com.xiaom.bms.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StressTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testConcurrentRequests() throws InterruptedException {
        int numberOfThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        List<Long> responseTimes = Collections.synchronizedList(new ArrayList<>());

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                long startTime = System.currentTimeMillis();
                ResponseEntity<String> response = restTemplate.getForEntity("/api/signal", String.class);
                long endTime = System.currentTimeMillis();
                responseTimes.add(endTime - startTime);
                assert response.getStatusCode().is2xxSuccessful();
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        long totalTime = responseTimes.stream().mapToLong(Long::longValue).sum();
        double averageTime = totalTime / (double) numberOfThreads;
        long maxTime = responseTimes.stream().max(Long::compare).orElse(0L);
        long minTime = responseTimes.stream().min(Long::compare).orElse(0L);

        // Calculate standard deviation
        double variance = responseTimes.stream()
                .mapToDouble(time -> Math.pow(time - averageTime, 2))
                .average().orElse(0);
        double stdDev = Math.sqrt(variance);

        // Calculate percentiles
        responseTimes.sort(Long::compareTo);
        double p95 = responseTimes.get(Math.min((int) (numberOfThreads * 0.95), responseTimes.size() - 1));
        double p99 = responseTimes.get(Math.min((int) (numberOfThreads * 0.99), responseTimes.size() - 1));

        System.out.println("Average response time: " + averageTime  + " ms");
        System.out.println("Max response time: " + maxTime  + " ms");
        System.out.println("Min response time: " + minTime  + " ms");
        System.out.println("Standard deviation: " + stdDev + " ms");
        System.out.println("95th percentile: " + p95  + " ms");
        System.out.println("99th percentile: " + p99  + " ms");
    }
}