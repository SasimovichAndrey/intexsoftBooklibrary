package intexsoftBookLibrary.requestProcessing.processors;

import intexsoftBookLibrary.requestProcessing.MenuRequest;
import intexsoftBookLibrary.requestProcessing.RequestAnswer;
import intexsoftBookLibrary.requestProcessing.exceprions.DataBaseAccessException;
import intexsoftBookLibrary.requestProcessing.exceprions.RequestFormatException;

public interface IRequestProcessor {
	public RequestAnswer process(MenuRequest request) throws RequestFormatException, DataBaseAccessException;
}
