package intexsoftBookLibrary.requestProcessing;

import java.util.HashMap;

import intexsoftBookLibrary.requestProcessing.exceprions.RequestFormatException;


public class RequestParser {
	
	public static MenuRequest parse(String stringRequest) throws RequestFormatException{
		
		String splitRequest[] = stringRequest.split(" ");
		splitRequest[0] = splitRequest[0].toUpperCase();
		
		if(splitRequest.length == 0){
				throw new RequestFormatException();
		}
		
		HashMap<String, String> paramMap = new HashMap<String, String>();
		
		for(int i = 1; i < splitRequest.length; i++){
			String[] splitParam = splitRequest[i].split("=");
			if(splitParam.length > 1)
				paramMap.put(splitParam[0], splitParam[1]);
			else
				throw new RequestFormatException();
		}
		
		String reqCommand;
		
		reqCommand = splitRequest[0];
		
		return new MenuRequest(reqCommand, paramMap); 
	}
}
