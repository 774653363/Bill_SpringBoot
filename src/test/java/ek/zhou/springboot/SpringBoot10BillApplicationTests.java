package ek.zhou.springboot;

import ek.zhou.springboot.entities.BillProvider;
import ek.zhou.springboot.entities.Provider;
import ek.zhou.springboot.entities.User;
import ek.zhou.springboot.mapper.BillMapper;
import ek.zhou.springboot.mapper.ProviderMapper;
import ek.zhou.springboot.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBoot10BillApplicationTests {
    @Autowired
    ProviderMapper providerMapper;
    @Autowired
    BillMapper billMapper;
    @Autowired
    UserMapper userMapper;
    @Test
    public void contextLoads() {
        List<Provider> list = providerMapper.getProviders(null);
        System.out.println(list.get(0));
        Provider provider = providerMapper.getProviderByPid(2);
        System.out.println(provider);
        provider.setProviderName("B货域名供应商666");
        providerMapper.updateProvider(provider);
        providerMapper.addProvider(provider);
//        providerMapper.deleteProviderByPid(10);
    }

    @Test
    public void billTest() {
        List<BillProvider> bills = billMapper.getBills(null);
        System.out.println(bills.get(0));
        BillProvider bill = billMapper.getBillByBid(1);
        System.out.println(bill);
        bill.setBillName("ESC包年云服务666");
        billMapper.updateBill(bill);
        bill.setBillName("666");
        billMapper.addBill(bill);
    }
    @Test
    public void userTest() {
        List<User> users = userMapper.getUsers(null);
        System.out.println(users.get(0));
        User user = userMapper.getUserById(2);
        System.out.println(user);
        user.setUsername("666");
        userMapper.updateUser(user);
        user.setUsername("777");
        userMapper.addUser(user);
    }
}
