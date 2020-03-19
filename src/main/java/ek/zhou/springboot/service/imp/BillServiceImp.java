package ek.zhou.springboot.service.imp;

import ek.zhou.springboot.entities.Bill;
import ek.zhou.springboot.entities.BillProvider;
import ek.zhou.springboot.entities.Provider;
import ek.zhou.springboot.mapper.BillMapper;
import ek.zhou.springboot.mapper.ProviderMapper;
import ek.zhou.springboot.service.BillService;
import ek.zhou.springboot.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 账单服务层实现类
 *
 */
@Service
@CacheConfig(cacheNames = "bill")
public class BillServiceImp implements BillService {
    //注入mapper
    @Autowired
    private BillMapper billMapper;

    @Override
    public List<BillProvider> getBills(Bill bill) {
        return billMapper.getBills(bill);
    }

    @Override
    @Cacheable(key = "#bid")
    public BillProvider getBillByBid(Integer bid) {
        return billMapper.getBillByBid(bid);
    }

    @Override
    public BillProvider addBill(BillProvider billProvider) {
        billMapper.addBill(billProvider);
        return billProvider;
    }

    @Override
    @CacheEvict(key = "#bid")
    public int deleteBillByBid(Integer bid) {

        return billMapper.deleteBillByBid(bid);
    }

    @Override
    @CachePut(key = "#billProvider.bid")
    public BillProvider updateBill(BillProvider billProvider) {
        billMapper.updateBill(billProvider);
        return billProvider;
    }
}
