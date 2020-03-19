package ek.zhou.springboot.mapper;

import ek.zhou.springboot.entities.Bill;
import ek.zhou.springboot.entities.BillProvider;
import ek.zhou.springboot.entities.Provider;

import java.util.List;

/**
 * 账单mapper接口
 */
public interface BillMapper {

    /**
     * 条件查询账单列表
     * @param bill
     * @return
     */
    public List<BillProvider> getBills(Bill bill);

    /**
     * 根据bid查询账单
     * @param bid
     * @return
     */
    public BillProvider getBillByBid(Integer bid);

    /**
     * 插入账单
     * @param billProvider
     * @return
     */
    public int addBill(BillProvider billProvider);

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
    public int updateBill(BillProvider billProvider);
}
