package org.seckill.web;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/seckill")
public class SeckillController {
    @Autowired
    private SeckillService seckillService;
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @RequestMapping(value="/list",method = RequestMethod.GET)
    public List<Seckill> list(){
     //获取列表页
        List<Seckill>list= seckillService.getSeckillList();
      /*  model.addAttribute("list",list);
        model.addAttribute("test","测试");*/
        return list;
    }
    @RequestMapping(value="/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId")Long seckillId,Model model){
        if(seckillId==null){
            return "redirct:/seckill/list";
        }
        Seckill seckill=seckillService.getById(seckillId);
        if(seckill==null){
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }
    @RequestMapping(value="/{seckillId}/exposer",method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
       SeckillResult<Exposer>result;
       try{
           Exposer exposer=seckillService.exportSeckillUrl(seckillId);
           result=new SeckillResult<Exposer>(true,exposer);
       }catch(Exception e){
           logger.info(e.getMessage(),e);
           result=new SeckillResult<Exposer>(false,e.getMessage());
        }
          return result;
    }
    @RequestMapping(value="/{seckillId}/{md5}/execution",
    method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public SeckillResult<SeckillExecution>execute(@PathVariable("seckillId")Long seckillId,
                                                  @PathVariable("md5")String md5,
                                                  @CookieValue(value="killPhone",required = false) Long phone){
        if(phone==null){
            return new SeckillResult<>(false,"未注册");
        }
         SeckillResult<SeckillExecution>result;
         try{
             SeckillExecution execution=seckillService.executeSeckill(phone,seckillId,md5);
             return new SeckillResult<SeckillExecution>(true,execution);
         }
         catch(SeckillCloseException e){
             SeckillExecution execution=new SeckillExecution(seckillId, SeckillStateEnum.END);
             return new SeckillResult<>(false,execution);
         }
         catch (RepeatKillException e){
             SeckillExecution execution=new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_SECKILL);
             return new SeckillResult<>(false,execution);
         }
         catch (Exception e){
             SeckillExecution execution=new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
             return new SeckillResult<>(false,execution);
         }
    }
    @RequestMapping(value="/time/now",method = RequestMethod.GET)
    public SeckillResult<Long>time(){
          Date now=new Date();
          return new SeckillResult<>(true,now.getTime() );
    }
}
