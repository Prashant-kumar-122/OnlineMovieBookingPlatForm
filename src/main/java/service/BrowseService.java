package service;



import dto.BrowseShowsRequest;
import entity.Show;
import entity.Theatre;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import repository.ShowRepository;
import repository.TheatreRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BrowseService {

    private final ShowRepository showRepository;
    private final TheatreRepository theatreRepository;

    private final Logger log = LoggerFactory.getLogger(BrowseService.class);

    /**
     * Get all shows filtered by city, movie name, and date.
     */
    public List<Show> getShows(BrowseShowsRequest request) {
        log.info("Fetching shows in city {} for movie {} on date {}",
                request.getCity(), request.getMovieName(), request.getDate());

        List<Theatre> theatres = theatreRepository.findAll(); // simple fetch, can add filters if needed
        List<Show> result = new ArrayList<>();

        for (Theatre theatre : theatres) {
            if (!theatre.getCity().equalsIgnoreCase(request.getCity())) continue;

            for (Show show : theatre.getShows()) {
                LocalDate showDate = show.getStartTime().toLocalDate();
                if (show.getMovieName().equalsIgnoreCase(request.getMovieName())
                        && showDate.equals(request.getDate())) {
                    result.add(show);
                }
            }
        }

        log.info("Found {} shows matching the criteria", result.size());
        return result;
    }

    /**
     * B2B partner operations
     */
    public Show createShow(Show show) {
        log.info("Creating show {} in theatre {}", show.getMovieName(), show.getTheatreName());
        return showRepository.save(show);
    }

    public Show updateShow(Long showId, Show showDetails) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found: " + showId));
        show.setMovieName(showDetails.getMovieName());
        show.setStartTime(showDetails.getStartTime());
        show.setEndTime(showDetails.getEndTime());
        show.setSeats(showDetails.getSeats());
        show.setTheatreName(showDetails.getTheatreName());
        show.setCity(showDetails.getCity());
        log.info("Updated show {}", showId);
        return showRepository.save(show);
    }

    public String deleteShow(Long showId) {
        Show show = showRepository.findById(showId)
                .orElseThrow(() -> new RuntimeException("Show not found: " + showId));
        showRepository.delete(show);
        log.info("Deleted show {}", showId);
        return "Show deleted successfully";
    }
}