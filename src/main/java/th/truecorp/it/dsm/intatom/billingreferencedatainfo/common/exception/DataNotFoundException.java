package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.exception;

public class DataNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String errorMessage;

	public DataNotFoundException() {
	}

	public DataNotFoundException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "DataNotFoundException [errorMessage=" + errorMessage + "]";
	}

}
