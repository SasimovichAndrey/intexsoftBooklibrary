package intexsoftBookLibrary.textInterface.resultprinters;

import java.util.HashMap;

import intexsoftBookLibrary.requestProcessing.RequestAnswer;
import intexsoftBookLibrary.requestProcessing.processors.AnswerMapKeys;


public class ReturnPrinter implements IResultsPrinter{
	public void print(RequestAnswer answer){
		HashMap<String, Object> results = answer.getResultMap();
		if((Boolean)results.get(AnswerMapKeys.IS_FINDED)){
			if((Boolean)results.get(AnswerMapKeys.IS_RETURNED)){
				System.out.println("OK abonent=" + results.get(AnswerMapKeys.ABONENT));
			}
			else{
				System.out.println("ALREADYRETURNED");
			}
		}
		else{
			System.out.println("NOTFOUND");
		}
	}
}
