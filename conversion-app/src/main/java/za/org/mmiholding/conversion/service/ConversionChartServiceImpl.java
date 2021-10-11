package za.org.mmiholding.conversion.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.org.mmiholding.conversion.model.Categories;
import za.org.mmiholding.conversion.model.ConversionChart;
import za.org.mmiholding.conversion.model.Units;
import za.org.mmiholding.conversion.repository.CategoriesRepository;
import za.org.mmiholding.conversion.repository.ConversionChartRepository;

import java.util.HashMap;
@Service
@RequiredArgsConstructor
public class ConversionChartServiceImpl implements ConversionChartService{
    @Autowired
    private final ConversionChartRepository conversionChartRepository;
    private HashMap<String, ConversionChart> conversionChartMap = new HashMap<String, ConversionChart>();
    @Override
    public ConversionChart getConversionChartByCategoryAndFromUnitAndToUnit(Categories category, Units fromUnit, Units toUnit){
        ConversionChart conversionChart = conversionChartMap.get(category.getName()+"~"+fromUnit.getName()+"~"+toUnit.getName());
        if(conversionChart == null){
            conversionChart = conversionChartRepository.findByCategoryAndFromUnitAndToUnit(category, fromUnit, toUnit);
        }
        if(conversionChart != null){
            conversionChartMap.put(category.getName()+"~"+fromUnit.getName()+"~"+toUnit.getName(), conversionChart);
        }
        return conversionChart;
    }
}
