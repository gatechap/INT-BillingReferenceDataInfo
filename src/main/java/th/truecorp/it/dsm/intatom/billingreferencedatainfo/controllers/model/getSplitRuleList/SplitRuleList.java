package th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getSplitRuleList;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class SplitRuleList implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(name = "size", description = "Size of list", example = "2", required = true)
    public int size;

    @Schema(name = "splitRuleInfoArray", description = "Array or list of info", example = "", required = true)
    public List<SplitRuleInfoArray> splitRuleInfoArray;
}
