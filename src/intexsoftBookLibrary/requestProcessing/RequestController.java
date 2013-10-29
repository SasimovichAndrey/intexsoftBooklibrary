package intexsoftBookLibrary.requestProcessing;

import java.sql.SQLException;
import java.util.HashMap;

import intexsoftBookLibrary.requestProcessing.exceprions.DataBaseAccessException;
import intexsoftBookLibrary.requestProcessing.exceprions.RequestFormatException;
import intexsoftBookLibrary.requestProcessing.exceprions.UnknownRequestTypeException;
import intexsoftBookLibrary.requestProcessing.processors.ExitProcessor;
import intexsoftBookLibrary.requestProcessing.processors.FindProcessor;
import intexsoftBookLibrary.requestProcessing.processors.IRequestProcessor;
import intexsoftBookLibrary.requestProcessing.processors.OrderProcessor;
import intexsoftBookLibrary.requestProcessing.processors.ReturnProcessor;

public class RequestController {
	
	private static final String FIND_STRING = "FIND";
	private static final String ORDER_STRING = "ORDER";
	private static final String RETURN_STRING = "RETURN";
	private static final String EXIT_STRING = "EXIT";
	
	private static final String [] commandStrings = {FIND_STRING, ORDER_STRING, RETURN_STRING, EXIT_STRING};
	private static final IRequestProcessor[] processors = {new FindProcessor(), new OrderProcessor(), new ReturnProcessor(), new ExitProcessor()};
	
	private HashMap<String, IRequestProcessor> processorMap;
	
	
	public RequestController() throws SQLException, ClassNotFoundException{
		processorMap = new HashMap<>();
		for(int i = 0; i < commandStrings.length; i++)
			processorMap.put(commandStrings[i], processors[i]);
	}
	
	public RequestAnswer processRequest(MenuRequest request) throws UnknownRequestTypeException, RequestFormatException, DataBaseAccessException{
		if(processorMap.containsKey(request.getCommand())){
			return processorMap.get(request.getCommand()).process(request);
		}
		else
			throw new UnknownRequestTypeException("Invalid request type: " + request.getCommand());
	}
}
