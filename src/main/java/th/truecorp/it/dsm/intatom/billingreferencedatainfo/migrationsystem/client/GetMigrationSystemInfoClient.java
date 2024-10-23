package th.truecorp.it.dsm.intatom.billingreferencedatainfo.migrationsystem.client;

import com.tit.ei.log.interfaces.model.ErrorInputLogger;
import com.tit.ei.log.interfaces.model.LegacyInputLogger_ObjectRequest;
import com.tit.ei.log.interfaces.provider.WriteLogProvider;
import com.tit.ei.log.util.DateTimeUtility;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils.yaml.YAMLUtil;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils.yaml.model.ServiceClient;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.migrationsystem.model.RequestGetMigrationSystemInfo;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.migrationsystem.model.ResponseGetMigrationSystemInfo;
import th.truecorp.it.dsm.intspringstarter.errormessage.controller.ErrorMessageController;
import th.truecorp.it.dsm.intspringstarter.errormessage.model.ErrorMessageInfo;
import th.truecorp.it.esd.ei.commonlib.utils.StringUtils;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;

@Service
public class GetMigrationSystemInfoClient implements IGetMigrationSystemInfoClient {

	@Override
	public ResponseGetMigrationSystemInfo getMigrationSystemInfo(RequestGetMigrationSystemInfo request,
																 String correlatedId, String controllerPath, String uuid) throws Exception {

		WriteLogProvider writeLogProvider = new WriteLogProvider(uuid, controllerPath, correlatedId,
				YAMLUtil.getMyConfig().getSpring().getProfiles());

		ResponseEntity<ResponseGetMigrationSystemInfo> responseCall = new ResponseEntity<ResponseGetMigrationSystemInfo>(
				HttpStatus.INTERNAL_SERVER_ERROR);

		ResponseGetMigrationSystemInfo response = null;

		ServiceClient serviceClient = YAMLUtil.getMyConfig().getServiceObj("getMigrationSystemInfo");

		String url = null;
		Date startRequest = null;
		Date endRequest = null;
		try {
			url = serviceClient.getEndpoint();
			String user = serviceClient.getUser();
			String pass = serviceClient.getPassword();

			HttpHeaders header = new HttpHeaders();
			header.setContentType(MediaType.APPLICATION_JSON);
			header.setBasicAuth(user, pass);

			HttpEntity<RequestGetMigrationSystemInfo> entityRequest = new HttpEntity<>(request, header);

			startRequest = Calendar.getInstance().getTime();

			responseCall = restTemplate(serviceClient.getConnectionTimeout(),serviceClient.getReadTimeout()).exchange(
					url,
					HttpMethod.POST,
					entityRequest,
					new ParameterizedTypeReference<ResponseGetMigrationSystemInfo>() {
					});
			endRequest = Calendar.getInstance().getTime();

			response = responseCall.getBody();

		} catch (Exception e) {
			writeLogError(e, writeLogProvider);
			throw new Exception(e);
		} finally {

			// write log Legacy
			writeLogLegacyJSON(writeLogProvider, request, response,
					String.valueOf(responseCall.getStatusCodeValue()), responseCall.getStatusCode().name(), url,
					startRequest, endRequest);
		}

		return response;
	}

	public static void writeLogError(Exception e, WriteLogProvider writeLogProvider) throws Exception {
		DateTimeUtility dateTimeUtilityReq = new DateTimeUtility();
		ErrorMessageInfo errorInfo = ErrorMessageController.getInternalFailureMessageInfo(e.getMessage().toString());
		String message = StringUtils.convertStackTraceToString(e);
		ErrorInputLogger errLog = new ErrorInputLogger();
		errLog.setErrorCode(errorInfo.getErrorCode());
		errLog.setMessage(errorInfo.getMessage());
		errLog.setStackTrace(message.replaceAll("['\\r','\\n','\\t']", ""));
		errLog.setDateCompletion(dateTimeUtilityReq.getCurrentISO8601());
		writeLogProvider.writeLogError(errLog);
	}

	public static void writeLogLegacyJSON(WriteLogProvider writeLogProvider, Object request, Object responseCall,
                                          String httpResponseCode, String httpResponseMessage, String url, Date startRequest, Date endRequest)
			throws Exception {
		DateTimeUtility dateTimeUtilityReq = new DateTimeUtility();
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

	public static RestTemplate restTemplate(int connectionTimeout, int readTimeout) throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();

		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

		requestFactory.setHttpClient(httpClient);
		requestFactory.setConnectTimeout(connectionTimeout);
		requestFactory.setReadTimeout(readTimeout);

		RestTemplate restTemplate = new RestTemplate(requestFactory);
		return restTemplate;
	}

}
