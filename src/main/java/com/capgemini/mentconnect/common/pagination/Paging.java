package com.capgemini.mentconnect.common.pagination;

import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Collectors;

public class Paging {

    private Integer pageNumber;

    private Integer pageSize;

    private List<Sorting> sort;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<Sorting> getSort() {
        return sort;
    }

    public void setSort(List<Sorting> sort) {
        this.sort = sort;
    }

    public List<Sort.Order> getOrder(){
        return sort != null ? sort.stream().map(elem -> new Sort.Order(elem.getDirection(), elem.getProperty())).collect(Collectors.toList()) : List.of();
    }
}