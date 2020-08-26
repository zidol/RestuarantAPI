package kr.co.fastcampus.eatgo.interfaces;


import kr.co.fastcampus.eatgo.applicaton.RegionService;
import kr.co.fastcampus.eatgo.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionController {

    @Autowired
    private RegionService regionsService;

    @GetMapping("/regions")
    public List<Region> list() {
        List<Region> regions = regionsService.getRegions();
//        List<Region> regions = new ArrayList<>();
//        regions.add(Region.builder().name("Seoul").build());
        return  regions;
    }

}
