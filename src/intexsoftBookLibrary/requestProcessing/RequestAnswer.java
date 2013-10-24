package intexsoftBookLibrary.requestProcessing;

import java.util.HashMap;

public class RequestAnswer {
	private HashMap<String, Object> resultMap;
	private RequestType reqType;

	//Getters and setters
	public HashMap<String, Object> getResultMap() {
		return resultMap;
	}
	public void setResultMap(HashMap<String, Object> resultMap) {
		this.resultMap = resultMap;
	}
	public RequestType getReqType() {
		return reqType;
	}
	public void setReqType(RequestType reqType) {
		this.reqType = reqType;
	}
}