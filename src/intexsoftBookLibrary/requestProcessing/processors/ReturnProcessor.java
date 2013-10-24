package intexsoftBookLibrary.requestProcessing.processors;

import java.util.HashMap;

import intexsoftBookLibrary.dao.DAOFactory;
import intexsoftBookLibrary.dao.DbType;
import intexsoftBookLibrary.dao.IBookDAO;
import intexsoftBookLibrary.dao.mysql.exceptions.BookNotFoundException;
import intexsoftBookLibrary.dao.mysql.exceptions.DAOException;
import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.requestProcessing.MenuRequest;
import intexsoftBookLibrary.requestProcessing.RequestAnswer;
import intexsoftBookLibrary.requestProcessing.RequestType;
import intexsoftBookLibrary.requestProcessing.exceprions.DataBaseAccessException;
import intexsoftBookLibrary.requestProcessing.exceprions.RequestFormatException;

public class ReturnProcessor implements IRequestProcessor{
	public RequestAnswer process(MenuRequest request) throws RequestFormatException, DataBaseAccessException{
		
		RequestAnswer answer = new RequestAnswer();
		answer.setReqType(RequestType.RETURN);
		HashMap<String, Object> results = new HashMap<String, Object>();
		
		Integer bookID;
		try{
			bookID = Integer.parseInt(request.getParam("id")); // ID искомой книги
		}
		catch(NumberFormatException e){
			throw new RequestFormatException();
		}
		
		DAOFactory sqlDaoFactory = DAOFactory.getFactory(DbType.MYSQL);
		IBookDAO bookDAO = sqlDaoFactory.getBookDAO();
		Book targetBook = null;
		
		try {
			targetBook = bookDAO.getBookById(bookID);
		} catch (DAOException e) {
			e.printStackTrace();
			throw new DataBaseAccessException();
		}
		
		if(targetBook != null){
			results.put(AnswerMapKeys.IS_FINDED, true);
			if(targetBook.getReader() == null && targetBook.getIssueDate() == null){
				results.put(AnswerMapKeys.IS_RETURNED, false);
			}
			else{
				results.put(AnswerMapKeys.IS_RETURNED, true);
				results.put(AnswerMapKeys.ABONENT, targetBook.getReader());
				targetBook.setReader(null);
				targetBook.setIssueDate(null);
				try {
					bookDAO.update(targetBook);
				} catch (DAOException e) {
					e.printStackTrace();
					throw new DataBaseAccessException();
				} catch (BookNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		else{
			results.put(AnswerMapKeys.IS_FINDED, false);
		}
		
		answer.setResultMap(results);
		
		return answer;
	}

}
