package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.model;

import java.io.Serializable;

public class CodeDescriptionInfo implements Serializable {
    protected String code;
    protected String description;

    public CodeDescriptionInfo(String code, String description) {
        super();
        this.code = code;
        this.description = description;
    }

    public CodeDescriptionInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
