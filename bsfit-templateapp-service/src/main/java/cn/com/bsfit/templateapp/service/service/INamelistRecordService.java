package cn.com.bsfit.templateapp.service.service;

import cn.com.bsfit.templateapp.service.entity.NamelistRecord;
import cn.com.bsfit.templateapp.service.mapper.NamelistRecordMapper;
import cn.com.bsfit.templateapp.service.vo.NamelistRecordVO;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 作者
 * @since 2023-06-20
 */
public interface INamelistRecordService extends IService<NamelistRecord> {




    boolean existsNameList(NamelistRecordVO namelistRecordVO);
}
