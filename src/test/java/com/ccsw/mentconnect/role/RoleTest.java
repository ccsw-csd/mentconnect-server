package com.ccsw.mentconnect.role;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
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

    public static final String VALID_TYPE_1 = "EXT";
    public static final String VALID_TYPE = "INT";
    public static final String NOT_VALID_TYPE = "UNK";
    public static final int TOTAL_ROLES = 1;

    @InjectMocks
    RoleServiceImpl roleServiceImpl;

    @Mock
    BeanMapper beanMapper;

    @Mock
    private RoleRepository roleRepository;

    @Test
    public void should() {
        assertThat(RoleTypeEnum.valueOf(VALID_TYPE).name(), is("INT"));
        assertThat(RoleTypeEnum.valueOf(VALID_TYPE_1).name(), is("EXT"));
    }

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
    public void findTypeShouldReturnAllRoles() {

        List<RoleEntity> list = new ArrayList<>();

        list.add(mock(RoleEntity.class));
        RoleTypeEnum type = RoleTypeEnum.EXT;

        when(roleRepository.findByType(type)).thenReturn(list);
        List<RoleEntity> roles = roleServiceImpl.findByType(type);

        assertNotNull(roles);
        assertEquals(1, roles.size());
    }

}
