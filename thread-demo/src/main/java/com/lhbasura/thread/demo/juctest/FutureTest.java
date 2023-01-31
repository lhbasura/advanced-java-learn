package com.lhbasura.thread.demo.juctest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
public class FutureTest {
    

    private static ExecutorService singleExecutor = Executors.newSingleThreadExecutor();

    private static final int redisTimeout = 1000;

    public static void main(String[]args) throws InterruptedException {

        Callable<Integer> callable1 = () -> {
            int x = 2;
//            while (x>0){

//            }
//            singleExecutor.wait(100);
            Thread.sleep(1000);
//            throw  new SocketException();
            System.out.println("go there");
            return 1;
        };
        Callable<Integer> callable2 = () -> {
            int x = 2;
            System.out.println("callable2");
            return x;
        };

        Future<Integer> future1 = singleExecutor.submit(callable1);

        Integer result = null;
//        Future<Integer> future2 = singleExecutor.submit(callable2);
        try {
//            for(int i=0;i<2;i++){
////                future1 = singleExecutor.submit(callable1);
                result = future1.get(redisTimeout, TimeUnit.MILLISECONDS);
//            }
        } catch (TimeoutException e) {
//            System.out.println("线程操作超时！！！");
        } catch (ExecutionException e) {
//            System.out.println("线程操作异常！！！");
//            e.printStackTrace();
        } catch (InterruptedException exception) {
//            System.out.println("线程操作中止！！！");
//            exception.printStackTrace();
        } finally {
            future1.cancel(true);
        }
        System.out.println("ss");
//        System.out.println("result:"+result);
//
//        try {
//            result=future2.get(redisTimeout, TimeUnit.MILLISECONDS);
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }
//        System.out.println("over:"+result);
//       Thread thread =  new Thread(()->{
//           int x =0;
//           try {
//               x = future2.get(redisTimeout, TimeUnit.MILLISECONDS);
//               System.out.println("after thread:"+x);
//           } catch (InterruptedException e) {
//               e.printStackTrace();
//           } catch (ExecutionException e) {
//               e.printStackTrace();
//           } catch (TimeoutException e) {
//               e.printStackTrace();
//           }finally {
//               future2.cancel(true);
//           }
//           System.out.println(x);
//       });
//       thread.start();



    }
}
