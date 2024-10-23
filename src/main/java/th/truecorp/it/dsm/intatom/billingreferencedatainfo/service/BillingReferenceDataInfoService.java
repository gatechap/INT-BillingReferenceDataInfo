package th.truecorp.it.dsm.intatom.billingreferencedatainfo.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.exception.DataNotFoundException;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.exception.InvalidRequestException;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils.yaml.YAMLUtil;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getSplitRuleList.RequestGetSplitRuleList;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getSplitRuleList.ResponseGetSplitRuleList;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getSplitRuleList.SplitRuleInfoArray;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getSplitRuleList.SplitRuleList;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getpolicieslist.PoliciesInfo;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getpolicieslist.PoliciesList;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getpolicieslist.RequestPoliciesList;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getpolicieslist.ResponsePoliciesList;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.request.GetCycleControlListRequest;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.request.GetImmediateChargeCodeInfoRequest;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.response.ChargeCodeInfo;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.response.CycleControlList;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.response.CycleInfo;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.response.GetCycleControlListResponse;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.response.GetImmediateChargeCodeInfoResponse;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.ChargeCodeInfoDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.CycleControlInfoDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.PoliciesListByPolicyTypeInfo;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.QueryAllSplitRuleListInfoDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.QueryLogicalDateInfoDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.QuerySplitRuleBySplitCodeInfoDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.service.blngrtr.IBillingReferenceDataInfoRouter;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.service.utils.DataTypeConvertor;
import th.truecorp.it.dsm.intspringstarter.errormessage.constant.ErrorCodeConstant;
import th.truecorp.it.dsm.intspringstarter.errormessage.constant.ErrorMessageConstant;
import th.truecorp.it.dsm.intspringstarter.errormessage.constant.ErrorSystemTypeConstant;
import th.truecorp.it.dsm.intspringstarter.errormessage.controller.ErrorMessageController;
import th.truecorp.it.dsm.intspringstarter.errormessage.model.ErrorMessageInfo;
import th.truecorp.it.esd.ei.commonlib.utils.ValidateUtils;
import th.truecorp.it.esd.ei.commonlib.utils.bean.ValidateInfo;

@Service
public class BillingReferenceDataInfoService implements IBillingReferenceDataInfoService {

	@Autowired
	private IBillingReferenceDataInfoRouter billingReferenceDataInfoRouter;

	@Override
	public ResponseGetSplitRuleList getSplitRuleList(String uuid, String controllerPath,
			RequestGetSplitRuleList request) throws Exception {
		ResponseGetSplitRuleList response = new ResponseGetSplitRuleList();
		String allOrByCode = "";
		String errorCode;
		String errorMessage;
		ErrorMessageInfo errorInfo = null;
		response.setUuid(uuid);

		try {

//            //BEGIN test CES
//            billingReferenceDataInfoRouter.queryAllSplitRuleList("CES","02/02/2022", "uuid", "path", "corr");
//            billingReferenceDataInfoRouter.querySplitRuleBySplitCode("CES", "1", "02/02/2022", "uuid", "path", "corr");
//            billingReferenceDataInfoRouter.queryLogicalDate("CES", "uuid", "path", "corr");
//            billingReferenceDataInfoRouter.getChargeCodeInfo("CES","1", "uuid");
//            GetCycleControlListRequest cRequest = new GetCycleControlListRequest();
//            cRequest.setCorrelatedId("corr");
//            cRequest.setCycleMonth(2);
//            cRequest.setCycleYear(2022);
//            billingReferenceDataInfoRouter.getCycleControlList("CES", cRequest, "uuid");
//            //END test CES

			String[] fieldValidateNotNull = new String[] { "splitCode" };
			ValidateInfo checkNullInfo = ValidateUtils.isNotNullParam(request, fieldValidateNotNull);
			if (checkNullInfo.isInvalidFlag()) {
				allOrByCode = "All";
			} else {
				allOrByCode = "ByCode";
			}

			// CES getMigrationSystemInfo
			String billingSystem = YAMLUtil.getMyConfig().getSpring().getEnvironmentCtrl().getDefaultBillingSystemRef();
//            RequestGetMigrationSystemInfo requestMigration = new RequestGetMigrationSystemInfo();
//            requestMigration.setCallerUuid(uuid);
//            requestMigration.setCorrelatedId(StringUtils.isEmpty(request.getCorrelatedId()) ? uuid : request.getCorrelatedId());
//            requestMigration.setMainKey("SPLITCODE");
//            requestMigration.setMainValue(String.valueOf(request.getSplitCode()));
//
//            ResponseGetMigrationSystemInfo resultGetMigration = iGetMigrationSystemInfoClient
//                    .getMigrationSystemInfo(requestMigration, request.getCorrelatedId(), controllerPath, uuid);
//
//            String billingSystem = null;
//
//            if (ErrorCodeConstant.SUCCESS.toString().equals(resultGetMigration.getErrorCode())) {
//                billingSystem = resultGetMigration.getMigrationSystem();
//            } else if (ErrorCodeConstant.DATA_NOT_FOUND.toString().equals(resultGetMigration.getErrorCode())) {
//                billingSystem = YAMLUtil.getMyConfig().getSpring().getEnvironmentCtrl().getDefaultBillingSystem();
//            } else {
//                throw new Exception("getMigrationSystemInfo : " + resultGetMigration.getMessage());
//            }

			List<QueryLogicalDateInfoDAO> logical_dates = billingReferenceDataInfoRouter.queryLogicalDate(billingSystem,
					uuid, controllerPath, request.getCorrelatedId());

			if (allOrByCode == "All") {
				List<QueryAllSplitRuleListInfoDAO> result = billingReferenceDataInfoRouter.queryAllSplitRuleList(
						billingSystem, logical_dates.get(0).getLogicalDate(), uuid, controllerPath,
						request.getCorrelatedId());
				if (result != null && result.size() > 0) {
					SplitRuleList splitRuleList = new SplitRuleList();
					List<SplitRuleInfoArray> splitRuleInfoArray = new ArrayList<SplitRuleInfoArray>();
					for (QueryAllSplitRuleListInfoDAO item : result) {
						SplitRuleInfoArray splitRuleInfo = new SplitRuleInfoArray();

						if (item.getExceedInd() != null) {
							splitRuleInfo.setExceedInd(item.getExceedInd());
						} else {
							splitRuleInfo.setExceedInd(null);
						}

						if (item.getIncludeInd() != null) {
							splitRuleInfo.setIncludeInd(item.getIncludeInd());
						} else {
							splitRuleInfo.setIncludeInd(null);
						}

						if (item.getQuantity() != null) {
							splitRuleInfo.setQuantity(item.getQuantity().setScale(2, BigDecimal.ROUND_HALF_UP));
						} else {
							splitRuleInfo.setQuantity(null);
						}

						if (item.getSplitCode() != null) {
							splitRuleInfo.setSplitCode(item.getSplitCode());
						} else {
							splitRuleInfo.setSplitCode(null);
						}

						if (item.getSplitDescription() != null) {
							splitRuleInfo.setSplitDescription(item.getSplitDescription());
						} else {
							splitRuleInfo.setSplitDescription(null);
						}

						if (item.getSplitEffectiveDate() != null) {
							splitRuleInfo.setSplitEffectiveDate(item.getSplitEffectiveDate());
						} else {
							splitRuleInfo.setSplitEffectiveDate(null);
						}

						if (item.getSplitExpirationDate() != null) {
							splitRuleInfo.setSplitExpirationDate(item.getSplitExpirationDate());
						} else {
							splitRuleInfo.setSplitExpirationDate(null);
						}

						if (item.getSplitLevel() != null) {
							splitRuleInfo.setSplitLevel(item.getSplitLevel());
						} else {
							splitRuleInfo.setSplitLevel(null);
						}

						if (item.getSplitValue() != null) {
							splitRuleInfo.setSplitValue(item.getSplitValue());
						} else {
							splitRuleInfo.setSplitValue(null);
						}

						if (item.getSplitWay() != null) {
							splitRuleInfo.setSplitWay(item.getSplitWay());
						} else {
							splitRuleInfo.setSplitWay(null);
						}

						if (item.getWorkInd() != null) {
							splitRuleInfo.setWorkInd(item.getWorkInd());
						} else {
							splitRuleInfo.setWorkInd(null);
						}

						splitRuleInfoArray.add(splitRuleInfo);
					}

					splitRuleList.setSize(splitRuleInfoArray.size());
					splitRuleList.setSplitRuleInfoArray(splitRuleInfoArray);
					response.setSplitRuleList(splitRuleList);
					response.setErrorCode(ErrorCodeConstant.SUCCESS.toString());
					response.setMessage(ErrorMessageConstant.SUCCESS.toString());
				} else {
					response.setErrorCode(ErrorCodeConstant.DATA_NOT_FOUND.toString());
					response.setMessage(ErrorMessageConstant.DATA_NOT_FOUND.toString());
				}
			} else if (allOrByCode == "ByCode") {
				List<QuerySplitRuleBySplitCodeInfoDAO> result = billingReferenceDataInfoRouter
						.querySplitRuleBySplitCode(billingSystem, request.getSplitCode(),
								logical_dates.get(0).getLogicalDate(), uuid, controllerPath, request.getCorrelatedId());
				if (result != null && result.size() > 0) {
					SplitRuleList splitRuleList = new SplitRuleList();
					List<SplitRuleInfoArray> splitRuleInfoArray = new ArrayList<SplitRuleInfoArray>();
					for (QuerySplitRuleBySplitCodeInfoDAO item : result) {
						SplitRuleInfoArray splitRuleInfo = new SplitRuleInfoArray();

						if (item.getExceedInd() != null) {
							splitRuleInfo.setExceedInd(item.getExceedInd());
						} else {
							splitRuleInfo.setExceedInd(null);
						}

						if (item.getIncludeInd() != null) {
							splitRuleInfo.setIncludeInd(item.getIncludeInd());
						} else {
							splitRuleInfo.setIncludeInd(null);
						}

						if (item.getQuantity() != null) {
							splitRuleInfo.setQuantity(item.getQuantity().setScale(2, BigDecimal.ROUND_HALF_UP));
						} else {
							splitRuleInfo.setQuantity(null);
						}

						if (item.getSplitCode() != null) {
							splitRuleInfo.setSplitCode(item.getSplitCode());
						} else {
							splitRuleInfo.setSplitCode(null);
						}

						if (item.getSplitDescription() != null) {
							splitRuleInfo.setSplitDescription(item.getSplitDescription());
						} else {
							splitRuleInfo.setSplitDescription(null);
						}

						if (item.getSplitEffectiveDate() != null) {
							splitRuleInfo.setSplitEffectiveDate(item.getSplitEffectiveDate());
						} else {
							splitRuleInfo.setSplitEffectiveDate(null);
						}

						if (item.getSplitExpirationDate() != null) {
							splitRuleInfo.setSplitExpirationDate(item.getSplitExpirationDate());
						} else {
							splitRuleInfo.setSplitExpirationDate(null);
						}

						if (item.getSplitLevel() != null) {
							splitRuleInfo.setSplitLevel(item.getSplitLevel());
						} else {
							splitRuleInfo.setSplitLevel(null);
						}

						if (item.getSplitValue() != null) {
							splitRuleInfo.setSplitValue(item.getSplitValue());
						} else {
							splitRuleInfo.setSplitValue(null);
						}

						if (item.getSplitWay() != null) {
							splitRuleInfo.setSplitWay(item.getSplitWay());
						} else {
							splitRuleInfo.setSplitWay(null);
						}

						if (item.getWorkInd() != null) {
							splitRuleInfo.setWorkInd(item.getWorkInd());
						} else {
							splitRuleInfo.setWorkInd(null);
						}

						splitRuleInfoArray.add(splitRuleInfo);
					}

					splitRuleList.setSize(splitRuleInfoArray.size());
					splitRuleList.setSplitRuleInfoArray(splitRuleInfoArray);
					response.setSplitRuleList(splitRuleList);
					response.setErrorCode(ErrorCodeConstant.SUCCESS.toString());
					response.setMessage(ErrorMessageConstant.SUCCESS.toString());
				} else {
					response.setErrorCode(ErrorCodeConstant.DATA_NOT_FOUND.toString());
					response.setMessage(ErrorMessageConstant.DATA_NOT_FOUND.toString());
				}
			}
		} catch (IOException ioe) {
			System.out.println("IOException:" + ioe);
			errorCode = errorInfo.getErrorCode();
			errorMessage = errorInfo.getMessage();
			response.setUuid(uuid);
			response.setErrorCode(errorCode);
			response.setMessage(errorMessage);
		} catch (Exception e) {
			e.printStackTrace();
			errorCode = errorInfo.getErrorCode();
			errorMessage = errorInfo.getMessage();
			response.setUuid(uuid);
			response.setErrorCode(errorCode);
			response.setMessage(errorMessage);
		}

		return response;
	}

	@Override
	public GetImmediateChargeCodeInfoResponse getImmediateChargeCode(GetImmediateChargeCodeInfoRequest request,
			String uuid) throws Exception {
		GetImmediateChargeCodeInfoResponse result = new GetImmediateChargeCodeInfoResponse();

		String dateTimeFormatP7 = "yyyy-MM-dd'T'HH:mm:ss.SSS+07:00";
		String dateTimeFormat = "dd/MM/YYYY HH:MM:ss";
		String errorCode;
		String errorMessage;
		ErrorMessageInfo errorInfo = null;
		String invalidValue = "";
		String invalidField = "";

		// ValidateVal and check Null
		String[] fieldValidateNotNull = { "chargeCode" };
//        HashMap<String, String> validValues = new HashMap<String, String>();
//        validValues.put("status", "ACTIVE,ALL");

		try {

			ValidateInfo checkNullInfo = ValidateUtils.isNotNullParam(request, fieldValidateNotNull);

			if (checkNullInfo.isInvalidFlag()) {
				invalidField = checkNullInfo.getInvalidField();
				errorInfo = ErrorMessageController.getParameterIsRequiredMessageInfo(invalidField);
				throw new IOException();
			} else {
//                ValidateInfo checkValidValueInfo = ValidateUtils.validValues(request, validValues);
//                if (checkValidValueInfo.isInvalidFlag()) {
//                    invalidField = checkValidValueInfo.getInvalidField();
//                    invalidValue = checkValidValueInfo.getInvalidValue();
//                    errorInfo = ErrorMessageController.getParameterIsInvalidMessageInfoNonArray(invalidField, invalidValue);
//                    throw new InvalidParameterException();
//                }
			}

			// CES getMigrationSystemInfo
			String billingSystem = YAMLUtil.getMyConfig().getSpring().getEnvironmentCtrl().getDefaultBillingSystemRef();
//            RequestGetMigrationSystemInfo requestMigration = new RequestGetMigrationSystemInfo();
//            requestMigration.setCallerUuid(uuid);
//            requestMigration.setCorrelatedId(uuid);
//            requestMigration.setMainKey("CHARGECODE");
//            requestMigration.setMainValue(String.valueOf(request.getChargeCode()));
//
//            ResponseGetMigrationSystemInfo resultGetMigration = iGetMigrationSystemInfoClient
//                    .getMigrationSystemInfo(requestMigration, null, "getImmediateChargeCode", uuid);
//
//            String billingSystem = null;
//
//            if (ErrorCodeConstant.SUCCESS.toString().equals(resultGetMigration.getErrorCode())) {
//                billingSystem = resultGetMigration.getMigrationSystem();
//            } else if (ErrorCodeConstant.DATA_NOT_FOUND.toString().equals(resultGetMigration.getErrorCode())) {
//                billingSystem = YAMLUtil.getMyConfig().getSpring().getEnvironmentCtrl().getDefaultBillingSystem();
//            } else {
//                throw new Exception("getMigrationSystemInfo : " + resultGetMigration.getMessage());
//            }

			errorInfo = ErrorMessageController.getSuccessMessageInfo();

			ChargeCodeInfoDAO response = billingReferenceDataInfoRouter.getChargeCodeInfo(billingSystem,
					request.getChargeCode(), uuid);
			if (response != null) {
				ChargeCodeInfo chargeCodeInfo = new ChargeCodeInfo();
				chargeCodeInfo.setChargeCode(response.getChargeCode());
				chargeCodeInfo.setChargeCodeDesc(response.getChargeCodeDesc());
				chargeCodeInfo.setRevenueCode(response.getRevenueCode());
				chargeCodeInfo.setGroupType(response.getGroupType());
				chargeCodeInfo.setL9BillPresentInd(response.getL9BillPresentInd());
				chargeCodeInfo.setBillTh(response.getBillTh());
				chargeCodeInfo.setBillEn(response.getBillEn());

				result.setChargeCodeInfo(chargeCodeInfo);
				result.setUuid(uuid);
				result.setErrorCode(errorInfo.getErrorCode());
				result.setMessage(errorInfo.getMessage());

			} else {

				errorInfo = ErrorMessageController.getDataNotFoundMessageInfo();

				result.setUuid(uuid);
				result.setErrorCode(errorInfo.getErrorCode());
				result.setMessage(errorInfo.getMessage());

			}

		} catch (InvalidParameterException ipe) {
			ipe.printStackTrace();
			errorCode = errorInfo.getErrorCode();
			errorMessage = errorInfo.getMessage();
			result.setUuid(uuid);
			result.setErrorCode(errorCode);
			result.setMessage(errorMessage);
		} catch (IOException ioe) {
			System.out.println("IOException:" + ioe);
			errorCode = errorInfo.getErrorCode();
			errorMessage = errorInfo.getMessage();
			result.setUuid(uuid);
			result.setErrorCode(errorCode);
			result.setMessage(errorMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorCode = errorInfo.getErrorCode();
			errorMessage = errorInfo.getMessage();
			result.setUuid(uuid);
			result.setErrorCode(errorCode);
			result.setMessage(errorMessage);
		}

		return result;
	}

	@Override
	public GetCycleControlListResponse getCycleControlList(GetCycleControlListRequest request, String uuid)
			throws Exception {
		GetCycleControlListResponse result = new GetCycleControlListResponse();
		CycleControlList cycleControlList = new CycleControlList();
		List<CycleInfo> cycleInfos = new ArrayList<>();
		// ValidateVal and check Null

		String dateTimeFormatP7 = "yyyy-MM-dd'T'HH:mm:ss.SSS+07:00";
		String dateTimeFormat = "dd/MM/YYYY HH:MM:ss";
		String errorCode;
		String errorMessage;
		ErrorMessageInfo errorInfo = null;
		String invalidValue = "";
		String invalidField = "";

		String[] fieldValidateNotNull = { "correlatedId", "cycleMonth", "cycleYear" };
		try {

			ValidateInfo checkNullInfo = ValidateUtils.isNotNullParam(request, fieldValidateNotNull);
			if (checkNullInfo.isInvalidFlag()) {
				invalidField = checkNullInfo.getInvalidField();
				errorInfo = ErrorMessageController.getParameterIsRequiredMessageInfo(invalidField);
				throw new IOException();
			}

			// CES getMigrationSystemInfo
			String billingSystem = YAMLUtil.getMyConfig().getSpring().getEnvironmentCtrl().getDefaultBillingSystemRef();
//            RequestGetMigrationSystemInfo requestMigration = new RequestGetMigrationSystemInfo();
//            requestMigration.setCallerUuid(uuid);
//            requestMigration.setCorrelatedId(uuid);
//            requestMigration.setMainKey("CYCLEMONTH");
//            requestMigration.setMainValue(String.valueOf(request.getCycleMonth()));
//            requestMigration.setSubTypeKey("CYCLEYEAR");
//            requestMigration.setSubTypeValue(String.valueOf(request.getCycleYear()));
//
//            ResponseGetMigrationSystemInfo resultGetMigration = iGetMigrationSystemInfoClient
//                    .getMigrationSystemInfo(requestMigration, null, "getCycleControlList", uuid);
//
//            String billingSystem = null;
//
//            if (ErrorCodeConstant.SUCCESS.toString().equals(resultGetMigration.getErrorCode())) {
//                billingSystem = resultGetMigration.getMigrationSystem();
//            } else if (ErrorCodeConstant.DATA_NOT_FOUND.toString().equals(resultGetMigration.getErrorCode())) {
//                billingSystem = YAMLUtil.getMyConfig().getSpring().getEnvironmentCtrl().getDefaultBillingSystem();
//            } else {
//                throw new Exception("getMigrationSystemInfo : " + resultGetMigration.getMessage());
//            }

			errorInfo = ErrorMessageController.getSuccessMessageInfo();

			List<CycleControlInfoDAO> codeInfoDAOS = billingReferenceDataInfoRouter.getCycleControlList(billingSystem,
					request, uuid);
			if (codeInfoDAOS.size() > 0) {
				for (CycleControlInfoDAO cycleControlInfoDAO : codeInfoDAOS) {
					CycleInfo cycleInfo = DataTypeConvertor.convertToCycleInfo(cycleControlInfoDAO);
					cycleInfos.add(cycleInfo);
				}
				cycleControlList.setCycleInfoArray(cycleInfos);
				cycleControlList.setSize(cycleInfos.size());
				result.setCycleControlList(cycleControlList);
				result.setUuid(uuid);
				result.setErrorCode(errorInfo.getErrorCode());
				result.setMessage(errorInfo.getMessage());
			} else {
				errorInfo = ErrorMessageController.getDataNotFoundMessageInfo();
				result.setUuid(uuid);
				result.setErrorCode(errorInfo.getErrorCode());
				result.setMessage(errorInfo.getMessage());
			}

		} catch (InvalidParameterException ipe) {
			ipe.printStackTrace();
			errorCode = errorInfo.getErrorCode();
			errorMessage = errorInfo.getMessage();
			result.setUuid(uuid);
			result.setErrorCode(errorCode);
			result.setMessage(errorMessage);
		} catch (IOException ioe) {
			System.out.println("IOException:" + ioe);
			errorCode = errorInfo.getErrorCode();
			errorMessage = errorInfo.getMessage();
			result.setUuid(uuid);
			result.setErrorCode(errorCode);
			result.setMessage(errorMessage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			errorCode = errorInfo.getErrorCode();
			errorMessage = errorInfo.getMessage();
			result.setUuid(uuid);
			result.setErrorCode(errorCode);
			result.setMessage(errorMessage);
		}

		return result;
	}

	@Override
	public ResponsePoliciesList getPoliciesListByPolicyType(RequestPoliciesList request, String uuid,
			String controllerPath) throws Exception {

		ResponsePoliciesList response = new ResponsePoliciesList();
		String correlatedId = request.getCorrelatedId();
		response.setUuid(uuid);

		ErrorMessageInfo errorInfo = null;
		String[] fieldValidateNotNull = null;

		List<String> policyTypeList = null;

		try {

			fieldValidateNotNull = new String[] { "correlatedId", "policyTypeList" };

			ValidateInfo checkNullInfo = ValidateUtils.isNotNullParam(request, fieldValidateNotNull);

			if (checkNullInfo.isInvalidFlag()) {
				String invalidField = checkNullInfo.getInvalidField();
				errorInfo = ErrorMessageController.getParameterIsRequiredMessageInfo(invalidField,
						ErrorSystemTypeConstant.INTX);
				throw new InvalidRequestException(errorInfo.getErrorCode(), errorInfo.getMessage());
			}

			if (request.getPolicyTypeList() != null && request.getPolicyTypeList().length < 1) {
				String invalidField = "policyTypeList";
				errorInfo = ErrorMessageController.getParameterIsRequiredMessageInfo(invalidField,
						ErrorSystemTypeConstant.INTX);
				throw new InvalidRequestException(errorInfo.getErrorCode(), errorInfo.getMessage());
			}

			policyTypeList = Arrays.asList(request.getPolicyTypeList());

			String billingSystem = YAMLUtil.getMyConfig().getSpring().getEnvironmentCtrl().getDefaultBillingSystemRef();

			List<PoliciesListByPolicyTypeInfo> result = billingReferenceDataInfoRouter
					.routingQueryPoliciesListByPolicyType(policyTypeList, uuid, controllerPath, correlatedId,
							billingSystem);

			if (result != null && result.size() < 1) {
				throw new DataNotFoundException();
			}

			PoliciesList policiesList = new PoliciesList();
			List<PoliciesInfo> policiesInfoArray = new ArrayList<PoliciesInfo>();

			policiesInfoArray = result.stream().map(x -> {
				PoliciesInfo policiesInfo = new PoliciesInfo();
				policiesInfo.setDescription(x.getDescription());
				policiesInfo.setLanguage(x.getLanguage());
				policiesInfo.setPolicyId(x.getPolicyId());
				policiesInfo.setPolicyName(x.getPolicyName());
				policiesInfo.setPolicyQualifier(x.getPolicyQualifier());
				policiesInfo.setPolicyType(x.getPolicyType());
				policiesInfo.setPolicyValue(x.getPolicyValue());
				return policiesInfo;
			}).collect(Collectors.toList());

			policiesList.setSize(policiesInfoArray.size());
			policiesList.setPoliciesInfoArray(policiesInfoArray);
			response.setPoliciesList(policiesList);
			response.setErrorCode(ErrorCodeConstant.SUCCESS.toString());
			response.setMessage(ErrorMessageConstant.SUCCESS.toString());

		} catch (InvalidRequestException e) {
			response.setErrorCode(e.getErrorCode());
			response.setMessage(e.getErrorMessage());
		} catch (DataNotFoundException e) {
			response.setErrorCode(ErrorCodeConstant.DATA_NOT_FOUND.toString());
			response.setMessage(ErrorMessageConstant.DATA_NOT_FOUND.toString());
		}

		return response;
	}

}
