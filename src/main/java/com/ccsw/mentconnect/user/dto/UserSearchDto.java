package com.ccsw.mentconnect.user.dto;

import org.springframework.data.domain.Pageable;

/**
 * @author amirzoya
 *
 *         Clase DTO de busqueda paginada de la entidad UserEntity
 *
 */
public class UserSearchDto {

    Pageable pageable;

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
