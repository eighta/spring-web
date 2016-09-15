package a8.utils;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVWriter;
import com.opencsv.bean.BeanToCsv;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

public class CsvUtils {
	
	public static <T> String convertBean2Csv(Object o, Class<T> requiredType, String [] columnMapping){
		
		ColumnPositionMappingStrategy<T> beanStrategy = new ColumnPositionMappingStrategy<>();
		beanStrategy.setType(requiredType);
		beanStrategy.setColumnMapping(columnMapping);
		
		StringWriter writer = new StringWriter();
		CSVWriter csvWriter = new CSVWriter(writer, ',', '\u0000' );
		
		BeanToCsv<T> beanToCsv = new BeanToCsv<>();
		beanToCsv.write(beanStrategy,csvWriter,Arrays.asList( new Object[] {o} ));
		
		String[] lines = writer.toString().split(System.getProperty("line.separator"));
		return lines[1];
	}
	
	public static <T> T convertCsv2Bean(String theString, Class<T> requiredType, String [] columnMapping){
		
		StringReader theStringReader = new StringReader(theString);
		
		ColumnPositionMappingStrategy<T> beanStrategy = new ColumnPositionMappingStrategy<>();
		beanStrategy.setType(requiredType);
		beanStrategy.setColumnMapping(columnMapping);
		
		CsvToBean<T> csvToBean = new CsvToBean<>();
		List<T> genericList = csvToBean.parse(beanStrategy, theStringReader);
		return genericList.get(0);
	}
}
