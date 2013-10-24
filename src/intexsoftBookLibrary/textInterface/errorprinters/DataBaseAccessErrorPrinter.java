package intexsoftBookLibrary.textInterface.errorprinters;


public class DataBaseAccessErrorPrinter implements IErrorPrinter {

	@Override
	public void printError() {
		System.out.println("Some troubles with data base. Please check your db-configuration. ");
	}

}
