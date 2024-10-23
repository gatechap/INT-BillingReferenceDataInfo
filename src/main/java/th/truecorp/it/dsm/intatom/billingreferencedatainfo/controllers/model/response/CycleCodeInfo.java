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
public class CycleCodeInfo implements Serializable 
{
	private static final long serialVersionUID = -2000277685881956548L;

	@Schema(name = "billPrdUpLimit",description = "Retrieves the defines the upper limit of bill production frequency for all billing arrangements that are related to the cycle code.",example = "12")
	@JsonProperty("billPrdUpLimit")
	private String billPrdUpLimit;

	@Schema(name = "billingReference",description = "Retrieves the an arbitrary past date serving as a reference close day.",example = "1989-01-01T00:00:00.000+07:00")
    @JsonProperty("billingReference")
    private String billingReference;

	@Schema(name = "cycleBillDay",description = "Retrieves the day used for calculating the billing date that appears on the document.",example = "1")
    @JsonProperty("cycleBillDay")
    private String cycleBillDay;

	@Schema(name = "cycleBusinessEntity",description = "Retrieves the cycle business entity.",example = "0")
    @JsonProperty("cycleBusinessEntity")
    private String cycleBusinessEntity;

	@Schema(name = "cycleCloseDay",description = "Retrieves the specific day within the cycle period on which the cycle ends. \r\n" + 
			" This day is indicated by the numbers 1-31 if the cycle frequency is monthly. If the close day is 31, then the cycle end date is the last day of the month, regardless of how many days are in the month. \r\n" + 
			" The day is indicated by the numbers 1-7 if the cycle frequency is weekly.\r\n" + 
			"",example = "18")
    @JsonProperty("cycleCloseDay")
    private String cycleCloseDay;

	@Schema(name = "cycleCode",description = "Retrieves the cycle code.",example = "19")
    @JsonProperty("cycleCode")
    private String cycleCode;

	@Schema(name = "cycleDescription",description = "Retrieves the description of the cycle.",example = "Monthly Cycle close on the 18")
    @JsonProperty("cycleDescription")
    private String cycleDescription;

	@Schema(name = "cycleFrequency",description = "Retrieves the cycle frequency.",example = "M")
    @JsonProperty("cycleFrequency")
    private String cycleFrequency;

    @Schema(name = "cycleFrequencyMultiplier",description = "Retrieves the together with frequency, this defines the cycle period.",example = "1")
    @JsonProperty("cycleFrequencyMultiplier")
    private String cycleFrequencyMultiplier;

    @Schema(name = "cyclePopulationCode",description = "Retrieves the cycle population code used by Amdocs Billing Customer Manager to define the cycle codes which are eligible for different customer types.",example = "A")
    @JsonProperty("cyclePopulationCode")
    private String cyclePopulationCode;

}
