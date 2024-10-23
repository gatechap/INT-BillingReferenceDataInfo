package th.truecorp.it.dsm.intatom.billingreferencedatainfo.service.blngrtr;

import java.util.List;

import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.request.GetCycleControlListRequest;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.ChargeCodeInfoDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.CycleControlInfoDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.PoliciesListByPolicyTypeInfo;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.QueryAllSplitRuleListInfoDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.QueryLogicalDateInfoDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.QuerySplitRuleBySplitCodeInfoDAO;

public interface IBillingReferenceDataInfoRouter {

	List<QueryAllSplitRuleListInfoDAO> queryAllSplitRuleList(String billingSystem, String logical_date, String uuid,
			String controllerPath, String correlatedId) throws Exception;

	List<QuerySplitRuleBySplitCodeInfoDAO> querySplitRuleBySplitCode(String billingSystem, String splitCode,
			String logical_date, String uuid, String controllerPath, String correlatedId) throws Exception;

	List<QueryLogicalDateInfoDAO> queryLogicalDate(String billingSystem, String uuid, String controllerPath,
			String correlatedId) throws Exception;

	ChargeCodeInfoDAO getChargeCodeInfo(String billingSystem, String chargeCode, String uuid) throws Exception;

	List<CycleControlInfoDAO> getCycleControlList(String billingSystem, GetCycleControlListRequest request, String uuid)
			throws Exception;

	List<PoliciesListByPolicyTypeInfo> routingQueryPoliciesListByPolicyType(
			List<String> policyTypeList, String uuid,
			String controllerPath, String correlatedId, String billingSystem) throws Exception;
}
