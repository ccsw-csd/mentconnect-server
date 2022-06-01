package com.ccsw.mentconnect.role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ccsw.mentconnect.common.mapper.BeanMapper;
import com.ccsw.mentconnect.role.logic.RoleServiceImpl;
import com.ccsw.mentconnect.role.model.RoleEntity;
import com.ccsw.mentconnect.role.model.RoleRepository;
import com.ccsw.mentconnect.role.model.RoleTypeEnum;

@ExtendWith(MockitoExtension.class)
public class RoleTest {

    public static final int TOTAL_ROLES = 1;

    @InjectMocks
    RoleServiceImpl roleServiceImpl;

    @Mock
    BeanMapper beanMapper;

    @Mock
    private RoleRepository roleRepository;

    @Test
    public void findAllShouldReturnAllRoles() {

        List<RoleEntity> list = new ArrayList<>();
        list.add(mock(RoleEntity.class));
        when(roleRepository.findAll()).thenReturn(list);

        List<RoleEntity> roles = roleServiceImpl.findAll();

        assertNotNull(roles);
        assertEquals(TOTAL_ROLES, roles.size());
    }

    @Test
    public void findByTypeShouldReturnFilteredRole() {

        List<RoleEntity> list = new ArrayList<>();

        list.add(mock(RoleEntity.class));
        when(roleRepository.findByType(RoleTypeEnum.EXT)).thenReturn(list);

        List<RoleEntity> roles = roleServiceImpl.findByType(RoleTypeEnum.EXT);

        assertNotNull(roles);
        assertEquals(TOTAL_ROLES, roles.size());
    }

}
