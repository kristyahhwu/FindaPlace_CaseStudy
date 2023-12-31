package org.yinyinwu.findaplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.yinyinwu.findaplace.exceptions.AuthencationException;
import org.yinyinwu.findaplace.model.Location;
import org.yinyinwu.findaplace.service.LocationService;

import java.util.List;

@Controller
public class LocationController {


    @Autowired
    private LocationService locationService;

//    @Autowired
//    public LocationController(LocationService locationService){
//        this.locationService = locationService;
//    }


    // list all locations
    @GetMapping("/location")
    public String listAllLocations(Model model) {
        List<Location> listLocations = locationService.listAllLocations();
        model.addAttribute("listLocations", listLocations);
        return "location";
    }

    // add new location
    @GetMapping("/location/add")
    public String addLocation(Model model){
        Location location = new Location();
        model.addAttribute("location", location);
        return "location_form";
    }

    // save location
    @PostMapping("/location/save")
    public String saveLocation(Location location, RedirectAttributes redirectAttributes){
        Location savedLocation = locationService.saveLocation(location);
        locationService.saveLocation(location);
        redirectAttributes.addFlashAttribute("message", "location added");
        return "redirect:/location";
    }

    // delete location
    @GetMapping("/location/delete/{id}")
    public String deleteLocation(@PathVariable(name="id") Integer id, Model model,
                                RedirectAttributes redirectAttributes) {
        try {
            locationService.deleteLocation(id);
            redirectAttributes.addFlashAttribute("message", "deleted successfully");
        } catch (AuthencationException ex) {
            redirectAttributes.addFlashAttribute("message", ex.getMessage());
        }
        return "redirect:/location";
    }
}
