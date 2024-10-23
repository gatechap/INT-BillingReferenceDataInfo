package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils.yaml.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Datasource {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
    private Hikari hikari;

}
