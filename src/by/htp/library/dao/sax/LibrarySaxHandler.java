package by.htp.library.dao.sax;

import by.htp.library.bean.Author;
import by.htp.library.bean.Book;
import by.htp.library.bean.Magazine;
import by.htp.library.bean.Newspaper;
import by.htp.library.bean.Publication;

import java.util.HashSet;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class LibrarySaxHandler extends DefaultHandler {
	private Set<Publication> bookList = new HashSet<>();
	private Publication book;
	private Author author;
	private StringBuilder text;
	private int attr;

	public Set<Publication> getBookList() {
		return bookList;
	}
	
	public int getAttr() {
		return attr;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		text = new StringBuilder();
		switch (qName) {
		case "publication":
			attr = Integer.parseInt(attributes.getValue("id"));
			break;
		case "author":
			author = new Author();
			author.setId(Integer.parseInt(attributes.getValue("id")));
			break;
		case "book":
			book = new Book();
			book.setId(attr);
			break;
		case "magazine":
			book = new Magazine();
			book.setId(attr);
			break;
		case "newspaper":
			book = new Newspaper();
			book.setId(attr);
			break;
		}
	}

	@Override
	public void characters(char[] buffer, int start, int length) {
		text.append(buffer, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		SAXTagName tagName = SAXTagName.valueOf(qName.toUpperCase().replace("-", "_"));
		switch (tagName) {
		case PUBLICATION:
			bookList.add(book);
			book = null;
			break;
		case BOOK:
			break;
		case MAGAZINE:
			break;
		case NEWSPAPER:
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
