package za.org.mmiholding.conversion.controller;

import java.lang.invoke.MethodHandles;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import za.org.mmiholding.conversion.exceptions.ConversionFactorNotConfiguredException;
import za.org.mmiholding.conversion.exceptions.ConversionTypeNotFoundException;
import za.org.mmiholding.conversion.exceptions.InvalidParameterException;
import za.org.mmiholding.conversion.http.ConversionRequest;
import za.org.mmiholding.conversion.http.SuccessResponse;
import za.org.mmiholding.conversion.model.Categories;
import za.org.mmiholding.conversion.model.ConversionChart;
import za.org.mmiholding.conversion.model.Units;
import za.org.mmiholding.conversion.service.CategoriesService;
import za.org.mmiholding.conversion.service.ConversionChartService;
import za.org.mmiholding.conversion.service.UnitsService;

@RestController
@RequestMapping("/unitconversion")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ConversionController {
	private static final Logger logger =  LoggerFactory.getLogger(MethodHandles.lookup().lookupClass()) ;

	@Autowired
	private CategoriesService categoriesService;
	@Autowired
	private UnitsService unitsService;
	@Autowired
	private ConversionChartService conversionChartService;

	@PostMapping("/")
	public ResponseEntity<?>
	process(@RequestBody ConversionRequest request) {
		String type = request.getType();
		String from = request.getFrom();
		String to = request.getTo();
		double value = request.getValue();
		logger.info("unit conversion rest service execution started .............");
		logger.info("The input parameters are conversionType - "+type+", convertFrom - "+from+","
				+ " convertTo - "+to+", unitValueTobeConverted = "+value);


		Categories category = categoriesService.getCategoriesByName(type);
		SuccessResponse response;

		if(category != null){
			Units fromUnit = unitsService.getUnitsByCategoryAndName(category, from);
			Units toUnit = unitsService.getUnitsByCategoryAndName(category, to);

			if(fromUnit == null){
				throw new InvalidParameterException("from_unit "+from+" is not added under the category "+category.getName() );
			}
			if(toUnit == null){
				throw new InvalidParameterException("to_unit "+to+" is not added under the category "+category.getName() );
			}
			ConversionChart conversion = conversionChartService.getConversionChartByCategoryAndFromUnitAndToUnit(category, fromUnit, toUnit);
			if(conversion == null){
				throw new ConversionFactorNotConfiguredException("Conversion chart for the category "+category.getName()+" from_unit - "+fromUnit+ ", to_unit = "+toUnit+" is not configured" );
			}
			response = new SuccessResponse(value*conversion.getConversionFactor()+conversion.getConversionAddend(), toUnit.getName());
		}else{
			throw new ConversionTypeNotFoundException("Conversion category type not found for category "+type );	 
		}


		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}
