package za.org.mmiholding.conversion.service;

import za.org.mmiholding.conversion.model.Categories;


public interface CategoriesService {
    Categories getCategoriesByName(String name);
}
