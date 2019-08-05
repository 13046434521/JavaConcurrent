package com.jtl.concurrent.example.cas;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 作者:jtl
 * 日期:Created in 2019/8/5 14:38
 * 描述:循环CAS的用法
 * 更改:
 */

class CASTest {
    //AtomicInteger JDK1.5后封装的类，在java/util/concurrent/atomic包中。支持原子操作。
    private static AtomicInteger mAtomicInteger = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 2; i++) {
            Thread.sleep(1000);
            final int name = i;
            executorService.execute(() -> {
                Thread.currentThread().setName("Thread_CAS" + name);
                for (int a = 0; a < 100; a++) {
                    try {
                        Thread.sleep(100);
                        safeCount();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    //使用CAS确保线程安全
    private static void safeCount() {
        for (; ; ) {
            int num = mAtomicInteger.get();
            boolean success = mAtomicInteger.compareAndSet(num, ++num);
            if (success) {
                System.out.println("Thread:" + Thread.currentThread().getName() + "  num:" + mAtomicInteger.get());
                break;
            }
        }
    }

    /**
     * Unsafe类中compareAndSwapInt()方法的参数解释
     * 如果obj内的offset地址偏移量的值value，和expect相等，就证明没有其他线程改变过这个变量，那么就把value值更新为update值。
     *
     * @param obj    比较对象
     * @param offset 偏移量
     * @param expect 需要比较的值
     * @param update 需要更新的值
     * @return 更新成功返回true，失败为false
     */
    public final native boolean compareAndSwapInt(Object obj, long offset, int expect, int update);
}
