package th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class GetCycleControlListRequest implements Serializable {

    @Schema(name = "correlatedId", description = "correlated Id", example = "c2e16734-077a-417c-aef3-d8707dc6250e")
    private String correlatedId;

    @Schema(name = "cycleMonth", description = "cycle Month", example = "2")
    private int cycleMonth;

    @Schema(name = "cycleYear", description = "cycle Year", example = "2021")
    private int cycleYear;

}
