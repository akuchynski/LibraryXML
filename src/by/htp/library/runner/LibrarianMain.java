package by.htp.library.runner;

import by.htp.library.bean.Catalog;
import by.htp.library.logic.LibraryService;
import by.htp.library.logic.impl.CityLibraryServiceImpl;

public class LibrarianMain {

	public static void main(String[] args) {

		LibraryService service = new CityLibraryServiceImpl();

		// SAXParser
		Catalog booksCatalog1 = service.getMainCatalog(1);
		System.out.println(booksCatalog1.getTitle() + ": " + booksCatalog1.getBooks());

		// StAXParser
		Catalog booksCatalog2 = service.getMainCatalog(2);
		System.out.println(booksCatalog2.getTitle() + ": " + booksCatalog2.getBooks());

		// DOMParser
		Catalog booksCatalog3 = service.getMainCatalog(3);
		System.out.println(booksCatalog3.getTitle() + ": " + booksCatalog3.getBooks());

	}
}
