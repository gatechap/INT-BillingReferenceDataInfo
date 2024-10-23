package th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getSplitRuleList;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import th.truecorp.it.dsm.intspringstarter.common.model.RequestInfo;

@Data
public class RequestGetSplitRuleList extends RequestInfo {
    private static final long serialVersionUID = 1L;

    @Schema(name = "splitCode", description = "split Code", example = "UTC_UALL01")
    private String splitCode;
}
