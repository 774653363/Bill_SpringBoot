package ek.zhou.springboot.controller;

import ek.zhou.springboot.dao.ProviderDao;
import ek.zhou.springboot.entities.Provider;
import ek.zhou.springboot.service.ProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 供应商控制器
 */
@Controller
public class ProviderController {
    //获取日志对象
    Logger logger = LoggerFactory.getLogger(getClass());
    //注入service
    @Autowired
    private ProviderService providerService;
    /**
     * 查询所有供应商列表并响应给列表页面
     * @return
     */
    @GetMapping("/providers")
    public String providers(Provider provider, Map<String,Object> map){

        //使用日志记录
        logger.info("查询供应商列表...");
        //调用dao查询所有数据
        List<Provider> all = providerService.getProviders(provider);
        //存放数据到map中响应给页面
        map.put("providers",all);

        map.put("providerName",provider.getProviderName());
        return "provider/list";
    }

    /**
     * 根据id查询供应商信息
     * 使用type区分跳转页面是查询页面还是更新页面
     * @param pid
     * @param map
     * @param type
     * @return
     */
    @GetMapping("/provider/{pid}")
    public String view(@PathVariable Integer pid,Map<String,Object> map,@RequestParam(defaultValue = "view") String type){
        //使用日志记录信息
        logger.info("查询供应商详情");
        //调用dao查询数据
        Provider provider = providerService.getProviderByPid(pid);
        //将数据存入map
        map.put("provider",provider);
        //跳转到页面
        return "provider/"+type;
    }

    /**
     * 更新供应商信息
     * @param provider
     * @return
     */
    @PutMapping("/provider")
    public String update(Provider provider){
        //使用日志记录信息
        logger.info("更新供应商");
        //调用dao更新数据
        providerService.updateProvider(provider);
        //跳转回列表页面
        return "redirect:/providers";
    }

    /**
     * 跳转到供应商添加页面
     * @return
     */
    @GetMapping("/provider")
    public String addUI(){
        //使用日志记录信息
        logger.info("跳转到添加供应商页面");
        return "provider/add";
    }
    /**
     * 添加供应商
     */
    @PostMapping("/provider")
    public String add(Provider provider){
        //使用日志记录信息
        logger.info("添加供应商");
        //调用dao添加数据
        providerService.addProvider(provider);
        //跳转回列表页面
        return "redirect:/providers";
    }

    @DeleteMapping("/provider/{pid}")
    public String add(@PathVariable Integer pid){
        //使用日志记录信息
        logger.info("删除供应商");
        //调用dao添加数据
        providerService.deleteProviderByPid(pid);
        //跳转回列表页面
        return "redirect:/providers";
    }


}
