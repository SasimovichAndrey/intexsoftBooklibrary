package intexsoftBookLibrary.textInterface.resultprinters;

import intexsoftBookLibrary.requestProcessing.RequestAnswer;
import intexsoftBookLibrary.textInterface.exceptions.ResultFormatException;

public interface IResultsPrinter {
	public void print(RequestAnswer answer) throws ResultFormatException;
}
