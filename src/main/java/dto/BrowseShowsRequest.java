package dto;


import lombok.Data;
import java.time.LocalDate;

@Data
public class BrowseShowsRequest {
    private String city;
    private String movieName;
    private LocalDate date;
}