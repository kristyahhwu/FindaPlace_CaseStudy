package org.yinyinwu.findaplace.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.yinyinwu.findaplace.exceptions.AuthencationException;
import org.yinyinwu.findaplace.model.Role;
import org.yinyinwu.findaplace.model.User;
import org.yinyinwu.findaplace.repository.RoleRepository;
import org.yinyinwu.findaplace.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder encoder;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(RoleService roleService, UserRepository userRepository, @Lazy BCryptPasswordEncoder encoder) {
        this.roleService = roleService;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }


    // display all users and put into a list
    public List<User> listAllUsers() {
        return (List<User>) userRepository.findAll();
    }

  /*  public List<Role> listAllRoles(){
        return (List<Role>) roleRepository.findAll();
    }*/


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) {
        User user = userRepository.findUserByEmail(userName);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Collection<? extends GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getUserRole()))
                .collect(Collectors.toList());
        return mapRoles;
    }


    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findUserByEmail(email);
        return user;
    }

    public boolean isEmailUnique(Integer id, String email) {
        User userByEmail = userRepository.findUserByEmail(email);
        if (userByEmail == null) {
            return true;
        }
        return userByEmail.getId().equals(id);
    }

    @Transactional
    public User saveUser(User user) {
        boolean isSaved = (user.getId() != null);
        if (isSaved) {
            User existingUser = userRepository.findById(user.getId()).get();

            if (user.getEmail().isEmpty()) {
                user.setPassword(existingUser.getPassword());
            } else {
                user.setPassword(encoder.encode(user.getPassword()));
                user.setRoles(Arrays.asList(roleService.findRoleByRoleName("ROLE_USER")));
                System.out.println("user saved.");
                userRepository.save(user);
            }
        }
        return userRepository.save(user);
    }

    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void encoder(User user) {
        String encoder = passwordEncoder.encode(user.getPassword());
        user.setPassword(encoder);
    }

    public void deleteUser(Integer id) throws AuthencationException {
        Integer numberId = userRepository.countById(id);
        if (numberId == null) {
            throw new AuthencationException("User not found");
        }
        userRepository.deleteById(id);
    }


}
