package th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class ChargeCodeInfoDAO implements Serializable {

    private String chargeCode;
    private String chargeCodeDesc;
    private String revenueCode;
    private String groupType;
    private String l9BillPresentInd;
    private String billTh;
    private String billEn;

}
