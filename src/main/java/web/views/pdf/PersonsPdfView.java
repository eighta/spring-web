package web.views.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

import a8.data.Person;

public class PersonsPdfView extends AbstractPdfView{

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {


		// get data model which is passed by the Spring container
        List<Person> personList = (List<Person>) model.get("persons");
		
		Table table = new Table(4);
		table.addCell("Count");		//<- SEGUN LOCALE XXX
		table.addCell("First Name"); //<- SEGUN LOCALE XXX
		table.addCell("Last Name"); //<- SEGUN LOCALE XXX
		table.addCell("Date of Birth"); //<- SEGUN LOCALE XXX
		
		int rowCount = 0;
		for (Person aPerson : personList) {
			rowCount++;
			
			table.addCell(String.valueOf(rowCount));
			table.addCell(aPerson.getFirstName());
			table.addCell(aPerson.getLastName());
			table.addCell(String.valueOf(aPerson.getDateOfBirth() ) );
		}

		document.add(table);
		
		
	}

}
