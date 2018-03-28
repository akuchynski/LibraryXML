package by.htp.library.dao.dom;

import by.htp.library.bean.Author;
import by.htp.library.bean.Book;

import java.util.HashSet;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class BooksDomBuilder {

	private Set<Book> books;
	private DocumentBuilder docBuilder;

	public BooksDomBuilder() {
		this.books = new HashSet<Book>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.err.println("Server configuration error: " + e);
		}
	}

	public Set<Book> getBooks() {

		return books;
	}

	public void buildSetBooks(String fileName) {
		Document doc = null;
		try {
			doc = docBuilder.parse(fileName);
			Element root = doc.getDocumentElement();
			NodeList booksList = root.getElementsByTagName("publication");
			for (int i = 0; i < booksList.getLength(); i++) {
				Element bookElement = (Element) booksList.item(i);
				Book book = buildBook(bookElement);
				books.add(book);
			}
		} catch (IOException e) {
			System.err.println("File error or I/O error: " + e);
		} catch (SAXException e) {
			System.err.println("Parsing failure: " + e);
		}
	}

	private Book buildBook(Element bookElement) {
		Book book = new Book();

		NamedNodeMap attribute = bookElement.getAttributes();
		Integer bookId = Integer.parseInt(attribute.getNamedItem("id").getTextContent());
		book.setId(bookId);
		
		Integer count = Integer.parseInt(getElementTextContent(bookElement, "count"));
		book.setCount(count);

		book.setTitle(getElementTextContent(bookElement, "title"));
		Integer year = Integer.parseInt(getElementTextContent(bookElement, "year"));
		book.setYear(year);

		Element authorElement = (Element) bookElement.getElementsByTagName("author").item(0);
		Author author = new Author();
		Integer authorId = Integer.parseInt(attribute.getNamedItem("id").getTextContent());
		author.setId(authorId);
		author.setName(getElementTextContent(authorElement, "name"));
		author.setSurname(getElementTextContent(authorElement, "surname"));
		book.setAuthor(author);

		return book;
	}

	private static String getElementTextContent(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();

		return text;
	}
}
