package cn.com.bsfit.templateapp.service.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 作者
 * @since 2023-06-20
 */
@Data
@ToString
public class NamelistRecordVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer type;

    private String number;

    private LocalDateTime expires;


}
