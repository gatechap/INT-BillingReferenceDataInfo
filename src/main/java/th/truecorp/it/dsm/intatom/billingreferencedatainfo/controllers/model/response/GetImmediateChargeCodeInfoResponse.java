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
public class GetImmediateChargeCodeInfoResponse extends ResponseInfo implements Serializable {

    private ChargeCodeInfo chargeCodeInfo;

}
