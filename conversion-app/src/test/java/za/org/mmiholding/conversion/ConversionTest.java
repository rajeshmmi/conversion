package za.org.mmiholding.conversion;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import za.org.mmiholding.conversion.utilities.Util;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConversionTest
   
{
	@Autowired
	private WebApplicationContext context;

	private MockMvc mvc;

	@Before
	public void setUp() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	
	@Test
	public void validDataForArea() throws Exception {
		String jsonStr = Util.frameString("Area",
				"Square Kilometer",
				"Square Kilometer",
				1);
		MvcResult result = this.mvc.perform(post(Util.RELATIVE_URL)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
				.andExpect(status().isOk())
				.andReturn();
		assertEquals((Double)1.0, Util.getResponseFromResult(result));
	}

	@Test
	public void validDataForTemperature() throws Exception {
		String jsonStr = Util.frameString("Temperature",
				"Celsius",
				"Fahrenheit",
				32);
		MvcResult result = this.mvc.perform(post(Util.RELATIVE_URL)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
				.andExpect(status().isOk())
				.andReturn();
		assertEquals((Double)89.6, Util.getResponseFromResult(result));
	}

	@Test
	public void invalidUnitType() throws Exception {
		String jsonStr = Util.frameString("Temperature",
				"CelsiusN",
				"Fahrenheit",
				32);
		this.mvc.perform(post(Util.RELATIVE_URL)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
				.andExpect(status().isBadRequest())
				.andReturn();
	}

	@Test
	public void invalidCategoryType() throws Exception {
		String jsonStr = Util.frameString("Mass",
				"Celsius",
				"Fahrenheit",
				32);
		this.mvc.perform(post(Util.RELATIVE_URL)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
				.andExpect(status().isBadRequest())
				.andReturn();
	}

	@Test
	public void conversionFactorNotConfigured() throws Exception {
		String jsonStr = Util.frameString("Length",
				"Kilometer",
				"Meter",
				32);
		this.mvc.perform(post(Util.RELATIVE_URL)
				.accept(MediaType.APPLICATION_JSON_VALUE)
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
				.andExpect(status().isInternalServerError())
				.andReturn();
	}
		   
}