package com.vidor.service.seckill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SecKillService {
    public static final int TIMEOUT = 3000;
    @Autowired
    private RedisLockService lockService;

    static Map<String, Integer> products;
    static Map<String, Integer> stock;
    static Map<String, String> orders;

    static {
        products = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        products.put("123456", 10000);
        stock.put("123456", 10000);
    }

    public void doSecKill(String productId) {
        String value = String.valueOf(System.currentTimeMillis() + TIMEOUT);
        if (!lockService.lock(productId, value)) {
            System.out.println("未抢到，请重试");
            return;
        }
        int stockNum = stock.get(productId);
        if (stockNum == 0) {
            System.out.println("没有了，结束");
            return;
        }
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        orders.put(UUID.randomUUID().toString(), productId);
        stockNum = stockNum - 1;
        stock.put(productId, stockNum);
        System.out.println("抢到了，当前剩下：" + stockNum + ",当前订单数：" + orders.size());
        lockService.unlock(productId, value);
    }


}
