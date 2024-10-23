package th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getpolicieslist;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PoliciesInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("description")
	private String description;
	@JsonProperty("language")
	private String language;
	@JsonProperty("policyId")
	private String policyId;
	@JsonProperty("policyName")
	private String policyName;
	@JsonProperty("policyQualifier")
	private String policyQualifier;
	@JsonProperty("policyType")
	private String policyType;
	@JsonProperty("policyValue")
	private String policyValue;

}
