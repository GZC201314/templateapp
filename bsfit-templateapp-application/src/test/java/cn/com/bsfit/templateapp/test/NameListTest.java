package cn.com.bsfit.templateapp.test;

import cn.com.bsfit.cloud.core.util.BsfitResponse;
import cn.com.bsfit.templateapp.Application;
import cn.com.bsfit.templateapp.service.entity.NamelistRecord;
import cn.com.bsfit.templateapp.service.entity.NamelistType;
import cn.com.bsfit.templateapp.service.service.INamelistRecordService;
import cn.com.bsfit.templateapp.service.service.INamelistTypeService;
import cn.com.bsfit.templateapp.service.vo.NamelistRecordVO;
import cn.com.bsfit.templateapp.service.vo.Page;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * @author GZC
 * @desc
 */
@DisplayName("NameList Test")
@SpringBootTest(classes = {Application.class})
public class NameListTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Resource
    private INamelistTypeService namelistTypeService;
    @Resource
    private INamelistRecordService namelistRecordService;

    @DisplayName("新增名单列表类型")
    @Test
    public void addNameListType() {

            NamelistType namelistType = new NamelistType();
            namelistType.setName("testNUmber");
            boolean save = namelistTypeService.save(namelistType);
            Assertions.assertTrue(save);
    }
    @DisplayName("新增黑名单")
    @Test
    public void addNameListRecord() {
        NamelistRecordVO namelistRecordVO = new NamelistRecordVO();
        namelistRecordVO.setNumber("15655562167");
        namelistRecordVO.setExpires(LocalDateTime.of(2023,7,23,12,56,30));
            NamelistRecord namelistRecord = new NamelistRecord();
            BeanUtils.copyProperties(namelistRecordVO,namelistRecord);
            LocalDateTime now = LocalDateTime.now();
            namelistRecord.setCreateTime(now);
            namelistRecord.setModifyTime(now);
            QueryWrapper<NamelistType> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name","phoneNumber");
            NamelistType phoneNumberType = namelistTypeService.getOne(queryWrapper);
            if (Objects.isNull(phoneNumberType)){
                Assertions.assertFalse(false);
            }
            namelistRecord.setType(phoneNumberType.getId());
            boolean save =  namelistRecordService.save(namelistRecord);
            Assertions.assertTrue(save);
    }

    @DisplayName("更新黑名单,重复情况")
    @Test
    public void updateNameListRecord() {
        NamelistRecordVO namelistRecordVO = new NamelistRecordVO();
        namelistRecordVO.setId(1);
        namelistRecordVO.setNumber("15655552168");
        // 查询新值是否已存在
        QueryWrapper<NamelistRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("number",namelistRecordVO.getNumber());
        List<NamelistRecord> list = namelistRecordService.list(queryWrapper);
        if (!CollUtil.isEmpty(list)){
            Assertions.assertTrue(true);
        }
        NamelistRecord oldNamelistRecord = namelistRecordService.getById(namelistRecordVO.getId());
        oldNamelistRecord.setNumber(namelistRecordVO.getNumber());
        oldNamelistRecord.setModifyTime(LocalDateTime.now());
        boolean result = namelistRecordService.updateById(oldNamelistRecord);

        Assertions.assertTrue(result);
    }
    @DisplayName("更新黑名单")
    @Test
    public void updateNameListRecord1() {
        NamelistRecordVO namelistRecordVO = new NamelistRecordVO();
        namelistRecordVO.setId(1);
        namelistRecordVO.setNumber("17366192087");
        // 查询新值是否已存在
        QueryWrapper<NamelistRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("number",namelistRecordVO.getNumber());
        List<NamelistRecord> list = namelistRecordService.list(queryWrapper);
        if (!CollUtil.isEmpty(list)){
            Assertions.assertTrue(false);
        }
        NamelistRecord oldNamelistRecord = namelistRecordService.getById(namelistRecordVO.getId());
        oldNamelistRecord.setNumber(namelistRecordVO.getNumber());
        oldNamelistRecord.setModifyTime(LocalDateTime.now());
        boolean result = namelistRecordService.updateById(oldNamelistRecord);
        Assertions.assertTrue(result);
    }
    @DisplayName("查询黑名单")
    @Test
    public void queryNameListRecord() {
        NamelistRecordVO namelistRecordVO = new NamelistRecordVO();
        Page page = new Page();
        page.setPage(1);
        page.setPageSize(10);
        QueryWrapper<NamelistRecord> namelistRecordQueryWrapper = new QueryWrapper<>();
        if (StringUtils.hasText(namelistRecordVO.getNumber())){
            namelistRecordQueryWrapper.like("number",namelistRecordVO.getNumber());
        }
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<NamelistRecord> recordPage = new com.baomidou.mybatisplus.extension.plugins.pagination.Page<>(namelistRecordVO.getPage().getPage(), namelistRecordVO.getPage().getPageSize());
        com.baomidou.mybatisplus.extension.plugins.pagination.Page<NamelistRecord> namelistRecordPage = namelistRecordService.page(recordPage, namelistRecordQueryWrapper);
        Assertions.assertTrue(Objects.nonNull(namelistRecordPage));
    }

}
