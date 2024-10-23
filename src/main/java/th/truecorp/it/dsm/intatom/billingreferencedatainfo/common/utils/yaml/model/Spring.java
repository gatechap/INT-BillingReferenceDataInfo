package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils.yaml.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Spring {
    private String profiles;
    private Datasource datasource;
    private Datasource secondaryDatasource;
    private EnvironmentCtrl environmentCtrl;

}
