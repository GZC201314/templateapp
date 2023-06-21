package cn.com.bsfit.templateapp.service.controller;

import cn.com.bsfit.cloud.core.util.BsfitResponse;
import cn.com.bsfit.templateapp.service.entity.NamelistRecord;
import cn.com.bsfit.templateapp.service.entity.NamelistType;
import cn.com.bsfit.templateapp.service.service.INamelistRecordService;
import cn.com.bsfit.templateapp.service.service.INamelistTypeService;
import cn.com.bsfit.templateapp.service.vo.NamelistRecordVO;
import cn.com.bsfit.templateapp.service.vo.NamelistTypeVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 一个Controller 类
 * @Author: yujl
 * @Date: 2023/4/10 16:11
 */
@RestController
@RequestMapping("/namelist")
public class DemoController {
    @Resource
    private INamelistTypeService namelistTypeService;
    @Resource
    private INamelistRecordService namelistRecordService;
    @PostMapping("/addType")
    public BsfitResponse addType(@RequestBody NamelistTypeVO namelistTypeVO){

        if (Objects.nonNull(namelistTypeVO)){
            NamelistType namelistType = new NamelistType();
            BeanUtils.copyProperties(namelistTypeVO,namelistType);
            boolean save = namelistTypeService.save(namelistType);
            if (save){
                return BsfitResponse.successWithMsg("新增名单类型成功");
            }else {
                return BsfitResponse.failedWithError("新增名单类型失败");
            }
        }
        return BsfitResponse.failedWithError("新增名单类型失败,参数错误");
    }
    @PostMapping("/addRecord")
    public BsfitResponse addRecord(@RequestBody NamelistRecordVO namelistRecordVO){

        if (Objects.nonNull(namelistRecordVO)){
            NamelistRecord namelistRecord = new NamelistRecord();
            BeanUtils.copyProperties(namelistRecordVO,namelistRecord);
            LocalDateTime now = LocalDateTime.now();
            namelistRecord.setCreateTime(now);
            namelistRecord.setModifyTime(now);
            QueryWrapper<NamelistType> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name","phoneNumber");
            NamelistType phoneNumberType = namelistTypeService.getOne(queryWrapper);
            if (Objects.isNull(phoneNumberType)){
                return BsfitResponse.failedWithError("新增黑名单失败");
            }
            namelistRecord.setType(phoneNumberType.getId());
            boolean save =  namelistRecordService.save(namelistRecord);
            if (save){
                return BsfitResponse.successWithMsg("新增黑名单成功");
            }else {
                return BsfitResponse.failedWithError("新增黑名单失败");
            }
        }
        return BsfitResponse.failedWithError("新增黑名单失败,参数错误");
    }
}
