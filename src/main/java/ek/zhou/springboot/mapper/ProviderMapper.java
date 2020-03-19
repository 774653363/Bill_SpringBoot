package ek.zhou.springboot.mapper;

import ek.zhou.springboot.entities.Provider;

import java.util.List;

/**
 * 供应商mapper接口
 */
public interface ProviderMapper {

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
     * 插入供应商
     * @param provider
     * @return
     */
    public int addProvider(Provider provider);

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
    public int updateProvider(Provider provider);
}
