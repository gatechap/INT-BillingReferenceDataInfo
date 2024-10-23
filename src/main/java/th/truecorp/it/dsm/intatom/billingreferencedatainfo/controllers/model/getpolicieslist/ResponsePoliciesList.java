package th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getpolicieslist;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import th.truecorp.it.dsm.intspringstarter.common.model.ResponseInfo;

@Getter
@Setter
@ToString
public class ResponsePoliciesList extends ResponseInfo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("policiesList")
	private PoliciesList policiesList;

}
