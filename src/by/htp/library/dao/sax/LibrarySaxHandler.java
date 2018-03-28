package by.htp.library.dao.sax;

import by.htp.library.bean.Author;
import by.htp.library.bean.Book;
import java.util.HashSet;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class LibrarySaxHandler extends DefaultHandler {
	private Set<Book> bookList = new HashSet<>();
	private Book book;
	private Author author;
	private StringBuilder text;

	public Set<Book> getBookList() {
		return bookList;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		text = new StringBuilder();

		if (qName.equals("publication")) {
			book = new Book();
			book.setId(Integer.parseInt(attributes.getValue("id")));
		}

		if (qName.equals("author")) {
			author = new Author();
			author.setId(Integer.parseInt(attributes.getValue("id")));
		}
	}

	@Override
	public void characters(char[] buffer, int start, int length) {
		text.append(buffer, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		LibraryTagName tagName = LibraryTagName.valueOf(qName.toUpperCase().replace("-", "_"));
		switch (tagName) {
		case PUBLICATION:
			bookList.add(book);
			book = null;
			break;
		case BOOK:
			break;
		case COUNT:
			book.setCount(Integer.parseInt(text.toString()));
			break;
		case TITLE:
			book.setTitle(text.toString());
			break;
		case YEAR:
			book.setYear(Integer.parseInt(text.toString()));
			break;
		case AUTHOR:
			book.setAuthor(author);
			author = null;
			break;
		case NAME:
			author.setName(text.toString());
			break;
		case SURNAME:
			author.setSurname(text.toString());
			break;
		case AUTHORS:
			break;
		case READING_ROOM:
			break;
		case DATE:
			break;
		case HOME:
			break;
		case PUBLICATIONS:
			break;
		}
	}
	
	@Override
	public void warning(SAXParseException exception) {
		System.err.println("Warning: line " + exception.getLineNumber() + ": " + exception.getMessage());
	}
	
	@Override
	public void error(SAXParseException exception) {
		System.err.println("Error: line " + exception.getLineNumber() + ": " + exception.getMessage());
	}

	@Override
	public void fatalError(SAXParseException exception) throws SAXException {
		System.err.println("Fatal: line " + exception.getLineNumber() + ": " + exception.getMessage());
		throw (exception);
	}
}
