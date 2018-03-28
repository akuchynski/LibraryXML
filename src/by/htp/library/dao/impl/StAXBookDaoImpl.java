package by.htp.library.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Set;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.htp.library.bean.Book;
import by.htp.library.dao.BookDao;
import by.htp.library.dao.stax.StAXMenuParser;

public class StAXBookDaoImpl implements BookDao {

	Set<Book> books;

	@Override
	public Set<Book> readAll() {

		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		try {
			InputStream input = new FileInputStream("resources/library.xml");

			XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
			Set<Book> books = StAXMenuParser.process(reader);

			return books;
		} catch (XMLStreamException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return books;
	}
}
