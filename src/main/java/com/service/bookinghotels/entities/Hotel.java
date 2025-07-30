package com.service.bookinghotels.entities;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotels")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "distance_from_city_centre")
    private Double distanceFromCityCentre;

    @Builder.Default
    @Column(name = "rating")
    private Integer rating = 0;

    @Builder.Default
    @Column(name = "grades_counts")
    private Integer gradesCounts = 0;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<Room> rooms = new ArrayList<>();
}