package th.truecorp.it.dsm.intatom.billingreferencedatainfo.migrationsystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import th.truecorp.it.dsm.intspringstarter.common.model.RequestInfo;

@Setter
@ToString
@Getter
public class RequestGetMigrationSystemInfo extends RequestInfo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8710249515673635167L;

	@JsonProperty("callerUuid")
	private String callerUuid;

	@JsonProperty("mainKey")
	private String mainKey;

	@JsonProperty("mainValue")
	private String mainValue;

	@JsonProperty("subTypeKey")
	private String subTypeKey;

	@JsonProperty("subTypeValue")
	private String subTypeValue;
}
