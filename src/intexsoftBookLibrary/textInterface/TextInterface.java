package intexsoftBookLibrary.textInterface;

import intexsoftBookLibrary.requestProcessing.RequestAnswer;
import intexsoftBookLibrary.requestProcessing.RequestType;
import intexsoftBookLibrary.textInterface.errorprinters.*;
import intexsoftBookLibrary.textInterface.exceptions.ResultFormatException;
import intexsoftBookLibrary.textInterface.resultprinters.FindPrinter;
import intexsoftBookLibrary.textInterface.resultprinters.IResultsPrinter;
import intexsoftBookLibrary.textInterface.resultprinters.OrderPrinter;
import intexsoftBookLibrary.textInterface.resultprinters.ReturnPrinter;

import java.util.HashMap;
import java.util.Scanner;

public class TextInterface {
	private String request;
	private Scanner scanner;
	private static final IResultsPrinter[] printers = {new FindPrinter(), new OrderPrinter(), new ReturnPrinter()};
	private static final IErrorPrinter[] errPrinters = {new RequestTypeErrorPrinter(), new RequestFormatErrorPrinter(), new FatalErrorPrinter(), new DataBaseAccessErrorPrinter()};
	private static final HashMap<RequestType, IResultsPrinter> printersMap = printerMapInit();
	private static final HashMap<ErrorType, IErrorPrinter> errPrintersMap = errPrintersMapInit();
	
	private static HashMap<RequestType, IResultsPrinter> printerMapInit(){
		HashMap<RequestType, IResultsPrinter> map = new HashMap<RequestType, IResultsPrinter>();
		for(int i = 0; i < printers.length; i++){
			map.put(RequestType.values()[i], printers[i]);
		}
		return map;
	}
	
	private static HashMap<ErrorType, IErrorPrinter> errPrintersMapInit() {
		HashMap<ErrorType, IErrorPrinter> map = new HashMap<ErrorType, IErrorPrinter>();
		for(int i = 0; i < errPrinters.length; i++){
			map.put(ErrorType.values()[i], errPrinters[i]);
		}
		return map;
	}

	public String ReadRequest(){
		scanner = new Scanner(System.in);
		request = scanner.nextLine();	
		return request;
	}
	
	public void printReport(RequestAnswer answer){
		try {
			printersMap.get(answer.getReqType()).print(answer);
		} catch (ResultFormatException e) {
			System.out.println("Sorry. Some troubles with output system");
		}
	}
	
	public void printError(ErrorType errorType){
		errPrintersMap.get(errorType).printError();
	}
	
	public void Finish(){
		scanner.close();
	}
}
