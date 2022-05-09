package com.ccsw.mentconnect.common.pagination;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class PagingAndSorting {

    private Paging pageable;

    public Pageable getPageable() {
        Integer page = pageable.getPageNumber() != null ? pageable.getPageNumber() : 0;
        Integer size = pageable.getPageSize() != null ? pageable.getPageSize() : 25;
        List<Sort.Order> orders = pageable.getOrder();
        if(!orders.isEmpty()) {
            return PageRequest.of(page, size, Sort.by(pageable.getOrder()));
        } else {
            return PageRequest.of(page, size);
        }
    }

    public void setPageable(Paging pageable) {
        this.pageable = pageable;
    }
}
