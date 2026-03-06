package entity;


import enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue
    private Long id;

    private Long showId;

    private Long userId;

    private Integer seatCount;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Version
    private Long version;
}