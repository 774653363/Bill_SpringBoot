package ek.zhou.springboot.service.imp;

import ek.zhou.springboot.entities.Provider;
import ek.zhou.springboot.mapper.ProviderMapper;
import ek.zhou.springboot.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务商服务层实现类
 *
 */
@Service
@CacheConfig(cacheNames = "provider")
public class ProviderServiceImp implements ProviderService {
    //注入mapper
    @Autowired
    private ProviderMapper providerMapper;
    /**
     * 条件查询供应商列表
     * @param provider
     * @return
     */
    @Override
    public List<Provider> getProviders(Provider provider) {
        return providerMapper.getProviders(provider);
    }

    /**
     * 根据pid查询供应商
     * @param pid
     * @return
     */
    @Cacheable(key = "#pid")
    @Override
    public Provider getProviderByPid(Integer pid) {
        return providerMapper.getProviderByPid(pid);
    }

    /**
     * 添加供应商
     * @param provider
     * @return
     */

    @Override
    public Provider addProvider(Provider provider) {
        providerMapper.addProvider(provider);
        return provider;
    }

    /**
     * 根据pid删除供应商
     * @param pid
     * @return
     */
    @Override
    @CacheEvict(key = "#pid")
    public int deleteProviderByPid(Integer pid) {
        return providerMapper.deleteProviderByPid(pid);
    }

    /**
     * 更新供应商
     * @param provider
     * @return
     */
    @CachePut(key = "#provider.pid")
    @Override
    public Provider updateProvider(Provider provider) {
        providerMapper.updateProvider(provider);
        return provider;
    }
}
