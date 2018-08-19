package com.concurrentprogramming;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author xiaolei hu
 * @date 2018/8/19 16:02
 **/
public class countExample {

    private static int threadTotal = 200; // 这里改成 1 就正确了，但是这样就不是多线程了
    private static int clientTotal = 5000;

    private static long count = 0;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {

                }
            });
        }
        executorService.shutdown();
        System.out.println("count = " + count);
    }

    public static void add() {
        count++;
    }
}
