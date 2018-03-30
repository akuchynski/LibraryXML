package by.htp.library.dao.dom;

import by.htp.library.bean.Author;
import by.htp.library.bean.Book;
import by.htp.library.bean.Magazine;
import by.htp.library.bean.Newspaper;
import by.htp.library.bean.Publication;

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

	private Set<Publication> publications;
	private DocumentBuilder docBuilder;
	private Publication publication;

	public BooksDomBuilder() {
		this.publications = new HashSet<>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			docBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			System.err.println("Server configuration error: " + e);
		}
	}

	public Set<Publication> getPublications() {
		return publications;
	}

	public void buildSetPublications(String fileName) {
		Document doc = null;
		try {
			doc = docBuilder.parse(fileName);
			Element root = doc.getDocumentElement();
			NodeList publicationsList = root.getElementsByTagName("publication");
			for (int i = 0; i < publicationsList.getLength(); i++) {
				Element publElement = (Element) publicationsList.item(i);
				Publication publication = buildPublication(publElement);
				publications.add(publication);
			}
		} catch (IOException e) {
			System.err.println("File error or I/O error: " + e);
		} catch (SAXException e) {
			System.err.println("Parsing failure: " + e);
		}
	}

	private Publication buildPublication(Element publElement) {
		
		createNewPublication(publElement);

		NamedNodeMap attribute = publElement.getAttributes();
		Integer bookId = Integer.parseInt(attribute.getNamedItem("id").getTextContent());
		publication.setId(bookId);
		
		Integer count = Integer.parseInt(getElementTextContent(publElement, "count"));
		publication.setCount(count);

		publication.setTitle(getElementTextContent(publElement, "title"));
		Integer year = Integer.parseInt(getElementTextContent(publElement, "year"));
		publication.setYear(year);

		Element authorElement = (Element) publElement.getElementsByTagName("author").item(0);
		Author author = new Author();
		Integer authorId = Integer.parseInt(attribute.getNamedItem("id").getTextContent());
		author.setId(authorId);
		author.setName(getElementTextContent(authorElement, "name"));
		author.setSurname(getElementTextContent(authorElement, "surname"));
		publication.setAuthor(author);

		return publication;
	}

	private static String getElementTextContent(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();

		return text;
	}
	
	private void createNewPublication(Element bookElement) {
		String publicationType = bookElement.getChildNodes().item(1).getNodeName();
		switch (publicationType) {
		case "book":
			publication = new Book();
			break;
		case "magazine":
			publication = new Magazine();
			break;
		case "newspaper":
			publication = new Newspaper();
			break;
		}
	}
}
