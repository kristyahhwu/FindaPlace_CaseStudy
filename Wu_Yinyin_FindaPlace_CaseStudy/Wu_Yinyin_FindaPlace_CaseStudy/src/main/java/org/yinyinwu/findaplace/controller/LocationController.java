package org.yinyinwu.findaplace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.yinyinwu.findaplace.model.Location;
import org.yinyinwu.findaplace.service.LocationService;

import java.util.List;

@Controller
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/location")
    public String listAllLocations(Model model) {
        List<Location> locations = locationService.listAllLocations();
        model.addAttribute("listLocations", locations);
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
}
