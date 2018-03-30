package by.htp.library.logic.impl;

import by.htp.library.bean.Catalog;
import by.htp.library.bean.Publication;
import by.htp.library.dao.BookDao;
import by.htp.library.dao.dom.DOMBookDaoImpl;
import by.htp.library.logic.LibraryService;
import by.htp.library.dao.sax.SAXBookDaoImpl;
import by.htp.library.dao.stax.StAXBookDaoImpl;

import java.util.Set;

public class CityLibraryServiceImpl implements LibraryService {

	private BookDao dao1 = new SAXBookDaoImpl();
	private BookDao dao2 = new StAXBookDaoImpl();
	private BookDao dao3 = new DOMBookDaoImpl();

	@Override
	public Catalog getMainCatalog(int n) {

		Catalog catalog = new Catalog();
		Set<Publication> publications;

		switch (n) {
		case 1:
			publications = dao1.readAll();
			catalog.setPublications(publications);
			catalog.setTitle("SAX_PARSED_CATALOG");
			break;
		case 2:
			publications = dao2.readAll();
			catalog.setPublications(publications);
			catalog.setTitle("StAX_PARSED_CATALOG");
			break;
		case 3:
			publications = dao3.readAll();
			catalog.setPublications(publications);
			catalog.setTitle("DOM_PARSED_CATALOG");
			break;
		}

		return catalog;
	}
}
