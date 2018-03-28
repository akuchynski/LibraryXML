package by.htp.library.dao.stax;

public enum MenuTagName {

	PUBLICATION, COUNT, TITLE, YEAR, AUTHOR, AUTHORS, NAME, SURNAME, BOOK, READING_ROOM, DATE, HOME, PUBLICATIONS;

	public static MenuTagName getElementTagName(String element) {
		switch (element) {
		case "publication":
			return PUBLICATION;
		case "count":
			return COUNT;
		case "title":
			return TITLE;
		case "year":
			return YEAR;
		case "author":
			return AUTHOR;
		case "authors":
			return AUTHORS;
		case "name":
			return NAME;
		case "surname":
			return SURNAME;
		case "book":
			return BOOK;
		case "reading-room":
			return READING_ROOM;
		case "date":
			return DATE;
		case "home":
			return HOME;
		case "publications":
			return PUBLICATIONS;
		default:
			throw new EnumConstantNotPresentException(MenuTagName.class, element);
		}
	}
}
