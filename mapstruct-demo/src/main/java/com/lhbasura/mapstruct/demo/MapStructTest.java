package com.lhbasura.mapstruct.demo;


import org.junit.Test;

public class MapStructTest {
    @Test
    public void test(){
        User user=new User();
        user.setAddr("南岸水乡");
        user.setAge(18);
        user.setName("lhb");
        user.setPhone("23424232323");
        System.out.println("user:"+user);
        Account account=new Account();
        account=ModelMapper.INSTANCE.userToAccount(user);

        System.out.println("result:"+account);
    }

}
