package com.jtl.concurrent.example.aqs.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 作者:jtl
 * 日期:Created in 2019/7/29 15:34
 * 描述:
 * 更改:
 */

class SemaphoreExample4 {
    private static final int total=20;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore=new Semaphore(3);
        for (int i=0;i<total;i++){
            final int threadNum=i;
            executorService.execute(()-> {
                try {
                    if (semaphore.tryAcquire(5, TimeUnit.SECONDS)){//尝试拿许可，时间超过5秒，返回false
                        test(threadNum);
                        semaphore.release();//释放多个许可
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }

    private static void test(int time) throws InterruptedException {
        System.out.println(time+" --- ");
        Thread.sleep(1000);
    }
}
