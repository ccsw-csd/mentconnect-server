package com.ccsw.mentconnect.user;

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

import com.ccsw.mentconnect.user.logic.UserService;
import com.ccsw.mentconnect.user.logic.UserServiceImpl;
import com.ccsw.mentconnect.user.logic.UserSpecification;
import com.ccsw.mentconnect.user.model.UserEntity;
import com.ccsw.mentconnect.user.model.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    final Long ID = 3L;
    final String NAME = "Admin";
    final String USERNAME = "jopepe";
    final String SURNAMES = "MeentConnect";
    final String EMAIL = "jopepe@meentconnect.com";

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    // private UserEntity u;
    // @Autowired
    // private UserDto userDto;
    @Mock
    private UserService userService;
    // @InjectMocks
    // private UserRepository userRepositoryMock =
    // Mockito.mock(UserRepository.class);
    @Mock
    private UserRepository userRepo;
    // @Autowired
    // UserController userController = new UserController();

    // @BeforeEach
    // void setup() {
    // UserEntity mockUser = new UserEntity();
    // List<UserEntity> listaUser = new ArrayList<>();
    // mockUser.setId(ID);
    // mockUser.setName(NAME);
    // mockUser.setUsername(USERNAME);
    // mockUser.setSurnames(SURNAMES);
    // mockUser.setEmail(EMAIL);
    // listaUser.add(mockUser);
    // UserDto mockUserDto = new UserDto();
    // mockUserDto

    // Mockito.when(userRepositoryMock.findAll()).thenReturn(listaUser);
    // }

    @Test
    void findAllUsers() {
        List<UserEntity> listUser = new ArrayList<>();
        // añadimos la clase UserEntity a mock
        listUser.add(mock(UserEntity.class));

        List<UserEntity> users = userService.findAll();
        assertNotNull(users);
        assertEquals(0, users.size());
    }

    @Test
    void findNameUsers() {

        List<UserEntity> users = userRepo.findAll(UserSpecification.searchName(NAME));

        assertNotNull(users);
        assertEquals(0, users.size());
    }

    @Test
    void findIdUsers() {

        List<UserEntity> users = userRepo.findAll(UserSpecification.searchId(ID));

        assertNotNull(users);
        assertEquals(0, users.size());
    }

    @Test
    void findUsernameUsers() {

        List<UserEntity> users = userRepo.findAll(UserSpecification.searchUsername(USERNAME));

        assertNotNull(users);
        assertEquals(0, users.size());
    }

    @Test
    void findEmailUsers() {

        List<UserEntity> users = userRepo.findAll(UserSpecification.searchEmail(EMAIL));

        assertNotNull(users);

        assertEquals(0, users.size());
    }

    @Test
    public void findName() {
        List<UserEntity> user = new ArrayList<>();

        user.add(mock(UserEntity.class));

        when(userRepo.findAll()).thenReturn(user);
        // user.add(fteDto);
        List<UserEntity> users = userServiceImpl.findAll();
        assertNotNull(users);
        assertEquals(1, users.size());
    }

    @Test
    void findId() {
        List<UserEntity> user = new ArrayList<>();
        user.add(mock(UserEntity.class));
        when(userRepo.findAll()).thenReturn(user);
        List<UserEntity> users = userServiceImpl.findAll();
        assertNotNull(users);
        assertEquals(1, users.size());
    }

}
