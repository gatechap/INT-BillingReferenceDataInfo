package th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getSplitRuleList;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class SplitRuleInfoArray implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(name = "exceedInd", description = "exceedInd", example = "N", required = true)
    public String exceedInd;

    @Schema(name = "includeInd", description = "includeInd", example = "Y", required = true)
    public String includeInd;

    @Schema(name = "quantity", description = "quantity", example = "100.00", required = true)
    public BigDecimal quantity;

    @Schema(name = "splitCode", description = "split Code", example = "UTC_UALL01", required = true)
    public String splitCode;

    @Schema(name = "splitDescription", description = "splitDescription", example = "100% for All Charge", required = true)
    public String splitDescription;

    @Schema(name = "splitEffectiveDate", description = "splitEffectiveDate", example = "2001-01-02T00:00:00.000+07:00", required = true)
    public String splitEffectiveDate;

    @Schema(name = "splitExpirationDate", description = "splitExpirationDate", example = "null", required = true)
    public String splitExpirationDate;

    @Schema(name = "splitLevel", description = "splitLevel", example = "R", required = true)
    public String splitLevel;

    @Schema(name = "splitValue", description = "splitValue", example = "OC", required = true)
    public String splitValue;

    @Schema(name = "splitWay", description = "splitWay", example = "P", required = true)
    public String splitWay;

    @Schema(name = "workInd", description = "workInd", example = "N", required = true)
    public String workInd;
}
