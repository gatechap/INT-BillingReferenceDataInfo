package th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetCycleControlListResponse extends ResponseInfo implements Serializable {

    @JsonProperty("cycleControlList")
    private CycleControlList cycleControlList;

}
