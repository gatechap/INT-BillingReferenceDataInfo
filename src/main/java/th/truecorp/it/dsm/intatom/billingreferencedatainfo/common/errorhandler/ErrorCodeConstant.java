package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.errorhandler;

public enum ErrorCodeConstant 
{
	NONE(""),
	SUCCESS ("INT00001"),
	DATA_NOT_FOUND ("INT10001"),
	PAREMETER_IS_REQUIRED ("INT20011"),
	PARAMETER_IS_INVALID ("INT20021"),
	CAN_NOT_GREATER_THAN ("INT40011"),
	CAN_NOT_LESS_THAN ("INT40021"),
	DB_TIMEOUT ("INT50001"),
	INTERNAL_FAILURE("INT60001"),
	BACKEND_ERROR ("INT70001"),
	BACKEND_TIMEOUT ("INT70011");
	
	private final String name;
	
	private ErrorCodeConstant(String name) {
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
	
} // end class
