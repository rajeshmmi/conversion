package za.org.mmiholding.conversion.utilities;

import java.io.UnsupportedEncodingException;
import java.lang.invoke.MethodHandles;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;

public class Util {

	private static final Logger logger =  LoggerFactory.getLogger(MethodHandles.lookup().lookupClass()) ;
	public static final String RELATIVE_URL = "/unitconversion/";
	
	public static String frameString(String conversionType,String convertFrom,
			String convertTo, double value){
		return new JSONObject().put("type",conversionType)
				.put("from",convertFrom)
				.put("to",convertTo)
				.put("value",value).toString();
	}

public static Double getResponseFromResult(MvcResult result) throws UnsupportedEncodingException {
	String content = result.getResponse().getContentAsString();
	logger.info("Test response received for test case "+content);
	return new JSONObject(content).getDouble("result");

}
}