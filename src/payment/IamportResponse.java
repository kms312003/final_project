package payment;

public class IamportResponse<T> {
	
	private int code;
	private String message;
	private T response;
	
	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public T getResponse() {
		return response;
	}
}
