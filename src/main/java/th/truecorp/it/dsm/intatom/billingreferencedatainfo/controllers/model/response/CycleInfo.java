package th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class CycleInfo implements Serializable 
{
	private static final long serialVersionUID = -5418031859470591353L;

	@Schema(name = "cycleCode",description = "Retrieves the billing cycle information.",example = "")
	@JsonProperty("cycleCode")
    private CycleCodeInfo cycleCode;

	@Schema(name = "cycleInstance",description = "Retrieves the identifies the serial occurrence of the cycle instance in a calendar year. The cycle instance has the following interpretations according to the cycle frequency.",example = "10")
    @JsonProperty("cycleInstance")
    private String cycleInstance;

	@Schema(name = "cycleSeqNo",description = "Retrieves the unique identifier of the cycle.",example = "123")
    @JsonProperty("cycleSeqNo")
    private String cycleSeqNo;

	@Schema(name = "cycleYear",description = "Retrieves the year of the cycle end date.",example = "2020")
    @JsonProperty("cycleYear")
    private String cycleYear;

	@Schema(name = "endDate",description = "Retrieves the t Ending date of the cycle.",example = "2018-03-15T00:00:00.000+07:00")
    @JsonProperty("endDate")
    private String endDate;

	@Schema(name = "runDate",description = "Retrieves the date the billing run started for that cycle.",example = "2018-03-15T00:00:00.000+07:00")
    @JsonProperty("runDate")
    private String runDate;

	@Schema(name = "startDate",description = "Retrieves the starting date of the cycle.",example = "2018-03-15T00:00:00.000+07:00")
    @JsonProperty("startDate")
    private String startDate;

	@Schema(name = "status",description = "Retrieves the cycle status.",example = "OP")
    @JsonProperty("status")
    private String status;

}
