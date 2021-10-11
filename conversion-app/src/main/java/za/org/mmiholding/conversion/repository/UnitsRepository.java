package za.org.mmiholding.conversion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.org.mmiholding.conversion.model.Categories;
import za.org.mmiholding.conversion.model.Units;

@Repository
public interface UnitsRepository extends CrudRepository<Units, Long> {

    Units findByCategoryAndName(Categories category, String unitsName);
}