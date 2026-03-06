package controller;




import entity.Show;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import service.BrowseService;

@RestController
@RequestMapping("/api/b2b")
@RequiredArgsConstructor
public class TheatrePartnerController {

    private final BrowseService browseService;
    private final Logger log = LoggerFactory.getLogger(TheatrePartnerController.class);

    @PostMapping("/shows")
    public Show createShow(@RequestBody Show show){
        log.info("Creating show {} for theatre {}", show.getMovieName(), show.getTheatreName());
        return browseService.createShow(show);
    }

    @PutMapping("/shows/{id}")
    public Show updateShow(@PathVariable Long id, @RequestBody Show show){
        log.info("Updating show id {}", id);
        return browseService.updateShow(id, show);
    }

    @DeleteMapping("/shows/{id}")
    public String deleteShow(@PathVariable Long id){
        log.info("Deleting show id {}", id);
        return browseService.deleteShow(id);
    }
}