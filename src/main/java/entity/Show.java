package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Show {

    @Id
    @GeneratedValue
    private Long id;

    private String movieName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String theatreName;

    private String city;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "show_id")
    private List<Seat> seats;

    @Version
    private Long version;
}