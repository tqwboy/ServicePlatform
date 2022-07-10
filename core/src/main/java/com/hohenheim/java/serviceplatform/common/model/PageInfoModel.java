package com.hohenheim.java.serviceplatform.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Hohenheim
 * @date 2020/8/17
 * @description 分页信息
 */
@Data
public class PageInfoModel<T> implements Serializable {
    private int pageNum;

    private int pageSize;

    private int pages;

    private int size;

    private long total;

    private boolean hasNextPage;

    private int navigatePages;

    private int[] navigatepageNums;

    private int navigateFirstPage;

    private int navigateLastPage;

    private List<T> list;
}