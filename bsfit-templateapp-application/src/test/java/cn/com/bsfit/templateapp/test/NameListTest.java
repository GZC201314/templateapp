package cn.com.bsfit.templateapp.test;

import cn.com.bsfit.cloud.core.util.BsfitResponse;
import cn.com.bsfit.templateapp.Application;
import cn.com.bsfit.templateapp.service.entity.NamelistType;
import cn.com.bsfit.templateapp.service.service.INamelistRecordService;
import cn.com.bsfit.templateapp.service.service.INamelistTypeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import javax.annotation.Resource;
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

}
