package a8.utils;

import java.io.StringReader;
import java.util.List;

import org.apache.poi.ss.formula.functions.T;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import a8.data.Person;

public class CsvUtils {
	
	public static <T> String convertBean2Csv(Object o, Class<T> requiredType){
		
		return "-1-1";
	}

	public static Person convertCsv2Bean(String s, Object requiredType,String [] columnMapping){
		
		CSVReader reader = new CSVReader(new StringReader(s));
		
		ColumnPositionMappingStrategy<Person> beanStrategy = new ColumnPositionMappingStrategy<>();
		beanStrategy.setType(Person.class);
		beanStrategy.setColumnMapping(columnMapping);
		
		CsvToBean<Person> csvToBean = new CsvToBean<>();
		List<Person> beansList = csvToBean.parse(beanStrategy, reader);
		
		return beansList.get(0);
	}
	
//	public static <T> T convertCsv2Bean(String s, Class<T> requiredType,String [] columnMapping){
//		
//		CSVReader reader = new CSVReader(new StringReader(s));
//		
//		ColumnPositionMappingStrategy<T> beanStrategy = new ColumnPositionMappingStrategy<>();
//		beanStrategy.setType(requiredType);
//		beanStrategy.setColumnMapping(columnMapping);
//		
//		CsvToBean<T> csvToBean = new CsvToBean<>();
//		List<T> beansList = csvToBean.parse(beanStrategy, reader);
//		
//		return beansList.get(0);
//	}
}
