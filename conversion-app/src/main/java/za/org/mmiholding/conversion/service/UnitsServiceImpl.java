package za.org.mmiholding.conversion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.org.mmiholding.conversion.model.Categories;
import za.org.mmiholding.conversion.model.Units;
import za.org.mmiholding.conversion.repository.CategoriesRepository;
import za.org.mmiholding.conversion.repository.UnitsRepository;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UnitsServiceImpl implements UnitsService{
    @Autowired
    private final UnitsRepository unitsRepository;
    private HashMap<String, Units> unitsMap = new HashMap<String, Units>();
    @Override
    public Units getUnitsByCategoryAndName(Categories category, String name) {
        Units unit = unitsMap.get(category.getName()+"~"+name);
        if(unit == null){
            unit = unitsRepository.findByCategoryAndName(category, name);
        }
        if(unit != null){
            unitsMap.put(category.getName()+"~"+name, unit);
        }
        return unit;
    }
}
