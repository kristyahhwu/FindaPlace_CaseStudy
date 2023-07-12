package org.yinyinwu.findaplace.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.yinyinwu.findaplace.model.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Integer> {

    // implement later
    public Role findRoleByUserRole(String role);

    @Query(value = "select * from role where role.id= (select role_id from users_roles where user_id = :id)", nativeQuery = true)
    public List<Role> findRoleByUser(@Param("id") long id);


}
