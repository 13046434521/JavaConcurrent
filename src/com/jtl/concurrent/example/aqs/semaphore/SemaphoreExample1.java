package com.jtl.concurrent.example.aqs.semaphore;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 作者:jtl
 * 日期:Created in 2019/7/29 15:34
 * 描述:
 * 更改:
 */

class SemaphoreExample1 {
    private static final int total=20;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore=new Semaphore(3);
        for (int i=0;i<total;i++){
            final int threadNum=i;
            executorService.execute(()-> {
                try {
                    semaphore.acquire();//获取许可
                    test(threadNum);
                    semaphore.release();//释放许可
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }

    private static void test(int time){
        System.out.println(time+" --- ");
    }
}
