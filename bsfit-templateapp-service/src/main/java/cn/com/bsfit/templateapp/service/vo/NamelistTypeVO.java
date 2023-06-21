package cn.com.bsfit.templateapp.service.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 作者
 * @since 2023-06-20
 */
@Data
public class NamelistTypeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;


}
