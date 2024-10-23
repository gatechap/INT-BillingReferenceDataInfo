package th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class CycleControlInfoDAO implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Short billPrdUpLimit;
    private String billingReference;
    private Short cycleBillDay;
    private Integer cycleBusinessEntity;
    private Short cycleCloseDay;
    private Short cycleCode;
    private String cycleDescription;
    private String cycleFrequency;
    private Short cycleFrequencyMultiplier;
    private String cyclePopulationCode;
    private Short cycleInstance;
    private Integer cycleSeqNo;
    private Short cycleYear;
    private String endDate;
    private String runDate;
    private String startDate;
    private String status;
}
