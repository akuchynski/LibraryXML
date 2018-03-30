package by.htp.library.dao.dom;

import java.util.Set;
import by.htp.library.bean.Publication;
import by.htp.library.dao.BookDao;

public class DOMBookDaoImpl implements BookDao {

	Set<Publication> publications;

	@Override
	public Set<Publication> readAll() {

		BooksDomBuilder domBuilder = new BooksDomBuilder();
		domBuilder.buildSetPublications("resources/library.xml");
		publications = domBuilder.getPublications();

		return publications;
	}
}
