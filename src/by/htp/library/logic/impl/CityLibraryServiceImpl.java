package by.htp.library.logic.impl;

import by.htp.library.bean.Book;
import by.htp.library.bean.Catalog;
import by.htp.library.dao.BookDao;
import by.htp.library.logic.LibraryService;
import by.htp.library.dao.impl.DOMBookDaoImpl;
import by.htp.library.dao.impl.SAXBookDaoImpl;
import by.htp.library.dao.impl.StAXBookDaoImpl;

import java.util.Set;

public class CityLibraryServiceImpl implements LibraryService {

	private BookDao dao1 = new SAXBookDaoImpl();
	private BookDao dao2 = new StAXBookDaoImpl();
	private BookDao dao3 = new DOMBookDaoImpl();

	@Override
	public Catalog getMainCatalog(int n) {

		Catalog catalog = new Catalog();
		Set<Book> books;

		switch (n) {
		case 1:
			books = dao1.readAll();
			catalog.setBooks(books);
			catalog.setTitle("SAX_BOOK_CATALOG");
			break;
		case 2:
			books = dao2.readAll();
			catalog.setBooks(books);
			catalog.setTitle("StAX_BOOK_CATALOG");
			break;
		case 3:
			books = dao3.readAll();
			catalog.setBooks(books);
			catalog.setTitle("DOM_BOOK_CATALOG");
			break;
		}

		return catalog;
	}

	public void getMainCatalog() {

	}
}
