package intexsoftBookLibrary.requestProcessing;

import java.util.HashMap;


public class MenuRequest {
	private String  command;
	private HashMap<String, String> params;

	public MenuRequest(String command, HashMap<String, String> params) {
		super();
		this.command = command;
		this.params = params;
	}

	public String getCommand() {
		return command;
	}

	public String getParam(String paramName){
		return params.get(paramName);
	}
	
	public HashMap<String, String> getParams() {
		return params;
	}
}
