package za.org.mmiholding.conversion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.org.mmiholding.conversion.model.Categories;
import za.org.mmiholding.conversion.model.ConversionChart;
import za.org.mmiholding.conversion.model.Units;

@Repository
public interface ConversionChartRepository extends CrudRepository<ConversionChart, Long> {

    ConversionChart findByCategoryAndFromUnitAndToUnit(Categories category, Units fromUnit, Units toUnits);
}
