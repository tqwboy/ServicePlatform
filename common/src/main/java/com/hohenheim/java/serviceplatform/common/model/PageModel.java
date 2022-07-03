package com.hohenheim.java.serviceplatform.common.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;


/**
 * @author liaoj
 * @date 2020/12/21
 * @description 分页参数
 */
@Data
public class PageModel {
    private Integer pageSize = 20; // 默认每页显示20条记录

    @NotBlank(message = "请输入页码")
    private Integer pageNum;
}
