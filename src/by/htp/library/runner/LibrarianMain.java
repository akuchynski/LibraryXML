package by.htp.library.runner;

import by.htp.library.bean.Catalog;
import by.htp.library.logic.LibraryService;
import by.htp.library.logic.impl.CityLibraryServiceImpl;

public class LibrarianMain {

	public static void main(String[] args) {

		LibraryService service = new CityLibraryServiceImpl();

		// SAXParser
		Catalog �atalog1 = service.getMainCatalog(1);
		System.out.println(�atalog1.getTitle() + ": " + �atalog1.getPublications());

		// StAXParser
		Catalog �atalog2 = service.getMainCatalog(2);
		System.out.println(�atalog2.getTitle() + ": " + �atalog2.getPublications());

		// DOMParser
		Catalog �atalog3 = service.getMainCatalog(3);
		System.out.println(�atalog3.getTitle() + ": " + �atalog3.getPublications());

	}
}
