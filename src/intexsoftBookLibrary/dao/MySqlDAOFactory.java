package intexsoftBookLibrary.dao;

import intexsoftBookLibrary.dao.mysql.MySqlBookDAO;
import intexsoftBookLibrary.dao.mysql.MySqlLibraryDAO;

public class MySqlDAOFactory extends DAOFactory {
	@Override
	public IBookDAO getBookDAO() {
		return new MySqlBookDAO();
	}

	@Override
	public ILibraryDAO getLibraryDAO() {
		return new MySqlLibraryDAO();
	}

}
