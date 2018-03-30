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
	private Set<Publication> publicationList = new HashSet<>();
	private Publication publication;
	private Author author;
	private StringBuilder text;
	private int id;

	public Set<Publication> getPublicationList() {
		return publicationList;
	}

	public int getAttr() {
		return id;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		text = new StringBuilder();
		switch (qName) {
		case "publication":
			id = Integer.parseInt(attributes.getValue("id"));
			break;
		case "author":
			author = new Author();
			author.setId(Integer.parseInt(attributes.getValue("id")));
			break;
		case "book":
			publication = new Book();
			publication.setId(id);
			break;
		case "magazine":
			publication = new Magazine();
			publication.setId(id);
			break;
		case "newspaper":
			publication = new Newspaper();
			publication.setId(id);
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
			publicationList.add(publication);
			publication = null;
			break;
		case BOOK:
			break;
		case MAGAZINE:
			break;
		case NEWSPAPER:
			break;
		case COUNT:
			publication.setCount(Integer.parseInt(text.toString()));
			break;
		case TITLE:
			publication.setTitle(text.toString());
			break;
		case YEAR:
			publication.setYear(Integer.parseInt(text.toString()));
			break;
		case AUTHOR:
			publication.setAuthor(author);
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
