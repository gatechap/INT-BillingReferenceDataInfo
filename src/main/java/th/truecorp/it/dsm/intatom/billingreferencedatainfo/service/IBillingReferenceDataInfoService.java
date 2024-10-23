package th.truecorp.it.dsm.intatom.billingreferencedatainfo.service;

import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getSplitRuleList.RequestGetSplitRuleList;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getSplitRuleList.ResponseGetSplitRuleList;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getpolicieslist.RequestPoliciesList;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getpolicieslist.ResponsePoliciesList;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.request.GetCycleControlListRequest;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.request.GetImmediateChargeCodeInfoRequest;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.response.GetCycleControlListResponse;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.response.GetImmediateChargeCodeInfoResponse;

public interface IBillingReferenceDataInfoService {
	public ResponseGetSplitRuleList getSplitRuleList(String uuid, String controllerPath,
			RequestGetSplitRuleList request) throws Exception;

	public GetImmediateChargeCodeInfoResponse getImmediateChargeCode(GetImmediateChargeCodeInfoRequest request,
			String uuid) throws Exception;

	public GetCycleControlListResponse getCycleControlList(GetCycleControlListRequest request, String uuid)
			throws Exception;

	ResponsePoliciesList getPoliciesListByPolicyType(RequestPoliciesList request, String uuid, String controllerPath)
			throws Exception;

}
