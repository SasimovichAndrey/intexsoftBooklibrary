package intexsoftBookLibrary.textInterface.resultprinters;

import intexsoftBookLibrary.library.Book;
import intexsoftBookLibrary.requestProcessing.RequestAnswer;
import intexsoftBookLibrary.requestProcessing.processors.AnswerMapKeys;
import intexsoftBookLibrary.textInterface.exceptions.ResultFormatException;

import java.util.HashMap;
import java.util.List;

public class FindPrinter implements IResultsPrinter{
	private static final String FOUND_STRING = "FOUND";
	private static final String FOUND_MISSING_STRING = "FOUNDMISSING";
	private static final String NOT_FOUND_STRING = "NOTFOUND";
	
	public void print(RequestAnswer answer) throws ResultFormatException{
		HashMap<String, Object> results = answer.getResultMap();
		if(results.containsKey(AnswerMapKeys.FINDED_BOOKS)){
			List<Book> findedBooks = (List<Book>)results.get(AnswerMapKeys.FINDED_BOOKS);
			
			System.out.println("Search result: \n");
			
			if(findedBooks.size() != 0)
				for(int i = 0; i < findedBooks.size(); i++){
					if(findedBooks.get(i).getReader() != null){
						System.out.print(FOUND_MISSING_STRING + " ");
					}
					else{
						System.out.print(FOUND_STRING + " ");
					}
					System.out.print("index=" + findedBooks.get(i).getID() + " " + findedBooks.get(i).getLibrary().getName() + " ");
					if(findedBooks.get(i).getReader() != null){
						System.out.print("issued=" + findedBooks.get(i).getIssueDate() + " ");
					}
					System.out.println();
				}
			else{
				System.out.println(NOT_FOUND_STRING);
			}
		}
		else
			throw new ResultFormatException();
	}
}
