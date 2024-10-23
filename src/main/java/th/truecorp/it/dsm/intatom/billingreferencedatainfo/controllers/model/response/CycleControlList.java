package th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CycleControlList {

    public CycleControlList(){

    }

    @JsonProperty("cycleInfoArray")
    public List<CycleInfo> cycleInfoArray;

    @JsonProperty("size")
    public int size;
}
