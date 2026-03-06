package entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Theatre {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String city;

    private String address;

    private String partnerName; // B2B partner

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "theatre_id")
    private List<Show> shows;

    @Version
    private Long version; // Optimistic locking
}
