package th.truecorp.it.dsm.intatom.billingreferencedatainfo.service.blngrtr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.truecorp.it.dsm.billngrulelib.constant.BillingSystemConstant;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.request.GetCycleControlListRequest;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.ces.IBillingReferenceDataInfoCESDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.IBillingReferenceDataInfoDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.*;

import java.util.List;

@Service
public class BillingReferenceDataInfoRouter implements IBillingReferenceDataInfoRouter {

	@Autowired
	private IBillingReferenceDataInfoDAO iBillingReferenceDataInfoDAO;

	@Autowired
	private IBillingReferenceDataInfoCESDAO iBillingReferenceDataInfoCESDAO;

	@Override
	public List<PoliciesListByPolicyTypeInfo> routingQueryPoliciesListByPolicyType(List<String> policyTypeList,
			String uuid, String controllerPath, String correlatedId, String billingSystem) throws Exception {
		List<PoliciesListByPolicyTypeInfo> response = null;
		if (BillingSystemConstant.CES.toString().equalsIgnoreCase(billingSystem)) {
			response = iBillingReferenceDataInfoCESDAO.queryPoliciesListByPolicyTypeCES(policyTypeList, uuid,
					controllerPath, correlatedId);
		} else if (BillingSystemConstant.CCBS.toString().equalsIgnoreCase(billingSystem)) {
			response = iBillingReferenceDataInfoDAO.queryPoliciesListByPolicyType(policyTypeList, uuid, controllerPath,
					correlatedId);
		}
		return response;
	}

	@Override
	public List<QueryAllSplitRuleListInfoDAO> queryAllSplitRuleList(String billingSystem, String logical_date,
			String uuid, String controllerPath, String correlatedId) throws Exception {
		List<QueryAllSplitRuleListInfoDAO> response = null;
		if (BillingSystemConstant.CES.toString().equalsIgnoreCase(billingSystem)) {
			response = iBillingReferenceDataInfoCESDAO.queryAllSplitRuleListCES(logical_date, uuid, controllerPath,
					correlatedId);
		} else if (BillingSystemConstant.CCBS.toString().equalsIgnoreCase(billingSystem)) {
			response = iBillingReferenceDataInfoDAO.queryAllSplitRuleList(logical_date, uuid, controllerPath,
					correlatedId);
		}
		return response;
	}

	@Override
	public List<QuerySplitRuleBySplitCodeInfoDAO> querySplitRuleBySplitCode(String billingSystem, String splitCode,
			String logical_date, String uuid, String controllerPath, String correlatedId) throws Exception {
		List<QuerySplitRuleBySplitCodeInfoDAO> response = null;
		if (BillingSystemConstant.CES.toString().equalsIgnoreCase(billingSystem)) {
			response = iBillingReferenceDataInfoCESDAO.querySplitRuleBySplitCodeCES(splitCode, logical_date, uuid,
					controllerPath, correlatedId);
		} else if (BillingSystemConstant.CCBS.toString().equalsIgnoreCase(billingSystem)) {
			response = iBillingReferenceDataInfoDAO.querySplitRuleBySplitCode(splitCode, logical_date, uuid,
					controllerPath, correlatedId);
		}
		return response;
	}

	@Override
	public List<QueryLogicalDateInfoDAO> queryLogicalDate(String billingSystem, String uuid, String controllerPath,
			String correlatedId) throws Exception {
		List<QueryLogicalDateInfoDAO> response = null;
		if (BillingSystemConstant.CES.toString().equalsIgnoreCase(billingSystem)) {
			response = iBillingReferenceDataInfoCESDAO.queryLogicalDateCES(uuid, controllerPath, correlatedId);
		} else if (BillingSystemConstant.CCBS.toString().equalsIgnoreCase(billingSystem)) {
			response = iBillingReferenceDataInfoDAO.queryLogicalDate(uuid, controllerPath, correlatedId);
		}
		return response;
	}

	@Override
	public ChargeCodeInfoDAO getChargeCodeInfo(String billingSystem, String chargeCode, String uuid) throws Exception {
		ChargeCodeInfoDAO response = null;
		if (BillingSystemConstant.CES.toString().equalsIgnoreCase(billingSystem)) {
			response = iBillingReferenceDataInfoCESDAO.getChargeCodeInfoCES(chargeCode, uuid);
		} else if (BillingSystemConstant.CCBS.toString().equalsIgnoreCase(billingSystem)) {
			response = iBillingReferenceDataInfoDAO.getChargeCodeInfo(chargeCode, uuid);
		}
		return response;
	}

	@Override
	public List<CycleControlInfoDAO> getCycleControlList(String billingSystem, GetCycleControlListRequest request,
			String uuid) throws Exception {
		List<CycleControlInfoDAO> response = null;
		if (BillingSystemConstant.CES.toString().equalsIgnoreCase(billingSystem)) {
			response = iBillingReferenceDataInfoCESDAO.getCycleControlListCES(request, uuid);
		} else if (BillingSystemConstant.CCBS.toString().equalsIgnoreCase(billingSystem)) {
			response = iBillingReferenceDataInfoDAO.getCycleControlList(request, uuid);
		}
		return response;
	}

}
