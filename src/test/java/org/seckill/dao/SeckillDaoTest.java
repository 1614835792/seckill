package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
/*配置spring和junit整合，junit启动时加载springIOC容器*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
    @Autowired
    private SeckillDao seckillDao;
    @Test
    public void reduceNumber() {
        int num=seckillDao.reduceNumber(1000,new Date(115,10,1,5,11));
        System.out.println(new Date(115,11,1,5,11));
        System.out.println(num);
    }

    @Test
    public void queryById() {
        long id=1000;
        Seckill seckill=seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void queryAll() {
        List<Seckill> seckillList=seckillDao.queryAll(0,2);
        for(Seckill seckill:seckillList){
            System.out.println(seckill);
        }

    }
}