package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.log;

import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import com.tit.ei.log.interfaces.model.DBInputLogger;
import com.tit.ei.log.interfaces.model.ErrorInputLogger;
import com.tit.ei.log.interfaces.model.LegacyInputLogger_ObjectRequest;
import com.tit.ei.log.interfaces.model.RequestInputLogger;
import com.tit.ei.log.interfaces.model.ResponseInputLogger;
import com.tit.ei.log.interfaces.provider.WriteLogProvider;
import com.tit.ei.log.util.DateTimeUtility;

import th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils.DAOUtils;
import th.truecorp.it.dsm.intspringstarter.errormessage.constant.ErrorCodeConstant;
import th.truecorp.it.dsm.intspringstarter.errormessage.constant.ErrorMessageConstant;
import th.truecorp.it.dsm.intspringstarter.errormessage.constant.ErrorSystemTypeConstant;
import th.truecorp.it.dsm.intspringstarter.errormessage.controller.ErrorMessageController;
import th.truecorp.it.dsm.intspringstarter.errormessage.model.ErrorMessageInfo;
import th.truecorp.it.esd.ei.commonlib.utils.StringUtils;

public class WriteLog {

	static DateTimeUtility dateTimeUtilityReq = new DateTimeUtility();

	public static void writeLogLegacyJSON(WriteLogProvider writeLogProvider, Object request, Object responseCall,
			String httpResponseCode, String httpResponseMessage, String url, Date startRequest, Date endRequest)
			throws Exception {

		LegacyInputLogger_ObjectRequest legacyInputLogger = new LegacyInputLogger_ObjectRequest();
		legacyInputLogger.setRequests(request);
		legacyInputLogger.setStartRequest(startRequest != null ? startRequest : new Date());
		legacyInputLogger.setTargetEp(url);
		legacyInputLogger.setHttpResponseCode(httpResponseCode);
		legacyInputLogger.setHttpResponseMessage(httpResponseMessage);
		legacyInputLogger.setResponse(responseCall);
		legacyInputLogger.setEndRequest(endRequest != null ? endRequest : new Date());
		legacyInputLogger.setDateCompletion(dateTimeUtilityReq.getCurrentISO8601());

		writeLogProvider.writeLogLegacy_JSON(legacyInputLogger);
	}
	
	public static void writeLogRequest(Object request, String uuid, WriteLogProvider writeLogProvider,
			HttpServletRequest httpServlet, String user) throws Exception {

		RequestInputLogger logReq = new RequestInputLogger();
		logReq.setStartTime(System.currentTimeMillis());
		logReq.setUuid(uuid);
		logReq.setUsername(user);
		logReq.setLegacyUsername(httpServlet.getHeader("X-LegacyUsername"));
		logReq.setInputParamLogObj(request);
		logReq.setChannel(httpServlet.getHeader("X-Channel"));
		logReq.setDateCompletion(dateTimeUtilityReq.getCurrentISO8601());
		writeLogProvider.writeLogRequest(logReq);
	}

	public static void writeLogResponse(String uuid, WriteLogProvider writeLogProvider, String errorCode,
			String message, String mainInputKey, String mainInputValue, String system) throws Exception {
		String resultStatus;
		ResponseInputLogger logRes = new ResponseInputLogger();

		logRes.setUuid(uuid);
		logRes.setErrorCode(errorCode);
		logRes.setMessage(message);
		if (system.equals(ErrorSystemTypeConstant.OSB.toString())) {
			if (errorCode.equals(ErrorCodeConstant.OSB_SUCCESS.toString())) {
				resultStatus = "S";
			} else if (errorCode.equals(ErrorCodeConstant.OSB_PARAMETER_IS_INVALID.toString())
					|| errorCode.equals(ErrorCodeConstant.OSB_INVALID_ONLY_ONE_GROUP.toString())
					|| errorCode.equals(ErrorCodeConstant.OSB_DATA_NOT_FOUND.toString())
					|| errorCode.equals(ErrorCodeConstant.OSB_PAREMETER_IS_REQUIRED.toString())
					|| errorCode.equals(ErrorCodeConstant.OSB_CAN_NOT_GREATER_THAN.toString())
					|| errorCode.equals(ErrorCodeConstant.OSB_CAN_NOT_LESS_THAN.toString())) {
				resultStatus = "NA";
			} else {
				resultStatus = "F";
			}
		} else {
			if (errorCode.equals(ErrorCodeConstant.SUCCESS.toString())) {
				resultStatus = "S";
			} else if (errorCode.equals(ErrorCodeConstant.PARAMETER_IS_INVALID.toString())
					|| errorCode.equals(ErrorCodeConstant.INVALID_ONLY_ONE_GROUP.toString())
					|| errorCode.equals(ErrorCodeConstant.DATA_NOT_FOUND.toString())
					|| errorCode.equals(ErrorCodeConstant.PAREMETER_IS_REQUIRED.toString())
					|| errorCode.equals(ErrorCodeConstant.CAN_NOT_GREATER_THAN.toString())
					|| errorCode.equals(ErrorCodeConstant.CAN_NOT_LESS_THAN.toString())) {
				resultStatus = "NA";
			} else {
				resultStatus = "F";
			}
		}
		logRes.setResultStatus(resultStatus);
		logRes.setDateCompletion(dateTimeUtilityReq.getCurrentISO8601());
		logRes.setMainInputKey(mainInputKey);
		logRes.setMainInputValue(mainInputValue);
		logRes.setEndTime(System.currentTimeMillis());
		writeLogProvider.writeLogResponse(logRes);
	}

	public static void writeLogDB(WriteLogProvider writeLogProvider, String sourceEP,
			MapSqlParameterSource parameterSource, String resultStatus, String dataSourceUrl, long startTimeMillisecs,
			long endTimeMillisecs) throws Exception {

		DBInputLogger dbInputLogger = new DBInputLogger();
		DateTimeUtility dateTimeUtilityDB = new DateTimeUtility();
		dbInputLogger.setSourceEP(sourceEP);
		dbInputLogger.setTargetEP(dataSourceUrl);
		dbInputLogger.setParameters(DAOUtils.getParameterString(parameterSource));
		dbInputLogger.setResultStatus(resultStatus);
		dbInputLogger.setDateCompletion(dateTimeUtilityDB.getCurrentISO8601());
		dbInputLogger.setStartTimeMillisecs(startTimeMillisecs);
		dbInputLogger.setEndTimeMillisecs(endTimeMillisecs);
		writeLogProvider.writeLogDB(dbInputLogger);
	}

	public static void writeLogError(Exception e, WriteLogProvider writeLogProvider) throws Exception {
		ErrorMessageInfo errorInfo = ErrorMessageController.getInternalFailureMessageInfo(e.getMessage().toString());
		String message = StringUtils.convertStackTraceToString(e);
		ErrorInputLogger errLog = new ErrorInputLogger();
		errLog.setErrorCode(errorInfo.getErrorCode());
		errLog.setMessage(errorInfo.getMessage());
		errLog.setStackTrace(message.replaceAll("['\\r','\\n','\\t']", ""));
		errLog.setDateCompletion(dateTimeUtilityReq.getCurrentISO8601());
		writeLogProvider.writeLogError(errLog);
	}
	
	public static ErrorMessageInfo writeLogInternalFailer(Exception ex, String uuid, String controlPath,
			String correlatedId, String profile) {
		ErrorMessageInfo result = null;
		try {
			result = ErrorMessageController.getInternalFailureMessageInfo(ex.getMessage());
			ErrorInputLogger errLog = new ErrorInputLogger();
			DateTimeUtility dateTimeUtilityReq = new DateTimeUtility();

			errLog.setMessage(result.getMessage());
			errLog.setErrorCode(result.getErrorCode());
			errLog.setResultStatus("F");
			errLog.setDateCompletion(dateTimeUtilityReq.getCurrentISO8601());

			String errorStackTrace = Arrays.toString(ex.getStackTrace());
			errLog.setStackTrace(errorStackTrace);

			WriteLogProvider log = new WriteLogProvider(uuid, controlPath, correlatedId, profile);
			log.writeLogError(errLog);
		} catch (Exception e) {
			e.printStackTrace();

			if (result == null) {
				result = new ErrorMessageInfo(ErrorCodeConstant.INTERNAL_FAILURE,
						ErrorMessageConstant.INTERNAL_FAILURE);
			}
		}

		return result;
	} // end method
}
