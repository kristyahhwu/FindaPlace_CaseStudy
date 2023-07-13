package org.yinyinwu.findaplace;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.yinyinwu.findaplace.model.User;
import org.yinyinwu.findaplace.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userrepo;

    @Test
    public void testFindUserByEmail() {
        String email = "test1@gmail.com";
        User user = userrepo.findUserByEmail(email);

        assertThat(user).isNotNull();
    }

    @Test
    public void testDeleteUser() {
        Integer userId = 1;
        userrepo.deleteById(userId);
    }

    @Test
    public void testCountById() {
        Integer id = 1;
        Integer countTestResult = userrepo.countById(id);

        assertThat(countTestResult).isNotNull().isGreaterThan(0);
    }
}
