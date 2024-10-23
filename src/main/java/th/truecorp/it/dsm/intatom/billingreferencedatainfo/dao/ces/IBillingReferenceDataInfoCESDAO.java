package th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.ces;

import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.request.GetCycleControlListRequest;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.*;

import java.util.List;

public interface IBillingReferenceDataInfoCESDAO {

	List<QueryAllSplitRuleListInfoDAO> queryAllSplitRuleListCES(String logical_date, String uuid, String controllerPath,
			String correlatedId) throws Exception;

	List<QuerySplitRuleBySplitCodeInfoDAO> querySplitRuleBySplitCodeCES(String splitCode, String logical_date,
			String uuid, String controllerPath, String correlatedId) throws Exception;

	List<QueryLogicalDateInfoDAO> queryLogicalDateCES(String uuid, String controllerPath, String correlatedId)
			throws Exception;

	ChargeCodeInfoDAO getChargeCodeInfoCES(String chargeCode, String uuid) throws Exception;

	List<CycleControlInfoDAO> getCycleControlListCES(GetCycleControlListRequest request, String uuid) throws Exception;

	List<PoliciesListByPolicyTypeInfo> queryPoliciesListByPolicyTypeCES(List<String> policyTypeList, String uuid,
			String controllerPath, String correlatedId) throws Exception;

}
