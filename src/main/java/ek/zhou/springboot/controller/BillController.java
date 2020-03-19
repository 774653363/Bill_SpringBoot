package ek.zhou.springboot.controller;

import ek.zhou.springboot.entities.Bill;
import ek.zhou.springboot.entities.BillProvider;
import ek.zhou.springboot.entities.Provider;
import ek.zhou.springboot.service.BillService;
import ek.zhou.springboot.service.ProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 账单控制器
 */
@Controller
public class BillController {
    //获取日志对象
    Logger logger = LoggerFactory.getLogger(getClass());
    //注入service
    @Autowired
    private BillService billService;
    @Autowired
    private ProviderService providerService;
    /**
     * 查询所有账单列表并响应给列表页面
     * @return
     */
    @GetMapping("/bills")
    public String bills(BillProvider billProvider, Map<String,Object> map){

        //使用日志记录
        logger.info("查询账单列表...");
        //调用billProviders查询所有数据
        List<BillProvider> billProviders = billService.getBills(billProvider);
        //调用providerService查询供应商所有数据
        List<Provider> providers = providerService.getProviders(null);
        //存放数据到map中响应给页面
        map.put("providers",providers);
        map.put("billProviders",billProviders);

        map.put("billName",billProvider.getBillName());
        map.put("pid",billProvider.getPid());
        map.put("pay",billProvider.getPay());
        return "bill/list";
    }

    /**
     * 根据id查询账单信息
     * 使用type区分跳转页面是查询页面还是更新页面
     * @param bid
     * @param map
     * @param type
     * @return
     */
    @GetMapping("/bill/{bid}")
    public String view(@PathVariable Integer bid,Map<String,Object> map,@RequestParam(defaultValue = "view") String type){
        //使用日志记录信息
        logger.info("查询账单详情");
        if(!"view".equals(type)){
            //调用providerService查询供应商所有数据
            List<Provider> providers = providerService.getProviders(null);
            //存放数据到map中响应给页面
            map.put("providers",providers);
        }
        //调用service查询数据
        BillProvider billProvider = billService.getBillByBid(bid);
        //将数据存入map
        map.put("billProvider",billProvider);
        //跳转到页面
        return "bill/"+type;
    }

    /**
     * 更新账单信息
     * @param billProvider
     * @return
     */
    @PutMapping("/bill")
    public String update(BillProvider billProvider){
        //使用日志记录信息
        logger.info("更新账单");
        //调用service更新数据
        billService.updateBill(billProvider);
        //跳转回列表页面
        return "redirect:/bills";
    }

    /**
     * 跳转到账单添加页面
     * @return
     */
    @GetMapping("/bill")
    public String addUI(Map<String,Object> map){
        //使用日志记录信息
        logger.info("跳转到添加账单页面");
        //调用providerService查询供应商所有数据
        List<Provider> providers = providerService.getProviders(null);
        //存放数据到map中响应给页面
        map.put("providers",providers);
        return "bill/add";
    }
    /**
     * 添加账单
     */
    @PostMapping("/bill")
    public String add(BillProvider billProvider){
        //使用日志记录信息
        logger.info("添加账单");
        //调用service添加数据
        billService.addBill(billProvider);
        //跳转回列表页面
        return "redirect:/bills";
    }

    @DeleteMapping("/bill/{bid}")
    public String delete(@PathVariable Integer bid){
        //使用日志记录信息
        logger.info("删除账单");
        //调用service删除数据
       billService.deleteBillByBid(bid);
        //跳转回列表页面
        return "redirect:/bills";
    }


}
