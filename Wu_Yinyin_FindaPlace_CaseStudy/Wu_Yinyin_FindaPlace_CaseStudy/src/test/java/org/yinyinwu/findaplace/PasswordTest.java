package org.yinyinwu.findaplace;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * @author Lily H.
 *
 * encoding password test
 */

public class PasswordTest {
    @Test
    public void testEncodePassword(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "12345678";
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println(encodedPassword);
        boolean matchPassword = passwordEncoder.matches(rawPassword, encodedPassword);
        assertThat(matchPassword).isTrue();
    }
}
