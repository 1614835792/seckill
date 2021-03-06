package org.seckill.service;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillException;

import java.util.List;

/*
* 业务接口：站在“使用者”角度设计接口
* 三个方面：方法定义粒度，参数，返回类型（return 类型/异常）
*/
public interface SeckillService {
    List<Seckill> getSeckillList();
    Seckill getById(long seckillId);
    /*秒杀开启时输出秒杀接口地址*/
    Exposer exportSeckillUrl(long seckillId);
    /*执行秒杀操作*/
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException,SeckillException;
    SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException,SeckillException;
}
