package redisson.demo;

import org.redisson.Redisson;
import org.redisson.api.RFuture;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Test2 {
    public static void main(String[]args){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://47.106.69.79:6379");
        config.useSingleServer().setPassword("491215954");
        final RedissonClient redisson = Redisson.create(config);
        RLock lock = redisson.getLock("anyLock");
        try{
            lock.lockAsync();
            lock.lockAsync(10, TimeUnit.SECONDS);
            RFuture<Boolean> res = lock.tryLockAsync(40, 20, TimeUnit.SECONDS);

            if(res.get()){
                // do your business
                PrintUtil.printRed(">>>>>do my business2");

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            PrintUtil.printRed(">>>>>is unlock");
        }



//
//        RLock lock = client.getLock("lock1");
//
//        try{
//            lock.lock();
//        }finally{
//            lock.unlock();
//        }

        PrintUtil.printRed("all over!!!!!");
    }
}
