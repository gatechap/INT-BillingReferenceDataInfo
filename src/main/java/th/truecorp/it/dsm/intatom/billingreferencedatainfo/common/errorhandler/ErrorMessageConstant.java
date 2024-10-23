package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.errorhandler;

public enum ErrorMessageConstant 
{
	NONE(""),
	SUCCESS("Success."),
	DATA_NOT_FOUND ("Data Not Found."),
	PAREMETER_IS_REQUIRED ("Parameter is required."),
	PARAMETER_IS_INVALID ("Parameter is invalid."),
	CAN_NOT_GREATER_THAN ("{0} can not greater than {1}."),
	CAN_NOT_LESS_THAN("{0} can not less than {0}."),
	DB_TIMEOUT ("DB is time-out {0}."),
	INTERNAL_FAILURE ("Internal Failure {0}."),
	BACKEND_ERROR ("{0} is error."),
	BACKEND_TIMEOUT ("{0} is time-out.");
	
	private final String name;
	
	private ErrorMessageConstant(String name) {
		this.name = name;
	}
	
	public boolean equalsName(String comparedName) 
	{
        // (comparedName == null) check is not needed because name.equals(null) returns false 
        return name.equals(comparedName);
    }
	
	public String toString() 
	{
		return this.name;
	}
} // end enum
