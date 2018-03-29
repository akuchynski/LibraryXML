package by.htp.library.dao.stax;

import java.util.HashSet;
import java.util.Set;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import by.htp.library.bean.Author;
import by.htp.library.bean.Book;
import by.htp.library.bean.Magazine;
import by.htp.library.bean.Newspaper;
import by.htp.library.bean.Publication;

public class StAXMenuParser {
	
	private StAXMenuParser() {
		throw new IllegalStateException("Utility class");
	}

	public static Set<Publication> process(XMLStreamReader reader) throws XMLStreamException {
		Set<Publication> books = new HashSet<>();
		Publication book = null;
		Author author = null;
		StAXTagName elementName = null;
		int attr = 0;
		while (reader.hasNext()) {
			int type = reader.next();
			
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				elementName = StAXTagName.getElementTagName(reader.getLocalName());
				
				switch (elementName) {
				case PUBLICATION:
					attr = Integer.parseInt(reader.getAttributeValue(null, "id"));
					break;
				case BOOK:
					book = new Book();
					book.setId(attr);
					break;
				case MAGAZINE:
					book = new Magazine();
					book.setId(attr);
					break;
				case NEWSPAPER:
					book = new Newspaper();
					book.setId(attr);
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
				elementName = StAXTagName.getElementTagName(reader.getLocalName());
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
