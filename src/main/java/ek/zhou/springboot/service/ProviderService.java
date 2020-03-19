package ek.zhou.springboot.service;

import ek.zhou.springboot.entities.Provider;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
/**
 * 服务商服务层接口
 *
 */

public interface ProviderService {
    /**
     * 条件查询供应商列表
     * @param provider
     * @return
     */
    public List<Provider> getProviders(Provider provider);
    /**
     * 根据pid查询供应商
     * @param pid
     * @return
     */

    public Provider getProviderByPid(Integer pid);
    /**
     * 添加供应商
     * @param provider
     * @return
     */

    public Provider addProvider(Provider provider);
    /**
     * 根据pid删除供应商
     * @param pid
     * @return
     */
    public int deleteProviderByPid(Integer pid);
    /**
     * 更新供应商
     * @param provider
     * @return
     */
    public Provider updateProvider(Provider provider);
}
