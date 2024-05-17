package com.hargovind.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	public MappingJacksonValue filtering() {
		
		SomeBean someBean = new SomeBean("value1", "value2", "value3");
		
        MappingJacksonValue mappingJacksonValue = 
        		filterBeans(someBean, "field1", "field2");

        return mappingJacksonValue;
	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue filteringList() {
		
		List<SomeBean> beans = Arrays.asList(
				new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6"));
		
		MappingJacksonValue mappingJacksonValue = 
				filterBeans(beans, "field1", "field3");

      return mappingJacksonValue;
	}
	
	private <T> MappingJacksonValue filterBeans(T beans, String... propertyArrays) {
        
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(beans);
		
        SimpleBeanPropertyFilter filter = 
        		SimpleBeanPropertyFilter.filterOutAllExcept(propertyArrays);
        
        FilterProvider filters = 
        		new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
   }
}
