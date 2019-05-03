package com.coinf.controller;

import com.coinf.entity.Region;
import com.coinf.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionController {

    @Autowired
    private RegionService regionService;

    @RequestMapping(path = "/regions")
    public List<Region> getAllRegions() {
        return regionService.getAllRegions();
    }

}