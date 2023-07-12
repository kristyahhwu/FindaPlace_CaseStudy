package org.yinyinwu.findaplace;

import org.yinyinwu.findaplace.model.User;
import org.yinyinwu.findaplace.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryTest {

    @Autowired
    private UserRepository userrepo;

    @Test
    public void testFindUserByEmail() {
        String email = "test@gmail.com";
        User user = userrepo.findUserByEmail(email);

        assertThat(user).isNotNull();
    }
}
