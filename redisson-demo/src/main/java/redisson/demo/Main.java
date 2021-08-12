package redisson.demo;

import jodd.util.StringUtil;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Config config = new Config();
        config.useSingleServer().setAddress("redis://47.106.69.79:6379");
        config.useSingleServer().setPassword("491215954");
        final RedissonClient redisson = Redisson.create(config);
        RLock lock = redisson.getLock("anyLock");
        executor.submit(() -> {
            try {
                lock.lockAsync();
                lock.lockAsync(10, TimeUnit.SECONDS);
                Future<Boolean> res = lock.tryLockAsync(3, 2, TimeUnit.SECONDS);
                if (res.get()) {
                    // do your business
                    while (true) {
                        BufferedReader reader = new BufferedReader(new FileReader("F:\\Study\\advanced-java-learn\\redisson-demo\\src\\main\\resources\\test"));
                        String flagStr = reader.readLine();
                        if (StringUtil.isNotBlank(flagStr)) {
                            break;
                        }
                        PrintUtil.printRed(">>>>>do my business1,flag:" + flagStr);
                        Thread.sleep(1000);
                    }
                }
            } catch (Exception e) {

            }
        });

        executor.submit(() -> {
            PrintUtil.printRed("redis is unlock");
            lock.unlock();
        });

//
//        RLock lock = client.getLock("lock1");
//
//        try{
//            lock.lock();
//        }finally{
//            lock.unlock();
//        }

        PrintUtil.printRed(">>>>>all over!!!!!");
    }

}
