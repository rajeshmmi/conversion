package za.org.mmiholding.conversion.service;

import za.org.mmiholding.conversion.model.Categories;
import za.org.mmiholding.conversion.model.ConversionChart;
import za.org.mmiholding.conversion.model.Units;

public interface ConversionChartService {
    ConversionChart getConversionChartByCategoryAndFromUnitAndToUnit(Categories category, Units fromUnit, Units toUnits);
}
