package th.truecorp.it.dsm.intatom.billingreferencedatainfo.migrationsystem.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@ToString
@Getter
public class ResponseGetMigrationSystemInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7594405156939914507L;

	@JsonProperty("migrationSystem")
	private String migrationSystem;
	@JsonProperty("errorCode")
	private String errorCode;
	@JsonProperty("message")
	private String message;
	@JsonProperty("uuid")
	private String uuid;

}
