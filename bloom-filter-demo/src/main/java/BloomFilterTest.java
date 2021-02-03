import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author  asura
 * @date  2021/2/3 14:51
 * @description 布隆过滤器测试demo
 */

/**
 * 基本思想：
 * 一共存m个比特位，
 * 根据k个hash函数算出k个位置
 * 将这k个位置的值进行与运算
 * 为1则说明存在，这意味着
 * 随着数据的增多，hash碰撞增多，误判存在的概率会变大
 * 但是若运算出来的值为0则一定不存在
 */
public class BloomFilterTest {
    public static void main(String []args){
        int expected = 1000000;
        double fpp = 0.0003;
        List<String>exampleList = new ArrayList<>();
        for(int i=0;i<expected;i++){
            exampleList.add(i+"");
        }
        BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()),expected,fpp);
        for(String str:exampleList){
            bf.put(str);
        }
        int errorTimes =0;
        for (int i =1000001;i<2000000;i++){
            boolean mightContain = bf.mightContain(i+"");
            if(mightContain){
                errorTimes++;
            }
        }
        System.out.println(errorTimes);
    }
}
