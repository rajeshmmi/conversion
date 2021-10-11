package za.org.mmiholding.conversion.service;

import za.org.mmiholding.conversion.model.Categories;
import za.org.mmiholding.conversion.model.Units;

public interface UnitsService {
    Units getUnitsByCategoryAndName(Categories category, String name);
}
