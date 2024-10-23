package th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model;


import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
public class QueryAllSplitRuleListInfoDAO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String splitCode;
    private String splitLevel;
    private String splitValue;
    private String includeInd;
    private String splitWay;
    private BigDecimal quantity;
    private String workInd;
    private String exceedInd;
    private String splitDescription;
    private String splitEffectiveDate;
    private String splitExpirationDate;

}
