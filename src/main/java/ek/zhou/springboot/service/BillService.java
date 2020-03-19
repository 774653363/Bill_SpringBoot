package ek.zhou.springboot.service;

import ek.zhou.springboot.entities.Bill;
import ek.zhou.springboot.entities.BillProvider;
import ek.zhou.springboot.entities.Provider;

import java.util.List;

/**
 * 账单服务层接口
 *
 */
public interface BillService {
    /**
     * 条件查询账单列表
     * @param bill
     * @return
     */
    public List<BillProvider> getBills(Bill bill);
    /**
     * 根据pid查询账单
     * @param bid
     * @return
     */
    public BillProvider getBillByBid(Integer bid);
    /**
     * 添加账单
     * @param billProvider
     * @return
     */
    public BillProvider addBill(BillProvider billProvider);
    /**
     * 根据bid删除账单
     * @param bid
     * @return
     */
    public int deleteBillByBid(Integer bid);
    /**
     * 更新账单
     * @param billProvider
     * @return
     */
    public BillProvider updateBill(BillProvider billProvider);
}
