package org.yinyinwu.findaplace.service;

import org.springframework.stereotype.Service;
import org.yinyinwu.findaplace.model.Role;

import java.util.List;

@Service
public interface RoleService {

    public void saveRole(Role role);
    public Role findRoleByRoleName(String name);
    public List<Role> getAllRoles();
    public List<Role> getRolesByUser(long id);
}
