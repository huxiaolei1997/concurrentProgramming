package com.concurrentprogramming;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author xiaolei hu
 * @date 2018/8/19 16:02
 **/
public class mapExample {
    private static Map<Integer, Integer> map = new HashMap<>();

    private static int threadNum = 200; // 这里改成 1 就正确了，但是这样就不是多线程了
    private static int clientNum = 5000;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadNum);
        for (int i = 0; i < clientNum; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    func(threadNum);
                    semaphore.release();
                } catch (Exception e) {

                }
            }) ;
        }
        executorService.shutdown();
        System.out.println("size = " + map.size());
    }

    public static void func(int threadNum) {
        map.put(threadNum, threadNum);
    }
}
