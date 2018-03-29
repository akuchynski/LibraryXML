package by.htp.library.dao;

import by.htp.library.bean.Publication;

import java.util.Set;

public interface BookDao {

	Set<Publication> readAll();

}
