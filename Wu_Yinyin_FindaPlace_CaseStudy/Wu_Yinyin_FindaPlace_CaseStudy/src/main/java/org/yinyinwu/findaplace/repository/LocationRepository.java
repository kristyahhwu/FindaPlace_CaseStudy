package org.yinyinwu.findaplace.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.yinyinwu.findaplace.model.Location;
import org.yinyinwu.findaplace.model.User;

public interface LocationRepository extends CrudRepository<Location, Integer> {

    // custom query
    @Query("SELECT l FROM Location l WHERE l.name = :name")
    Location findLocationByName(@Param("name") String name);

    Integer countById(Integer id);
}
