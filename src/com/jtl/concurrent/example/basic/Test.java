package com.jtl.concurrent.example.basic;

/**
 * 作者:jtl
 * 日期:Created in 2019/8/2 15:01
 * 描述:
 * 1.编译成字节码.class文件  javac -encoding UTF-8 Test.java
 * 2.反编译成，汇编代码  javap -v Test.class
 * 更改:
 */
public class Test {

    private Object mObject = new Object();

    /**
     * 普通同步方法，对象是当前实例对象
     * Test test1 = new Test();
     * test1.test1(); 锁是test1这个对象
     */
    public synchronized void test1() {

    }

    /**
     * 同步代码块，对象是synchronized(***);锁是括号中的对象实例
     * Test test2 = new Test();
     * test2.test2(); 锁是同步代码块中test2这个对象
     */
    public void test2() {
        synchronized (this) {

        }
    }

    /**
     * 同步代码块，对象是synchronized(***);锁是括号中的对象实例
     * Test test3 = new Test();
     * test3.test3(); 锁是同步代码块中mObject这个对象
     */
    public void test3() {
        synchronized (mObject) {

        }
    }


    /**
     * 同步代码块，对象是synchronized(***);锁是括号中的对象实例
     * Test test4 = new Test();
     * test4.test4(); 锁是同步代码块中Test.class
     */
    public void test4() {
        synchronized (Test.class) {

        }
    }

    /**
     * 普通同步方法，对象是当前实例对象
     * Test.test5();
     * 锁是Test.class
     */
    public static synchronized void test5() {

    }
}
