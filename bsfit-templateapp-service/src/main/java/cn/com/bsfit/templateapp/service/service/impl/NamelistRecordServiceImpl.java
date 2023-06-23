package cn.com.bsfit.templateapp.service.service.impl;

import cn.com.bsfit.templateapp.service.entity.NamelistRecord;
import cn.com.bsfit.templateapp.service.mapper.NamelistRecordMapper;
import cn.com.bsfit.templateapp.service.service.INamelistRecordService;
import cn.com.bsfit.templateapp.service.utils.RedisUtil;
import cn.com.bsfit.templateapp.service.vo.NamelistRecordVO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 作者
 * @since 2023-06-20
 */
@Service
public class NamelistRecordServiceImpl extends ServiceImpl<NamelistRecordMapper, NamelistRecord> implements INamelistRecordService {

    @Resource
    NamelistRecordMapper namelistRecordMapper;

    @Resource
    RedisUtil redisUtil;

    @Override
    public boolean existsNameList(NamelistRecordVO namelistRecordVO) {
        Object hget = redisUtil.hget("bsfit:namelist:" + namelistRecordVO.getType() + ":" + namelistRecordVO.getNumber().hashCode() % 2000, namelistRecordVO.getNumber());

        System.out.println(hget);
        if (hget!=null){
            return (boolean) hget;
        }
        QueryWrapper<NamelistRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",namelistRecordVO.getType());
        queryWrapper.eq("number",namelistRecordVO.getNumber());
        int count = namelistRecordMapper.selectCount(queryWrapper);
        redisUtil.hset("bsfit:namelist:"+namelistRecordVO.getType()+":"+namelistRecordVO.getNumber().hashCode()%2000,namelistRecordVO.getNumber(),count>0);
        return count>0;
    }
}
