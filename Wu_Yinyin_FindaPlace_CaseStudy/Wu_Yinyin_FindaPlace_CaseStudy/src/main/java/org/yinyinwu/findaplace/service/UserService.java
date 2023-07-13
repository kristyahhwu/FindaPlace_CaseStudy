package org.yinyinwu.findaplace.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.yinyinwu.findaplace.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    public UserDetails loadUserByUsername(String userName);

    User findUserByEmail(String email);

    public User saveUser(User user);

    public User getUser(Integer id);

    void encoder(User user);

    List<User> listAllUsers();

    public boolean isEmailUnique(Integer id, String email);



}
