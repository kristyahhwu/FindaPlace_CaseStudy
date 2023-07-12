package org.yinyinwu.findaplace.repository;

import org.yinyinwu.findaplace.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Integer> {

    // custom query
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findUserByEmail(String email);

    Integer countById(Integer id);
}
