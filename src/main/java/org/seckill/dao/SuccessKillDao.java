package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.SuccessKilled;

public interface SuccessKillDao {
    /*插入购买明细，可过滤重复*/
    int insertSuccessKilled(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
    /*根据id查询SuccessKilled并携带seckill商品秒杀列表*/
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);
}
