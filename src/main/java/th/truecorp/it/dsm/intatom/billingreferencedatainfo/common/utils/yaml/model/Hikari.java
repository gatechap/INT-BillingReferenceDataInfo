package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils.yaml.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Hikari {
    private int maximumPoolSize;
    private int connectionTimeout;
    private int idleTimeout;
    private int readTimeout;
}
