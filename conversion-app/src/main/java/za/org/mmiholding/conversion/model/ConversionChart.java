package za.org.mmiholding.conversion.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

    @Data
    @Entity
    @NoArgsConstructor
    @Table(name ="conversion_chart")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id",
            scope = ConversionChart.class)
    public class ConversionChart {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "category_id")
        @JsonBackReference
        private Categories category;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "from_unit")
        @JsonBackReference
        private Units fromUnit;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "to_unit")
        @JsonBackReference
        private Units toUnit;

        @Column(name="conversion_factor")
        private double conversionFactor;

        @Column(name="conversion_addend")
        private double conversionAddend;
    }

