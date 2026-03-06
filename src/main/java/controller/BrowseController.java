package controller;


import dto.BrowseShowsRequest;
import entity.Show;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import service.BrowseService;

import java.util.List;

@RestController
@RequestMapping("/api/b2c/browse")
@RequiredArgsConstructor
public class BrowseController {

    private final BrowseService browseService;
    private final Logger log = LoggerFactory.getLogger(BrowseController.class);

    @GetMapping("/shows")
    public List<Show> browseShows(BrowseShowsRequest request){
        log.info("Browsing shows in {} for movie {} on {}", request.getCity(), request.getMovieName(), request.getDate());
        return browseService.getShows(request);
    }
}