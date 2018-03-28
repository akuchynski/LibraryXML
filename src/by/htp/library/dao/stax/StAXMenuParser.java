package by.htp.library.dao.stax;

import java.util.HashSet;
import java.util.Set;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.htp.library.bean.Author;
import by.htp.library.bean.Book;

public class StAXMenuParser {

	public static Set<Book> process(XMLStreamReader reader) throws XMLStreamException {
		Set<Book> books = new HashSet<Book>();
		Book book = null;
		Author author = null;
		MenuTagName elementName = null;
		while (reader.hasNext()) {
			int type = reader.next();
			
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				elementName = MenuTagName.getElementTagName(reader.getLocalName());
				
				switch (elementName) {
				case PUBLICATION:
					book = new Book();
					Integer id = Integer.parseInt(reader.getAttributeValue(null, "id"));
					book.setId(id);
					break;
				case AUTHOR:
					author = new Author();
					Integer idA = Integer.parseInt(reader.getAttributeValue(null, "id"));
					author.setId(idA);
					break;
				default:
					break;
				}
				break;

			case XMLStreamConstants.CHARACTERS:
				String text = reader.getText().trim();

				if (text.isEmpty()) {
					break;
				}
				switch (elementName) {
				case COUNT:
					Integer count = Integer.parseInt(text);
					book.setCount(count);
					break;
				case TITLE:
					book.setTitle(text);
					break;
				case YEAR:
					Integer year = Integer.parseInt(text);
					book.setYear(year);
					break;
				case NAME:
					author.setName(text);
					break;
				case SURNAME:
					author.setSurname(text);
					break;
				default:
					break;

				}
				break;

			case XMLStreamConstants.END_ELEMENT:
				elementName = MenuTagName.getElementTagName(reader.getLocalName());
				switch (elementName) {

				case PUBLICATION:
					books.add(book);
				case AUTHOR:
					book.setAuthor(author);
				default:
					break;
				}
			}
		}
		return books;
	}
}
