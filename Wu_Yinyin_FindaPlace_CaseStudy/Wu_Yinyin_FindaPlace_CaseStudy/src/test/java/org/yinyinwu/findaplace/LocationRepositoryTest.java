package org.yinyinwu.findaplace;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.annotation.Rollback;
import org.yinyinwu.findaplace.model.Location;
import org.yinyinwu.findaplace.repository.LocationRepository;


@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class LocationRepositoryTest {

    @Autowired
    private LocationRepository repo;

    @Test
    public void testCreateLocation() {
        Location location = new Location();
        location.setName("shelter1");
        location.setAddress("123 Daly City");
        Location savedLocation = repo.save(location);

        assertThat(savedLocation).isNotNull();
    }


    @Test
    public void testListAllLocation() {
        Iterable<Location> locations = repo.findAll();
        locations.forEach(System.out::println);

    }
}
