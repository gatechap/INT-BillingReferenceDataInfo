package th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ResponseInfo implements Serializable {

    protected String uuid;
    protected String errorCode;
    protected String message;

    @Schema(name = "uuid", description = "Transaction ID.", example = "c2e16734-077a-417c-aef3-d8707dc6250e")
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Schema(name = "errorCode", description = "Error Code.", example = "OSBbllngA00001")
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @Schema(name = "message", description = "Error Message.", example = "Success")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
