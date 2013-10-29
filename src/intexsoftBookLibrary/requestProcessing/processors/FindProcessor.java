package intexsoftBookLibrary.requestProcessing.processors;

import intexsoftBookLibrary.dao.DAOFactory;
import intexsoftBookLibrary.dao.DbType;
import intexsoftBookLibrary.dao.IBookDAO;
import intexsoftBookLibrary.dao.mysql.exceptions.DAOException;
import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.requestProcessing.MenuRequest;
import intexsoftBookLibrary.requestProcessing.RequestAnswer;
import intexsoftBookLibrary.requestProcessing.RequestType;
import intexsoftBookLibrary.requestProcessing.exceprions.DataBaseAccessException;
import intexsoftBookLibrary.requestProcessing.exceprions.RequestFormatException;

import java.util.HashMap;
import java.util.List;

public class FindProcessor implements IRequestProcessor{
	public RequestAnswer process(MenuRequest request) throws RequestFormatException, DataBaseAccessException{
		RequestAnswer answer = new RequestAnswer();
		answer.setReqType(RequestType.FIND);
		HashMap<String, Object> results = new HashMap<String, Object>();
		
		DAOFactory sqlDaoFactory = DAOFactory.getFactory(DbType.MYSQL);
		IBookDAO bookDAO = sqlDaoFactory.getBookDAO();
		
		Book paramBook = new Book();
		if(request.getParam("author") != null){
			paramBook.setAuthor(request.getParam("author"));
		}
		if(request.getParam("name") != null){
			paramBook.setAuthor(request.getParam("name"));
		}
		
		try{
			List<Book> findedBooks = bookDAO.read(paramBook);
			
			results.put(AnswerMapKeys.FINDED_BOOKS, findedBooks);
			answer.setResultMap(results);
			
			return answer;
		}
		catch(DAOException e){
			throw new DataBaseAccessException(e);
		}
	}
}
