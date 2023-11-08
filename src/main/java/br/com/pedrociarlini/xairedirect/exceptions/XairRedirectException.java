package br.com.pedrociarlini.xairedirect.exceptions;

@SuppressWarnings("serial")
public class XairRedirectException extends RuntimeException {

	public XairRedirectException() {
		super();
	}

	public XairRedirectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public XairRedirectException(String message, Throwable cause) {
		super(message, cause);
	}

	public XairRedirectException(String message) {
		super(message);
	}

	public XairRedirectException(Throwable cause) {
		super(cause);
	}
}
