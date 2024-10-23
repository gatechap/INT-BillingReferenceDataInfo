package th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tit.ei.log.interfaces.model.RequestInputLogger;
import com.tit.ei.log.interfaces.model.ResponseInputLogger;
import com.tit.ei.log.interfaces.provider.WriteLogProvider;
import com.tit.ei.log.util.DateTimeUtility;

import th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.auth.Auth;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.log.WriteLog;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils.yaml.YAMLUtil;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getSplitRuleList.RequestGetSplitRuleList;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getSplitRuleList.ResponseGetSplitRuleList;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getpolicieslist.RequestPoliciesList;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.getpolicieslist.ResponsePoliciesList;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.request.GetCycleControlListRequest;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.request.GetImmediateChargeCodeInfoRequest;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.response.GetCycleControlListResponse;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.response.GetImmediateChargeCodeInfoResponse;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.service.IBillingReferenceDataInfoService;
import th.truecorp.it.dsm.intspringstarter.errormessage.constant.ErrorCodeConstant;
import th.truecorp.it.dsm.intspringstarter.errormessage.constant.ErrorMessageConstant;
import th.truecorp.it.dsm.intspringstarter.errormessage.constant.ErrorSystemTypeConstant;
import th.truecorp.it.dsm.intspringstarter.errormessage.controller.ErrorMessageController;
import th.truecorp.it.dsm.intspringstarter.errormessage.model.ErrorMessageInfo;

@RestController
@RequestMapping("/BillingReferenceDataInfo")
public class BillingReferenceDataInfoController {

	@Autowired
	private IBillingReferenceDataInfoService billingReferenceDataInfoService;

	@ResponseBody
	@RequestMapping(value = "/getSplitRuleList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseGetSplitRuleList> getSplitRuleList(@RequestBody RequestGetSplitRuleList request,
			@RequestHeader(name = "X-Username", required = false) String xUser) {

		String uuid = UUID.randomUUID().toString();
		String controllerPath = "/getSplitRuleList";
		ResponseGetSplitRuleList result = new ResponseGetSplitRuleList();

		ErrorMessageInfo errorInfo = null;

		try {

			errorInfo = ErrorMessageController.getSuccessMessageInfo();

			WriteLogProvider writeLogProvider = new WriteLogProvider(uuid, "/getSplitRuleList", null,
					YAMLUtil.getMyConfig().getSpring().getProfiles());
			RequestInputLogger log = new RequestInputLogger();
			log.setUsername(xUser);
			log.setLegacyUsername(xUser);
			log.setInputParamLogObj(request);
			log.setChannel("X-Channel");
			DateTimeUtility dateTimeUtilityReq = new DateTimeUtility();
			log.setDateCompletion(dateTimeUtilityReq.getCurrentISO8601());
			writeLogProvider.writeLogRequest(log);

			result = billingReferenceDataInfoService.getSplitRuleList(uuid, controllerPath, request);
			result.setUuid(uuid);

			ResponseInputLogger resLog = new ResponseInputLogger();
			resLog.setErrorCode(result.getErrorCode());
			resLog.setMessage(result.getMessage());
			resLog.setResultStatus(result.getMessage());
			resLog.setDateCompletion(dateTimeUtilityReq.getCurrentISO8601());
			resLog.setEndTime(System.currentTimeMillis());
			writeLogProvider.writeLogResponse(resLog);

			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (InvalidParameterException ipe) {
			ipe.printStackTrace();
			result.setErrorCode(errorInfo.getErrorCode());
			result.setMessage(errorInfo.getMessage());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			result.setErrorCode(errorInfo.getErrorCode());
			result.setMessage(errorInfo.getMessage());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorCode(errorInfo.getErrorCode());
			result.setMessage(errorInfo.getMessage());
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/getImmediateChargeCodeInfo", method = RequestMethod.POST)
	public ResponseEntity<GetImmediateChargeCodeInfoResponse> getImmediateChargeCodeInfo(
			@RequestBody GetImmediateChargeCodeInfoRequest getImmediateChargeCodeInfoRequest,
			@RequestHeader(name = "X-Username", required = false) String xUser) {

		String uuid = UUID.randomUUID().toString();
		GetImmediateChargeCodeInfoResponse result = new GetImmediateChargeCodeInfoResponse();

		ErrorMessageInfo errorInfo = null;

		try {

			errorInfo = ErrorMessageController.getSuccessMessageInfo();

			WriteLogProvider writeLogProvider = new WriteLogProvider(uuid, "/getImmediateChargeCodeInfo", null,
					YAMLUtil.getMyConfig().getSpring().getProfiles());
			RequestInputLogger log = new RequestInputLogger();
			log.setUsername(xUser);
			log.setLegacyUsername(xUser);
			log.setInputParamLogObj(getImmediateChargeCodeInfoRequest.getChargeCode());
			log.setChannel("X-Channel");
			DateTimeUtility dateTimeUtilityReq = new DateTimeUtility();
			log.setDateCompletion(dateTimeUtilityReq.getCurrentISO8601());
			writeLogProvider.writeLogRequest(log);

//            result = MockupBillingReferenceDataInfoResponse.generateGetImmediateChargeCodeInfoResponse();
			result = billingReferenceDataInfoService.getImmediateChargeCode(getImmediateChargeCodeInfoRequest, uuid);
			result.setUuid(uuid);

			ResponseInputLogger resLog = new ResponseInputLogger();
			resLog.setErrorCode(result.getErrorCode());
			resLog.setMessage(result.getMessage());
			resLog.setResultStatus(result.getMessage());
			resLog.setDateCompletion(dateTimeUtilityReq.getCurrentISO8601());
			resLog.setEndTime(System.currentTimeMillis());
			writeLogProvider.writeLogResponse(resLog);

			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (InvalidParameterException ipe) {
			ipe.printStackTrace();
			result.setErrorCode(errorInfo.getErrorCode());
			result.setMessage(errorInfo.getMessage());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			result.setErrorCode(errorInfo.getErrorCode());
			result.setMessage(errorInfo.getMessage());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorCode(errorInfo.getErrorCode());
			result.setMessage(errorInfo.getMessage());
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/getCycleControlList", method = RequestMethod.POST)
	public ResponseEntity<GetCycleControlListResponse> getCycleControlList(
			@RequestBody GetCycleControlListRequest request,
			@RequestHeader(name = "X-Username", required = false) String xUser) {

		String uuid = UUID.randomUUID().toString();
		GetCycleControlListResponse result = new GetCycleControlListResponse();

		ErrorMessageInfo errorInfo = null;

		try {

			errorInfo = ErrorMessageController.getSuccessMessageInfo();

			WriteLogProvider writeLogProvider = new WriteLogProvider(uuid, "/getCycleControlList", null,
					YAMLUtil.getMyConfig().getSpring().getProfiles());
			RequestInputLogger log = new RequestInputLogger();
			log.setUsername(xUser);
			log.setLegacyUsername(xUser);
			log.setInputParamLogObj(request);
			log.setChannel("X-Channel");
			DateTimeUtility dateTimeUtilityReq = new DateTimeUtility();
			log.setDateCompletion(dateTimeUtilityReq.getCurrentISO8601());
			writeLogProvider.writeLogRequest(log);

			result = billingReferenceDataInfoService.getCycleControlList(request, uuid);
			result.setUuid(uuid);

			ResponseInputLogger resLog = new ResponseInputLogger();
			resLog.setErrorCode(result.getErrorCode());
			resLog.setMessage(result.getMessage());
			resLog.setResultStatus(result.getMessage());
			resLog.setDateCompletion(dateTimeUtilityReq.getCurrentISO8601());
			resLog.setEndTime(System.currentTimeMillis());
			writeLogProvider.writeLogResponse(resLog);

			return new ResponseEntity<>(result, HttpStatus.OK);

		} catch (InvalidParameterException ipe) {
			ipe.printStackTrace();
			result.setErrorCode(errorInfo.getErrorCode());
			result.setMessage(errorInfo.getMessage());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			result.setErrorCode(errorInfo.getErrorCode());
			result.setMessage(errorInfo.getMessage());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrorCode(errorInfo.getErrorCode());
			result.setMessage(errorInfo.getMessage());
			return new ResponseEntity<>(result, HttpStatus.OK);
		}

	}

	@ResponseBody
	@RequestMapping(value = "/getPoliciesListByPolicyType", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponsePoliciesList> getPoliciesListByPolicyType(@RequestBody RequestPoliciesList request,
			HttpServletRequest httpServlet) throws Exception {

		ResponsePoliciesList response = new ResponsePoliciesList();
		String uuid = UUID.randomUUID().toString();
		String controllerPath = "/getPoliciesListByPolicyType";

		WriteLogProvider writeLogProvider = new WriteLogProvider(uuid, controllerPath, request.getCorrelatedId(),
				YAMLUtil.getMyConfig().getSpring().getProfiles());
		String user = Auth.getXUser(httpServlet.getHeader("Authorization"), httpServlet.getHeader("X-Username"));
		try {
			WriteLog.writeLogRequest(request, uuid, writeLogProvider, httpServlet, user);

			response = billingReferenceDataInfoService.getPoliciesListByPolicyType(request, uuid, controllerPath);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponsePoliciesList();
			response.setUuid(uuid);
			response.setErrorCode(ErrorCodeConstant.INTERNAL_FAILURE.toString());
			response.setMessage(ErrorMessageConstant.INTERNAL_FAILURE.toString().replace("{0}", e.getMessage()));

			WriteLog.writeLogError(e, writeLogProvider);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} finally {
			WriteLog.writeLogResponse(uuid, writeLogProvider, response.getErrorCode(), response.getMessage(),
					"policyTypeList",
					request.getPolicyTypeList() != null ? Arrays.asList(request.getPolicyTypeList()).toString() : null,
					ErrorSystemTypeConstant.INTX.toString());
		}
	}

}
