package th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getSplitRuleList;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import th.truecorp.it.dsm.intspringstarter.common.model.ResponseInfo;

@Data
public class ResponseGetSplitRuleList extends ResponseInfo {
    private static final long serialVersionUID = 1L;

    @Schema(name = "splitRuleList", description = "List of splitRuleInfo", required = true)
    public SplitRuleList splitRuleList;
}
