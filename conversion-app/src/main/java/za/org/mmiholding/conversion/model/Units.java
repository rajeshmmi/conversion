package za.org.mmiholding.conversion.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Data
@Entity
@NoArgsConstructor
@Table(name ="units")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = ConversionChart.class)
public class Units {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Categories category;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, mappedBy = "fromUnit")
    @JsonManagedReference
    private List<ConversionChart> conversionsFrom;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, mappedBy = "toUnit")
    @JsonManagedReference
    private List<ConversionChart> conversionsTo;



}