package intexsoftBookLibrary.dao;

public abstract class DAOFactory {
	public abstract IBookDAO getBookDAO();
	public abstract ILibraryDAO getLibraryDAO();
	
	public static DAOFactory getFactory(DbType dbType){
		switch (dbType) {
		case MYSQL:
			return new MySqlDAOFactory();
		}
		return null;
	}
}
