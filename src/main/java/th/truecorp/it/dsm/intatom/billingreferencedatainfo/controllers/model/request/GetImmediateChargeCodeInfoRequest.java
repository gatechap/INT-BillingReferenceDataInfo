package th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class GetImmediateChargeCodeInfoRequest implements Serializable {

    @Schema(name = "chargeCode", description = "charge Code", example = "OS0312")
    private String chargeCode;

}
