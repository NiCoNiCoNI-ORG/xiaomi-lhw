package com.xiaomi.producter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    private final AsyncRequestService asyncRequestService;

    @Autowired
    public RequestController(AsyncRequestService asyncRequestService) {
        this.asyncRequestService = asyncRequestService;
    }

    @GetMapping("/asyncRequests")
    public String triggerAsyncRequests() {
        String url = "http://example.com"; // 目标URL
        for (int i = 0; i < 5; i++) { // 发起5个异步请求
            try {
                asyncRequestService.makeAsyncRequest(url);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Requests have been triggered!";
    }
}