package intexsoftBookLibrary.requestProcessing.processors;


import intexsoftBookLibrary.requestProcessing.MenuRequest;
import intexsoftBookLibrary.requestProcessing.RequestAnswer;
import intexsoftBookLibrary.requestProcessing.RequestType;
import intexsoftBookLibrary.requestProcessing.exceprions.RequestFormatException;

public class ExitProcessor implements IRequestProcessor {

	@Override
	public RequestAnswer process(MenuRequest params) throws RequestFormatException {
		RequestAnswer answer = new RequestAnswer();
		answer.setReqType(RequestType.EXIT);
		return answer;
	}

}
