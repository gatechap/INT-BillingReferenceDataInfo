package th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PoliciesListByPolicyTypeInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;
	private String language;
	private String policyId;
	private String policyName;
	private String policyQualifier;
	private String policyType;
	private String policyValue;
}
