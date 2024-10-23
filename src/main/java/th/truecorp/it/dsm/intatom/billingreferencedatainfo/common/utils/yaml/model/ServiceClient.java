package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils.yaml.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ServiceClient {
	 private String name;
	 private String endpoint;
	 private String system;
	 private String user;
	 private String password;
	 private int connectionTimeout;
	 private int readTimeout;
	 private String appuser;
	 private String apppassword;
}
