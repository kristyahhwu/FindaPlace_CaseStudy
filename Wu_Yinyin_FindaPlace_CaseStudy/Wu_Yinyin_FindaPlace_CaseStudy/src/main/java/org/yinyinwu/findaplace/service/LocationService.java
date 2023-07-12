package org.yinyinwu.findaplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yinyinwu.findaplace.exceptions.AuthencationException;
import org.yinyinwu.findaplace.model.Location;
import org.yinyinwu.findaplace.repository.LocationRepository;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> listAllLocations() {
        return (List<Location>) locationRepository.findAll();
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public void deleteLocation(Integer id) throws AuthencationException {
        Integer numberId = locationRepository.countById(id);
        if (numberId == null) {
            throw new AuthencationException("location not found");
        }
        locationRepository.deleteById(id);
    }
}
