package a8.test.data;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;

import a8.data.Person;
import a8.utils.CsvUtils;

public class OpenCsvTest {

	// http://www.journaldev.com/12014/opencsv-csvreader-csvwriter-example
	
/*
OpenCSV Annotation

OpenCSV provides annotation based support too. Some of the OpenCSV annotations are;

CsvBindByName: for binding between a column name of the CSV input and a field in a bean.
CsvBindByPosition: for binding between a column number of the CSV input and a field in a bean.
CsvDate: for time based conversion.
However I don’t want to use OpenCSV annotations because then my code will become tightly coupled with OpenCSV.
*/
	
	private static final String CVS_FILE_PATH = "a8/test/cvs/emps.csv"; 
	
	@Test
	@Ignore
	public void writeCsvFile() throws IOException{
		
		StringWriter writer = new StringWriter();
		
		//using custom delimiter and quote character
		CSVWriter csvWriter = new CSVWriter(writer, ',');
		
		List<CsvEmployee> emps = new ArrayList<>();
		emps.add(new CsvEmployee("1", "Javier Larios","89","Colombia") );

		List<String[]> data = toStringArray(emps);

		csvWriter.writeAll(data);

		csvWriter.close();
		
		System.out.println(writer);
	}
	
	private static List<String[]> toStringArray(List<CsvEmployee> emps) {
		List<String[]> records = new ArrayList<String[]>();

		// adding header record
//		records.add(new String[] { "ID", "Name", "Age", "Country" });

		Iterator<CsvEmployee> it = emps.iterator();
		while (it.hasNext()) {
			CsvEmployee emp = it.next();
			records.add(new String[] { emp.getId(), emp.getName(), emp.getAge(), emp.getCountry() });
		}
		return records;
	}
	
	@Test
	@Ignore
	public void anotherCsv2Bean(){
		
		StringReader theStringReader = new StringReader("1,Pankaj Kumar");
		
		ColumnPositionMappingStrategy<Person> beanStrategy = new ColumnPositionMappingStrategy<>();
		beanStrategy.setType(Person.class);
		beanStrategy.setColumnMapping(new String[] {"id"});
		
		CsvToBean<Person> csvToBean = new CsvToBean<>();
		List<Person> emps = csvToBean.parse(beanStrategy, theStringReader);
		System.out.println(emps);
		System.out.println(emps.get(0).getId());
	}
	
	@Test
	public void testCsvUtilsConvertBean2Cvs(){
		Person person = new Person(1,"Javier", "Larios","2016-01-01");
		String csvString = CsvUtils.convertBean2Csv(person, Person.class,new String[] {"firstName","lastName"});
		System.out.println(csvString);
	}
	
	@Test
	public void testCsvUtilsConvertCsv2Bean(){
		String firstName = "Javier";
		String theString = firstName+",Larios";
		Person personConverted = CsvUtils.convertCsv2Bean(theString, Person.class, new String[] {"firstName","lastName"});
		assertEquals(personConverted.getFirstName(),firstName);
	}
	
	@Test
	@Ignore
	public void readFileToBean() throws IOException {
//		CSVReader reader = this.getCSVReader();
		
		StringReader theStringReader = new StringReader("1,Pankaj Kumar,20,India");
		//CSVReader csvReader = new CSVReader(theStringReader);
		
		ColumnPositionMappingStrategy<CsvEmployee> beanStrategy = new ColumnPositionMappingStrategy<>();
		beanStrategy.setType(CsvEmployee.class);
		beanStrategy.setColumnMapping(new String[] {"id","name","age","country"});
		
		CsvToBean<CsvEmployee> csvToBean = new CsvToBean<>();
		List<CsvEmployee> emps = csvToBean.parse(beanStrategy, theStringReader);
		System.out.println(emps);
		System.out.println(emps.get(0).getCountry());
	}
	
	@Ignore
	@Test
	public void readFileInOneShot() throws IOException {
		
		CSVReader reader = this.getCSVReader();
		
		List<CsvEmployee> emps = new ArrayList<>();
		
		List<String[]> records = reader.readAll();
		Iterator<String[]> iterator = records.iterator();
		while (iterator.hasNext()) {
			String[] record = iterator.next();
			CsvEmployee emp = new CsvEmployee();
			emp.setId(record[0]);
			emp.setName(record[1]);
			emp.setAge(record[2]);
			emp.setCountry(record[3]);
			emps.add(emp);
		}

		System.out.println(emps);

		reader.close();
	}
	
	@Ignore
	@Test
	public void readFileOneByOne() throws IOException {
		
		CSVReader reader = this.getCSVReader();

		List<CsvEmployee> emps = new ArrayList<>();

		// read line by line
		String[] record = null;

		while ((record = reader.readNext()) != null) {
			CsvEmployee emp = new CsvEmployee();
			emp.setId(record[0]);
			emp.setName(record[1]);
			emp.setAge(record[2]);
			emp.setCountry(record[3]);
			emps.add(emp);
		}

		System.out.println(emps);
		reader.close();

	}
	
	private CSVReader getCSVReader() throws FileNotFoundException{
		
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource(CVS_FILE_PATH);
		File file = new File(resource.getFile());

		return new CSVReader(new FileReader(file), ',');
	}
}
