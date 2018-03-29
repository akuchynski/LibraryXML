package by.htp.library.dao.dom;

import java.util.Set;
import by.htp.library.bean.Publication;
import by.htp.library.dao.BookDao;

public class DOMBookDaoImpl implements BookDao {

	Set<Publication> books;

	@Override
	public Set<Publication> readAll() {

		BooksDomBuilder domBuilder = new BooksDomBuilder();
		domBuilder.buildSetBooks("resources/library.xml");
		books = domBuilder.getBooks();

		return books;
	}
}
