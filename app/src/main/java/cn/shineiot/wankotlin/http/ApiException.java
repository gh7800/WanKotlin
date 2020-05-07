package cn.shineiot.wankotlin.http;

/**
 * @author GF63
 */
public class ApiException extends RuntimeException{

	private String errorCode;

	public ApiException(String code, String msg) {
		super(msg);
		this.errorCode = code;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
