package za.org.mmiholding.conversion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.org.mmiholding.conversion.model.Categories;
import za.org.mmiholding.conversion.repository.CategoriesRepository;

import java.util.HashMap;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService{
    @Autowired
    private final CategoriesRepository categoriesRepository;
    private HashMap<String, Categories> categoriesMap = new HashMap<String, Categories>();
    @Override
    public Categories getCategoriesByName(String name) {
        Categories category = categoriesMap.get(name);
        if(category == null){
            category = categoriesRepository.findByName(name);
        }
        if(category != null){
            categoriesMap.put(name, category);
        }
        return category;
    }
}
