package com.yeah.ruisu.threadpoolexecutorpractice;

import android.os.Message;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CustomThreadPoolManager
{
    private WeakReference<MainActivity.UiHandler> MyUiHandler;
    private static CustomThreadPoolManager INSTANCE;
    private static int NUMBER_OF_CORES = 4;
    private static final int KEEP_ALIVE_TIME = 1;
    private static final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    private final ExecutorService MyExecutorService;
    private final BlockingQueue<Runnable> TaskQueue;
    private List<Future> RunningTaskList;

    public static CustomThreadPoolManager getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new CustomThreadPoolManager();
        }

        return INSTANCE;
    }

    private CustomThreadPoolManager()
    {
        TaskQueue = new LinkedBlockingQueue<>();
        RunningTaskList = new ArrayList<>();
        MyExecutorService = new ThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES,
                                                    KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT,
                                                    TaskQueue);
    }

    public void setHandler(MainActivity.UiHandler InUiHandler)
    {
        this.MyUiHandler = new WeakReference<>(InUiHandler);
    }

    public void AddRunable()
    {
        Future MyFuture = MyExecutorService.submit(new CustomRunnable(this));
        RunningTaskList.add(MyFuture);
    }

    public void CancleAllTasks()
    {
        TaskQueue.clear();

        for(Future iFuture: RunningTaskList)
        {
            if (!iFuture.isDone())
            {
                iFuture.cancel(true);
            }
        }
        RunningTaskList.clear();
    }

    public void PostUiThread(Message InMessage)
    {
        MyUiHandler.get().sendMessage(InMessage);
    }
}
