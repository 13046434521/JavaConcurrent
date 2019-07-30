package com.jtl.concurrent.example.aqs.cyclicbarriar;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 作者:jtl
 * 日期:Created in 2019/7/30 19:40
 * 描述: 带Runnable的CyclicBarrier示例
 * 更改:
 */

class CyclicBarrierExample2 {
    private static int count=20;
    private static CyclicBarrier sCyclicBarrier=new CyclicBarrier(5, () ->
            System.out.println("Runnable is running")
    );

    public static void main(String[] args) throws Exception{
        ExecutorService executorService= Executors.newCachedThreadPool();

        for (int i=0;i<count;i++){
            int threadNum=i;
            Thread.sleep(1000);
            executorService.execute(()->{
                try {
                    Thread.currentThread().setName("executorService:"+threadNum);
                    test(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("InterruptedException:"+e.getMessage());
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                    System.out.println("BrokenBarrierException:"+e.getMessage());
                }
            });
        }

        //关闭线程池
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException, BrokenBarrierException {
        System.out.println("wait:"+threadNum+" ThreadName: "+Thread.currentThread().getName());
        sCyclicBarrier.await();
        System.out.println("continue:"+threadNum+" ThreadName: "+Thread.currentThread().getName());
    }
}
