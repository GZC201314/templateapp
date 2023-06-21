package cn.com.bsfit.templateapp.service.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author GZC
 * @desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    private Integer total = 0;
    private Integer page = 1;
    private Integer pageSize = 10;
    private String search;
}
