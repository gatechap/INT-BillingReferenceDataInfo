package th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.ces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tit.ei.log.interfaces.model.DBInputLogger;
import com.tit.ei.log.interfaces.provider.WriteLogProvider;
import com.tit.ei.log.util.DateTimeUtility;

import th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.log.WriteLog;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils.yaml.YAMLUtil;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.request.GetCycleControlListRequest;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.ces.query.BillingReferenceDataInfoCESQuery;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.ChargeCodeInfoDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.CycleControlInfoDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.CycleControlRowMapper;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.PoliciesListByPolicyTypeInfo;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.QueryAllSplitRuleListInfoDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.QueryLogicalDateInfoDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.QuerySplitRuleBySplitCodeInfoDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.ces.model.ChargeCodeInfoCESRowModelMapperDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.ces.model.PoliciesListByPolicyTypeCESRowMapper;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.ces.model.QueryAllSplitRuleListCESRowMapper;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.ces.model.QueryLogicalDateCESRowMapper;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.ces.model.QuerySplitRuleBySplitCodeCESRowmapper;
import th.truecorp.it.dsm.intspringstarter.errormessage.model.ErrorMessageInfo;

@Repository
public class BillingReferenceDataInfoCESDAO implements IBillingReferenceDataInfoCESDAO {

	@Autowired
	@Qualifier("dataSourceCES")
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<PoliciesListByPolicyTypeInfo> queryPoliciesListByPolicyTypeCES(List<String> policyTypeList, String uuid,
			String controllerPath, String correlatedId) throws Exception {

		int goldenDbVersion = YAMLUtil.getMyConfig().getSpring().getEnvironmentCtrl().getGoldenDbVersion();

		String sb = BillingReferenceDataInfoCESQuery.queryPoliciesListByPolicyTypeCES(goldenDbVersion);

		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		RowMapper<PoliciesListByPolicyTypeInfo> rowMapper = new PoliciesListByPolicyTypeCESRowMapper();

		parameterSource.addValue("policyTypeList", policyTypeList);

		// Write Log DB
		WriteLogProvider writeLogProvider = new WriteLogProvider(uuid, controllerPath, correlatedId,
				YAMLUtil.getMyConfig().getSpring().getProfiles());

		long startTimeMillisecs = System.currentTimeMillis();
		List<PoliciesListByPolicyTypeInfo> result = this.jdbcTemplate.query(sb.toString(), parameterSource, rowMapper);
		long endTimeMillisecs = System.currentTimeMillis();

		WriteLog.writeLogDB(writeLogProvider, "/queryPoliciesListByPolicyTypeCES", parameterSource, "Success",
				"dataSourceCES", startTimeMillisecs, endTimeMillisecs);

		return result;
	}

	@Override
	public List<QueryAllSplitRuleListInfoDAO> queryAllSplitRuleListCES(String logical_date, String uuid,
			String controllerPath, String correlatedId) throws SQLException, Exception {

		int goldenDbVersion = YAMLUtil.getMyConfig().getSpring().getEnvironmentCtrl().getGoldenDbVersion();
		List<QueryAllSplitRuleListInfoDAO> resultList = new ArrayList<QueryAllSplitRuleListInfoDAO>();
		final String paramSql = BillingReferenceDataInfoCESQuery.queryAllSplitRuleListCES(goldenDbVersion);
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		String methodName = "getSplitRuleList";

		parameterSource.addValue("logicalDate1", logical_date);
		parameterSource.addValue("logicalDate2", logical_date);

		RowMapper<QueryAllSplitRuleListInfoDAO> rowMapper = new QueryAllSplitRuleListCESRowMapper();
		WriteLogProvider writeLogProvider = new WriteLogProvider(uuid, controllerPath, correlatedId,
				YAMLUtil.getMyConfig().getSpring().getProfiles());

		DBInputLogger dbInputLogger = new DBInputLogger();
		dbInputLogger.setSourceEP("/queryAllSplitRuleListCES");
		dbInputLogger.setTargetEP(YAMLUtil.getMyConfig().getSpring().getDatasource().getUrl());
		dbInputLogger.setParameters(getParameterString(parameterSource));

		resultList = this.jdbcTemplate.query(paramSql, parameterSource, rowMapper);

		dbInputLogger.setResultStatus("Success");
		DateTimeUtility dateTimeUtilityDB = new DateTimeUtility();
		dbInputLogger.setDateCompletion(dateTimeUtilityDB.getCurrentISO8601());
		writeLogProvider.writeLogDB(dbInputLogger);

		return resultList;
	}

	@Override
	public List<QuerySplitRuleBySplitCodeInfoDAO> querySplitRuleBySplitCodeCES(String splitCode, String logical_date,
			String uuid, String controllerPath, String correlatedId) throws SQLException, Exception {
		int goldenDbVersion = YAMLUtil.getMyConfig().getSpring().getEnvironmentCtrl().getGoldenDbVersion();
		List<QuerySplitRuleBySplitCodeInfoDAO> resultList = new ArrayList<QuerySplitRuleBySplitCodeInfoDAO>();
		final String paramSql = BillingReferenceDataInfoCESQuery.querySplitRuleBySplitCodeCES(goldenDbVersion);
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		String methodName = "getSplitRuleList";

		parameterSource.addValue("splitCode", splitCode);
		parameterSource.addValue("logicalDate1", logical_date);
		parameterSource.addValue("logicalDate2", logical_date);

		RowMapper<QuerySplitRuleBySplitCodeInfoDAO> rowMapper = new QuerySplitRuleBySplitCodeCESRowmapper();
		WriteLogProvider writeLogProvider = new WriteLogProvider(uuid, controllerPath, correlatedId,
				YAMLUtil.getMyConfig().getSpring().getProfiles());

		DBInputLogger dbInputLogger = new DBInputLogger();
		dbInputLogger.setSourceEP("/querySplitRuleBySplitCodeCES");
		dbInputLogger.setTargetEP(YAMLUtil.getMyConfig().getSpring().getDatasource().getUrl());
		dbInputLogger.setParameters(getParameterString(parameterSource));

		resultList = this.jdbcTemplate.query(paramSql, parameterSource, rowMapper);

		dbInputLogger.setResultStatus("Success");
		DateTimeUtility dateTimeUtilityDB = new DateTimeUtility();
		dbInputLogger.setDateCompletion(dateTimeUtilityDB.getCurrentISO8601());
		writeLogProvider.writeLogDB(dbInputLogger);

		return resultList;
	}

	@Override
	public List<QueryLogicalDateInfoDAO> queryLogicalDateCES(String uuid, String controllerPath, String correlatedId)
			throws SQLException, Exception {
		int goldenDbVersion = YAMLUtil.getMyConfig().getSpring().getEnvironmentCtrl().getGoldenDbVersion();
		List<QueryLogicalDateInfoDAO> resultList = new ArrayList<QueryLogicalDateInfoDAO>();
		final String paramSql = BillingReferenceDataInfoCESQuery.queryLogicalDateCES(goldenDbVersion);
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		String methodName = "getSplitRuleList";

		RowMapper<QueryLogicalDateInfoDAO> rowMapper = new QueryLogicalDateCESRowMapper();
		WriteLogProvider writeLogProvider = new WriteLogProvider(uuid, controllerPath, correlatedId,
				YAMLUtil.getMyConfig().getSpring().getProfiles());

		DBInputLogger dbInputLogger = new DBInputLogger();
		dbInputLogger.setSourceEP("/queryLogicalDateCES");
		dbInputLogger.setTargetEP(YAMLUtil.getMyConfig().getSpring().getDatasource().getUrl());
		dbInputLogger.setParameters(getParameterString(parameterSource));

		resultList = this.jdbcTemplate.query(paramSql, parameterSource, rowMapper);

		dbInputLogger.setResultStatus("Success");
		DateTimeUtility dateTimeUtilityDB = new DateTimeUtility();
		dbInputLogger.setDateCompletion(dateTimeUtilityDB.getCurrentISO8601());
		writeLogProvider.writeLogDB(dbInputLogger);

		return resultList;
	}

	@Override
	public ChargeCodeInfoDAO getChargeCodeInfoCES(String chargeCode, String uuid) throws Exception {

		int goldenDbVersion = YAMLUtil.getMyConfig().getSpring().getEnvironmentCtrl().getGoldenDbVersion();
		final String sql = BillingReferenceDataInfoCESQuery.getChargeCodeInfoCES(goldenDbVersion);

		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		RowMapper<ChargeCodeInfoDAO> rowMapper = new ChargeCodeInfoCESRowModelMapperDAO();

		parameterSource.addValue("chargeCode", chargeCode);

		// Write Log DB
		WriteLogProvider writeLogProvider = new WriteLogProvider(uuid, "/getImmediateChargeCodeInfo", uuid,
				YAMLUtil.getMyConfig().getSpring().getProfiles());
		DBInputLogger dbInputLogger = new DBInputLogger();
		dbInputLogger.setSourceEP("/getChargeCodeInfoCES");
		dbInputLogger.setTargetEP(YAMLUtil.getMyConfig().getSpring().getDatasource().getUrl());
		dbInputLogger.setParameters(getParameterString(parameterSource));

		List<ChargeCodeInfoDAO> list = this.jdbcTemplate.query(sql.toString(), parameterSource, rowMapper);
		dbInputLogger.setResultStatus("Success");
		DateTimeUtility dateTimeUtilityDB = new DateTimeUtility();
		dbInputLogger.setDateCompletion(dateTimeUtilityDB.getCurrentISO8601());
		writeLogProvider.writeLogDB(dbInputLogger);

		ChargeCodeInfoDAO result = new ChargeCodeInfoDAO();
		if (list != null && list.size() > 0 && list.get(0) != null) {
			result = list.get(0);
		} else {
			result = null;
		}

		return result;

	}

	@Override
	public List<CycleControlInfoDAO> getCycleControlListCES(GetCycleControlListRequest request, String uuid)
			throws Exception {
		int goldenDbVersion = YAMLUtil.getMyConfig().getSpring().getEnvironmentCtrl().getGoldenDbVersion();
		final String sql = BillingReferenceDataInfoCESQuery.getCycleControlListCES(goldenDbVersion);
		List<CycleControlInfoDAO> result = new ArrayList<>();

		final String sourceEP = "/queryGetCycleControlList";
		final long START_TIME_METHOD = System.currentTimeMillis();
		long startTimeMillisecs = 0;
		long endTimeMillisecs = 0;

		String resultStatus = null;
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		ErrorMessageInfo messageInfo = null;
		try {

			RowMapper<CycleControlInfoDAO> rowMapper = new CycleControlRowMapper();
			parameterSource.addValue("cycleMonth", request.getCycleMonth());
			parameterSource.addValue("cycleYear", request.getCycleYear());

			// Write Log DB
			WriteLogProvider writeLogProvider = new WriteLogProvider(uuid, "/getCycleControlList", uuid,
					YAMLUtil.getMyConfig().getSpring().getProfiles());
			DBInputLogger dbInputLogger = new DBInputLogger();
			dbInputLogger.setSourceEP("/getCycleControlList");
			dbInputLogger.setTargetEP(YAMLUtil.getMyConfig().getSpring().getDatasource().getUrl());
			dbInputLogger.setParameters(getParameterString(parameterSource));

			result = this.jdbcTemplate.query(sql, parameterSource, rowMapper);
			dbInputLogger.setResultStatus("Success");
			DateTimeUtility dateTimeUtilityDB = new DateTimeUtility();
			dbInputLogger.setDateCompletion(dateTimeUtilityDB.getCurrentISO8601());
			writeLogProvider.writeLogDB(dbInputLogger);
			if (result != null) {
				resultStatus = "S";
			} else {
				resultStatus = "NA";
			}
		}

		catch (Exception e) {
			resultStatus = "F";
			throw new Exception(e);
		}

		return result;

	}

	public String getParameterString(MapSqlParameterSource parameterSource) {
		String result = "";
		for (String name : parameterSource.getParameterNames()) {
			result += "," + name + "="
					+ (parameterSource.getValue(name) != null ? (String) parameterSource.getValue(name).toString()
							: "");
		}
		if (result != null && result.trim().length() > 0) {
			result = result.substring(1);
		}
		return result;
	}

}
