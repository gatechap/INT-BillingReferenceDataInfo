package th.truecorp.it.dsm.intatom.billingreferencedatainfo.mockup;

import com.fasterxml.jackson.databind.ObjectMapper;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.response.GetImmediateChargeCodeInfoResponse;

public class MockupBillingReferenceDataInfoResponse {


    public static GetImmediateChargeCodeInfoResponse generateGetImmediateChargeCodeInfoResponse()throws Exception{
        GetImmediateChargeCodeInfoResponse res = new GetImmediateChargeCodeInfoResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = "{\n" +
                    "\"uuid\":\"c2e16734-077a-417c-aef3-d8707dc6250e\",\n" +
                    "\"errorCode\":\"OSBbllngA00001\",\n" +
                    "\"message\":\"Success\",\n" +
                        "\"chargeCodeInfo\": {\n" +
                        "\"chargeCode\": \"OS0312\",\n" +
                        "\"chargeCodeDesc\": \"Outstanding Debt 12/2003\",\n" +
                        "\"revenueCode\": \"OC\",\n" +
                        "\"groupType\": \"I\",\n" +
                        "\"l9BillPresentInd\": \"N\",\n" +
                        "\"billTh\": \"12/2003\",\n" +
                        "\"billEn\": \"Outstanding Debt 12/2003\"\n" +
                        "}\n" +
                "}";

        res = objectMapper.readValue(jsonStr,GetImmediateChargeCodeInfoResponse.class);
        return res;

    }




}
