package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKillDaoTest {
    @Autowired
    private SuccessKillDao successKillDao;
    @Test
    public void insertSuccessKilled() {
       int num= successKillDao.insertSuccessKilled(1000,17771802134L);
        System.out.println(num);
    }

    @Test
    public void queryByIdWithSeckill() {
        System.out.println(successKillDao);
        SuccessKilled successKilled=successKillDao.queryByIdWithSeckill(1000,17771802134L);
        System.out.println(successKilled);
    }
}