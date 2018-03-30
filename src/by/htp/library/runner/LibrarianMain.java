package by.htp.library.runner;

import by.htp.library.bean.Catalog;
import by.htp.library.logic.LibraryService;
import by.htp.library.logic.impl.CityLibraryServiceImpl;

public class LibrarianMain {

	public static void main(String[] args) {

		LibraryService service = new CityLibraryServiceImpl();

		// SAXParser
		Catalog ñatalog1 = service.getMainCatalog(1);
		System.out.println(ñatalog1.getTitle() + ": " + ñatalog1.getPublications());

		// StAXParser
		Catalog ñatalog2 = service.getMainCatalog(2);
		System.out.println(ñatalog2.getTitle() + ": " + ñatalog2.getPublications());

		// DOMParser
		Catalog ñatalog3 = service.getMainCatalog(3);
		System.out.println(ñatalog3.getTitle() + ": " + ñatalog3.getPublications());

	}
}
