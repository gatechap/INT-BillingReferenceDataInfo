package th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown=true)
public class ChargeCodeInfo implements Serializable {

    @Schema(name = "chargeCode", description = "charge Code", example = "OS0312")
    private String chargeCode;

    @Schema(name = "chargeCodeDesc", description = "charge Code Description", example = "Outstanding Debt 12/2003")
    private String chargeCodeDesc;

    @Schema(name = "revenueCode", description = "revenue Code", example = "OC")
    private String revenueCode;

    @Schema(name = "groupType", description = "group Type", example = "I")
    private String groupType;

    @Schema(name = "l9BillPresentInd", description = "Bill Present Ind", example = "N")
    private String l9BillPresentInd;

    @Schema(name = "billTh", description = "Bill Thai", example = "12/2003")
    private String billTh;

    @Schema(name = "billEn", description = "Bill English", example = "Outstanding Debt 12/2003")
    private String billEn;

}
