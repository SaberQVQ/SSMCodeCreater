package main;

import factory.create.CodeCreater;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Tian Qi
 * @version 1.0
 **/
public class test {

    public static void main(String[] args) throws IOException, TemplateException, SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
//        Entity mm = new Entity(){
//            {
//                setTest("11111");
//            }
//        };

        ThreadPoolExecutor executor = new ThreadPoolExecutor(8,20,0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10));

        for(int i=0;i<31;i++){
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目："+executor.getPoolSize()+"，队列中等待执行的任务数目："+
                    executor.getQueue().size()+"，已执行完的任务数目："+executor.getCompletedTaskCount());
        }

        executor.shutdown();
        /**
         *                               int corePoolSize,
         *                               int maximumPoolSize,
         *                               long keepAliveTime,
         *                               TimeUnit unit,
         *                               BlockingQueue<Runnable> workQueue,
         *                               ThreadFactory threadFactory,
         *                               RejectedExecutionHandler handler
         */




    }

    static class MyTask implements Runnable {
        private int taskNum;

        public MyTask(int num) {
            this.taskNum = num;
        }

        @Override
        public void run() {
            System.out.println("正在执行task "+taskNum);
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task "+taskNum+"执行完毕");
        }
    }

}
