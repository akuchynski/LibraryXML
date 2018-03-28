package by.htp.library.dao.impl;

import java.io.IOException;
import java.util.Set;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import by.htp.library.bean.Book;
import by.htp.library.dao.BookDao;
import by.htp.library.dao.sax.LibrarySaxHandler;

public class SAXBookDaoImpl implements BookDao {
	
	Set<Book> books;
			
	@Override
	public Set<Book> readAll() {

		try {
			XMLReader reader = XMLReaderFactory.createXMLReader();
			LibrarySaxHandler handler = new LibrarySaxHandler();
			reader.setContentHandler(handler);
			reader.parse(new InputSource("resources/library.xml"));
			Set<Book> books = handler.getBookList();
			return books;
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		return books;
	}
}
