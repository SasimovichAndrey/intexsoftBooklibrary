package intexsoftBookLibrary.requestProcessing.processors;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

import intexsoftBookLibrary.dao.DAOFactory;
import intexsoftBookLibrary.dao.DbType;
import intexsoftBookLibrary.dao.IBookDAO;
import intexsoftBookLibrary.dao.mysql.exceptions.DAOException;
import intexsoftBookLibrary.library.*;
import intexsoftBookLibrary.requestProcessing.MenuRequest;
import intexsoftBookLibrary.requestProcessing.RequestAnswer;
import intexsoftBookLibrary.requestProcessing.RequestType;
import intexsoftBookLibrary.requestProcessing.exceprions.DataBaseAccessException;
import intexsoftBookLibrary.requestProcessing.exceprions.RequestFormatException;

public class OrderProcessor implements IRequestProcessor{
	public RequestAnswer process(MenuRequest request)  throws RequestFormatException, DataBaseAccessException{
		try{
			RequestAnswer answer = new RequestAnswer();
			HashMap<String, Object> results = new HashMap<String, Object>();
			
			answer.setReqType(RequestType.ORDER);
			
			Integer bookID;
			bookID = Integer.parseInt(request.getParam("id")); // ID искомой книги
			String abonent = request.getParam("abonent"); // им€ абонента, запрашивающего книгу
			if(abonent==null)
				throw new RequestFormatException("");
			
			DAOFactory sqlDAOFactory = DAOFactory.getFactory(DbType.MYSQL);
			IBookDAO bookDAO = sqlDAOFactory.getBookDAO();
		
			Book reqBook = bookDAO.getBookById(bookID);
			
			// ≈сли ID найден в указателе
			if(reqBook != null){
				results.put(AnswerMapKeys.IS_FINDED, Boolean.TRUE);
				results.put(AnswerMapKeys.ABONENT, reqBook.getReader());
				results.put(AnswerMapKeys.IS_ORDERED, new Boolean(reqBook.getReader() == null));
				if((Boolean)results.get(AnswerMapKeys.IS_ORDERED)){
					reqBook.setReader(abonent);
					reqBook.setIssueDate(new Timestamp(new Date().getTime()));
					results.put(AnswerMapKeys.ABONENT, abonent);
					bookDAO.update(reqBook);
				}
			}
			// ≈сли ID не найден в указателе
			else{
				results.put(AnswerMapKeys.IS_FINDED, new Boolean(true));
			}
			results.put(AnswerMapKeys.DATE, new Timestamp(new Date().getTime()));
			
			answer.setResultMap(results);
			
			return answer;
		}
		catch (DAOException e) {
			e.printStackTrace();
			throw new DataBaseAccessException();
		}
		catch (NumberFormatException e) {
			throw new RequestFormatException("Invalid id:" + request.getParam("id"), e);
		}
	}
}
