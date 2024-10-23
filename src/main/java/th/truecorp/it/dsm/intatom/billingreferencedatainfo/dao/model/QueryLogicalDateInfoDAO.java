package th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class QueryLogicalDateInfoDAO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String logicalDate;
}
