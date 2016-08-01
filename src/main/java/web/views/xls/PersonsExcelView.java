package web.views.xls;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import a8.data.Person;

public class PersonsExcelView extends AbstractXlsView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// get data model which is passed by the Spring container
        List<Person> personList = (List<Person>) model.get("persons");
         
        // create a new Excel sheet
        HSSFSheet sheet = (HSSFSheet) workbook.createSheet("Person List");
        sheet.setDefaultColumnWidth(30);
         
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
         
        // create header row
        HSSFRow header = sheet.createRow(0);
         
        header.createCell(0).setCellValue("Count");				//<- SEGUN LOCALE XXX
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue("First Name");		//<- SEGUN LOCALE XXX
        header.getCell(1).setCellStyle(style);
         
        header.createCell(2).setCellValue("Last Name");			//<- SEGUN LOCALE XXX
        header.getCell(2).setCellStyle(style);
         
        header.createCell(3).setCellValue("Date of Birth");		//<- SEGUN LOCALE XXX
        header.getCell(3).setCellStyle(style);
         
        // create data rows
        int rowCount = 0;
         
        for (Person aPerson : personList) {
        	rowCount++;
            HSSFRow aRow = sheet.createRow(rowCount);
            aRow.createCell(0).setCellValue(rowCount);
            aRow.createCell(1).setCellValue(aPerson.getFirstName());
            aRow.createCell(2).setCellValue(aPerson.getLastName());
            aRow.createCell(3).setCellValue(aPerson.getDateOfBirth());
        }
		
	}

}
