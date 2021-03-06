package com.wallet.repository;

import com.wallet.model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    private static final String EMAIL = "email@teste.com.br";
    @Autowired
    UserRepository repository;

    @Before
    public void setUp(){
        User u = new User();
        u.setName("Set up User");
        u.setPassword("Password123");
        u.setEmail(EMAIL);

        repository.save(u);
    }

    @After
    public void tearDown(){
        repository.deleteAll();
    }
    @Test
    public void testSave(){
        User u = new User();
        u.setName("Teste");
        u.setPassword("123456");
        u.setEmail("teste@teste.com");

        User response = repository.save(u);
        assertNotNull(response);
    }

    @Test
    public void testFindByEmail(){
        Optional<User> response = repository.findByEmailEquals(EMAIL);
        assertTrue(response.isPresent());
        assertEquals(response.get().getEmail(), EMAIL);
    }
}
