package intexsoftBookLibrary.textInterface.resultprinters;

import java.util.Date;
import java.util.HashMap;

import intexsoftBookLibrary.requestProcessing.RequestAnswer;
import intexsoftBookLibrary.requestProcessing.processors.AnswerMapKeys;

public class OrderPrinter implements IResultsPrinter{
	public void print(RequestAnswer answer){
		HashMap<String, Object> result = answer.getResultMap();
		if((Boolean)result.get("isFinded")){
			if((Boolean)result.get("isOrdered")){
				System.out.println("OK " + (String)result.get(AnswerMapKeys.ABONENT) + " " + (Date)result.get("date"));
			}
			else{
				System.out.println("RESERVED " + (String)result.get("abonent") + " " + (Date)result.get("date"));
			}
		}
		else{
			System.out.println("NOTFOUND");
		}
	}
}
