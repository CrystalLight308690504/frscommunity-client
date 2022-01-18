package com.crystallightghot.frscommunityclient.view.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Date 2022/1/18
 * @Author crystalLightGhost
 * @Version: 1.0
 * description：
 */

public class ThreadPoolUtil {

    private  static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 10, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public static  ThreadPoolExecutor getThreadPoolExecutor(){
        return threadPoolExecutor;
    }
}
