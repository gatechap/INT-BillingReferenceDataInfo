package th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getpolicieslist;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PoliciesList implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("size")
	private int size;
	@JsonProperty("policiesInfoArray")
	private List<PoliciesInfo> policiesInfoArray;


}
