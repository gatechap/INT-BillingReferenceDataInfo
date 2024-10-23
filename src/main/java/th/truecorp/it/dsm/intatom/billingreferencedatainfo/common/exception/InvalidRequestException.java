package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.exception;

public class InvalidRequestException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String errorCode;

	private String errorMessage;


	public InvalidRequestException() {
	}

	public InvalidRequestException(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	@Override
	public String toString() {
		return "InvalidRequestException{" + "errorCode='" + errorCode + '\'' + ", errorMessage='" + errorMessage + '\''
				+ '}';
	}
}
