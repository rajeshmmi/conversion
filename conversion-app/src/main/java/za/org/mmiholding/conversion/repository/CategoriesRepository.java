package za.org.mmiholding.conversion.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.org.mmiholding.conversion.model.Categories;

@Repository
public interface CategoriesRepository extends CrudRepository<Categories, Long> {
    Categories findByName(String name);
}