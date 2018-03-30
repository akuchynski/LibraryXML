package by.htp.library.dao.stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Set;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.htp.library.bean.Publication;
import by.htp.library.dao.BookDao;

public class StAXBookDaoImpl implements BookDao {

	Set<Publication> publications;

	@Override
	public Set<Publication> readAll() {

		XMLInputFactory inputFactory = XMLInputFactory.newInstance();
		try {
			InputStream input = new FileInputStream("resources/library.xml");

			XMLStreamReader reader = inputFactory.createXMLStreamReader(input);
			Set<Publication> publications = StAXMenuParser.process(reader);

			return publications;
		} catch (XMLStreamException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return publications;
	}
}
