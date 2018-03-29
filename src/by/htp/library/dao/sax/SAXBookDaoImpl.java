package by.htp.library.dao.sax;

import java.io.IOException;
import java.util.Set;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.htp.library.bean.Publication;
import by.htp.library.dao.BookDao;

public class SAXBookDaoImpl implements BookDao {
	
	Set<Publication> books;
			
	@Override
	public Set<Publication> readAll() {

		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			LibrarySaxHandler handler = new LibrarySaxHandler();
			reader.setContentHandler(handler);
			reader.parse(new InputSource("resources/library.xml"));
			Set<Publication> books = handler.getBookList();
			return books;
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		return books;
	}
}
