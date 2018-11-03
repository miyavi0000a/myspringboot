package com.vidor.service.seckill;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SecKillServiceTest {

    @Autowired
    private SecKillService secKillService;

    @Test
    public void doSecKill() throws Exception{
        int threadnum = 100;
        Thread[] threads = new Thread[threadnum];
        for (int i = 0; i < threadnum; i++) {
            final int num = i;
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.print("开抢：");
                    secKillService.doSecKill("123456");
                }
            });
            threads[i].start();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
            //System.out.print(i + " end.\t");
        }
    }
}