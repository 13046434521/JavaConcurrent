package com.jtl.concurrent.example.aqs.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 作者:jtl
 * 日期:Created in 2019/7/29 14:59
 * 描述:CountDownLatch 例子
 * 更改:
 */

class CountDownLatchExample {

    private static final int total=200;
    public static void main(String[] args) throws InterruptedException{
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch=new CountDownLatch(total);
        for (int i=0;i<total;i++){
            final int threadNum=i;
            executorService.execute(()-> {
                test(threadNum,countDownLatch.getCount());
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
        System.out.println("CountDownLatch --- finish:"+countDownLatch.getCount());

        executorService.shutdown();
    }

    private static void test(int time,long count){
        System.out.println(time+" --- "+count);
    }
}