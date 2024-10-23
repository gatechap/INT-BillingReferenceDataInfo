package th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao;

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
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.ChargeCodeInfoDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.ChargeCodeInfoRowModelMapperDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.CycleControlInfoDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.CycleControlRowMapper;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.PoliciesListByPolicyTypeInfo;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.PoliciesListByPolicyTypeRowMapper;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.QueryAllSplitRuleListInfoDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.QueryAllSplitRuleListRowMapper;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.QueryLogicalDateInfoDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.QueryLogicalDateRowMapper;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.QuerySplitRuleBySplitCodeInfoDAO;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.QuerySplitRuleBySplitCodeRowmapper;
import th.truecorp.it.dsm.intspringstarter.errormessage.model.ErrorMessageInfo;

@Repository
public class BillingReferenceDataInfoDAO implements IBillingReferenceDataInfoDAO {

	@Autowired
	@Qualifier("OracleProdBilling")
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public List<PoliciesListByPolicyTypeInfo> queryPoliciesListByPolicyType(List<String> policyTypeList, String uuid,
			String controllerPath, String correlatedId) throws Exception {
		StringBuilder sb = new StringBuilder();

		sb.append(" SELECT DESCRIPTION ");
		sb.append(" 	,LANGUAGE ");
		sb.append(" 	,POLICY_ID ");
		sb.append(" 	,POLICY_NAME ");
		sb.append(" 	,POLICY_QUALIFIER ");
		sb.append(" 	,POLICY_TYPE ");
		sb.append(" 	,POLICY_VALUE ");
		sb.append(" FROM AR1_POLICIES ");
		sb.append(" WHERE POLICY_TYPE IN ( :policyTypeList ) ");

		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		RowMapper<PoliciesListByPolicyTypeInfo> rowMapper = new PoliciesListByPolicyTypeRowMapper();

		parameterSource.addValue("policyTypeList", policyTypeList);

		// Write Log DB
		WriteLogProvider writeLogProvider = new WriteLogProvider(uuid, controllerPath, correlatedId,
				YAMLUtil.getMyConfig().getSpring().getProfiles());

		long startTimeMillisecs = System.currentTimeMillis();
		List<PoliciesListByPolicyTypeInfo> result = this.jdbcTemplate.query(sb.toString(), parameterSource, rowMapper);
		long endTimeMillisecs = System.currentTimeMillis();

		WriteLog.writeLogDB(writeLogProvider, "/queryPoliciesListByPolicyType", parameterSource, "Success",
				"OracleProdBilling", startTimeMillisecs, endTimeMillisecs);

		return result;
	}

	@Override
	public List<QueryAllSplitRuleListInfoDAO> queryAllSplitRuleList(String logical_date, String uuid,
			String controllerPath, String correlatedId) throws SQLException, Exception {

		List<QueryAllSplitRuleListInfoDAO> resultList = new ArrayList<QueryAllSplitRuleListInfoDAO>();
		StringBuilder paramSql = new StringBuilder();
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		String methodName = "getSplitRuleList";

		paramSql.append(" SELECT       SPLIT_RULE.SPLIT_CODE,      SPLIT_RULE.SPLIT_LEVEL,");
		paramSql.append("      SPLIT_RULE.SPLIT_VALUE,      SPLIT_RULE.INCLUDE_IND,   ");
		paramSql.append("  SPLIT_RULE.SPLIT_WAY,      SPLIT_RULE.QUANTITY,      ");
		paramSql.append(" SPLIT_RULE.WORK_IND,      SPLIT_RULE.EXCEED_IND,    ");
		paramSql.append("  SPLIT_RULE.SPLIT_DESCRIPTION,    ");
		paramSql.append(
				"   TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(SPLIT_RULE.SPLIT_EFFECTIVE_DATE,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') EFFECTIVE_DATE,   ");
		paramSql.append(
				"  TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(SPLIT_RULE.SPLIT_EXPIRATION_DATE,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') EXPIRATION_DATE  ");
		paramSql.append("  FROM BL9_SPLIT_RULE_INFO SPLIT_RULE  ");
		paramSql.append("  WHERE SPLIT_RULE.SPLIT_EFFECTIVE_DATE <= TO_DATE(:logicalDate1, 'DD/MM/YYYY') ");
		paramSql.append("  AND (SPLIT_RULE.SPLIT_EXPIRATION_DATE IS NULL ");
		paramSql.append("  OR SPLIT_RULE.SPLIT_EXPIRATION_DATE >= TO_DATE(:logicalDate2, 'DD/MM/YYYY')) ");
		paramSql.append("  ORDER BY SPLIT_RULE.SPLIT_CODE,SPLIT_RULE.SPLIT_DESCRIPTION ");

		parameterSource.addValue("logicalDate1", logical_date);
		parameterSource.addValue("logicalDate2", logical_date);

		RowMapper<QueryAllSplitRuleListInfoDAO> rowMapper = new QueryAllSplitRuleListRowMapper();
		WriteLogProvider writeLogProvider = new WriteLogProvider(uuid, controllerPath, correlatedId,
				YAMLUtil.getMyConfig().getSpring().getProfiles());

		DBInputLogger dbInputLogger = new DBInputLogger();
		dbInputLogger.setSourceEP("/getSplitRuleList");
		dbInputLogger.setTargetEP(YAMLUtil.getMyConfig().getSpring().getDatasource().getUrl());
		dbInputLogger.setParameters(getParameterString(parameterSource));

		resultList = this.jdbcTemplate.query(paramSql.toString(), parameterSource, rowMapper);

		dbInputLogger.setResultStatus("Success");
		DateTimeUtility dateTimeUtilityDB = new DateTimeUtility();
		dbInputLogger.setDateCompletion(dateTimeUtilityDB.getCurrentISO8601());
		writeLogProvider.writeLogDB(dbInputLogger);

		return resultList;
	}

	@Override
	public List<QuerySplitRuleBySplitCodeInfoDAO> querySplitRuleBySplitCode(String splitCode, String logical_date,
			String uuid, String controllerPath, String correlatedId) throws SQLException, Exception {

		List<QuerySplitRuleBySplitCodeInfoDAO> resultList = new ArrayList<QuerySplitRuleBySplitCodeInfoDAO>();
		StringBuilder paramSql = new StringBuilder();
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		String methodName = "getSplitRuleList";

		paramSql.append(" SELECT       SPLIT_RULE.SPLIT_CODE,      SPLIT_RULE.SPLIT_LEVEL,");
		paramSql.append("      SPLIT_RULE.SPLIT_VALUE,      SPLIT_RULE.INCLUDE_IND,   ");
		paramSql.append("  SPLIT_RULE.SPLIT_WAY,      SPLIT_RULE.QUANTITY,      ");
		paramSql.append(" SPLIT_RULE.WORK_IND,      SPLIT_RULE.EXCEED_IND,    ");
		paramSql.append("  SPLIT_RULE.SPLIT_DESCRIPTION,    ");
		paramSql.append(
				"  TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(SPLIT_RULE.SPLIT_EFFECTIVE_DATE,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') EFFECTIVE_DATE,   ");
		paramSql.append(
				"  TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(SPLIT_RULE.SPLIT_EXPIRATION_DATE,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') EXPIRATION_DATE  ");
		paramSql.append("  FROM BL9_SPLIT_RULE_INFO SPLIT_RULE  ");
		paramSql.append(" WHERE SPLIT_RULE.SPLIT_CODE = :splitCode  AND   ");
		paramSql.append("  SPLIT_RULE.SPLIT_EFFECTIVE_DATE <= TO_DATE(:logicalDate1, 'DD/MM/YYYY')  ");
		paramSql.append("  AND (SPLIT_RULE.SPLIT_EXPIRATION_DATE IS NULL ");
		paramSql.append("  OR SPLIT_RULE.SPLIT_EXPIRATION_DATE >= TO_DATE(:logicalDate2, 'DD/MM/YYYY')) ");
		paramSql.append("  ORDER BY SPLIT_RULE.SPLIT_CODE,SPLIT_RULE.SPLIT_DESCRIPTION ");

		parameterSource.addValue("splitCode", splitCode);
		parameterSource.addValue("logicalDate1", logical_date);
		parameterSource.addValue("logicalDate2", logical_date);

		RowMapper<QuerySplitRuleBySplitCodeInfoDAO> rowMapper = new QuerySplitRuleBySplitCodeRowmapper();
		WriteLogProvider writeLogProvider = new WriteLogProvider(uuid, controllerPath, correlatedId,
				YAMLUtil.getMyConfig().getSpring().getProfiles());

		DBInputLogger dbInputLogger = new DBInputLogger();
		dbInputLogger.setSourceEP("/getSplitRuleList");
		dbInputLogger.setTargetEP(YAMLUtil.getMyConfig().getSpring().getDatasource().getUrl());
		dbInputLogger.setParameters(getParameterString(parameterSource));

		resultList = this.jdbcTemplate.query(paramSql.toString(), parameterSource, rowMapper);

		dbInputLogger.setResultStatus("Success");
		DateTimeUtility dateTimeUtilityDB = new DateTimeUtility();
		dbInputLogger.setDateCompletion(dateTimeUtilityDB.getCurrentISO8601());
		writeLogProvider.writeLogDB(dbInputLogger);

		return resultList;
	}

	@Override
	public List<QueryLogicalDateInfoDAO> queryLogicalDate(String uuid, String controllerPath, String correlatedId)
			throws SQLException, Exception {

		List<QueryLogicalDateInfoDAO> resultList = new ArrayList<QueryLogicalDateInfoDAO>();
		StringBuilder paramSql = new StringBuilder();
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		String methodName = "getSplitRuleList";

		paramSql.append(" select to_char(logical_date,'DD/MM/YYYY') logical_date ");
		paramSql.append(" from logical_date where logical_date_type = 'O' and expiration_date is null ");

		RowMapper<QueryLogicalDateInfoDAO> rowMapper = new QueryLogicalDateRowMapper();
		WriteLogProvider writeLogProvider = new WriteLogProvider(uuid, controllerPath, correlatedId,
				YAMLUtil.getMyConfig().getSpring().getProfiles());

		DBInputLogger dbInputLogger = new DBInputLogger();
		dbInputLogger.setSourceEP("/getSplitRuleList");
		dbInputLogger.setTargetEP(YAMLUtil.getMyConfig().getSpring().getDatasource().getUrl());
		dbInputLogger.setParameters(getParameterString(parameterSource));

		resultList = this.jdbcTemplate.query(paramSql.toString(), parameterSource, rowMapper);

		dbInputLogger.setResultStatus("Success");
		DateTimeUtility dateTimeUtilityDB = new DateTimeUtility();
		dbInputLogger.setDateCompletion(dateTimeUtilityDB.getCurrentISO8601());
		writeLogProvider.writeLogDB(dbInputLogger);

		return resultList;
	}

	@Override
	public ChargeCodeInfoDAO getChargeCodeInfo(String chargeCode, String uuid) throws Exception {

		StringBuilder sql = new StringBuilder();
		sql.append(
				" SELECT    AR1_CHARGE_CODE.CHARGE_CODE, AR1_CHARGE_CODE.CHARGE_CODE_DESC,AR1_CHARGE_CODE.REVENUE_CODE,AR1_CHARGE_CODE.GROUP_TYPE,AR1_CHARGE_CODE.L9_BILL_PRESENT_IND     ,MESSAGE_TEXT_TH.message_text Bill_TH,MESSAGE_TEXT_EN.message_text Bill_EN   ");
		sql.append(
				" FROM     AR1_CHARGE_CODE, BL1_CHARGE_CODE,BL1_MESSAGE_TEXT MESSAGE_TEXT_TH,BL1_MESSAGE_TEXT MESSAGE_TEXT_EN   ");
		sql.append(" WHERE    AR1_CHARGE_CODE.CHARGE_CODE = :chargeCode  ");
		sql.append(
				" AND AR1_CHARGE_CODE.REVENUE_CODE IN ( 'OC' , 'RC' )    AND AR1_CHARGE_CODE.GROUP_TYPE  IN ( 'I' ,'C' ,'P')  ");
		sql.append(
				" AND AR1_CHARGE_CODE.CHARGE_CODE = BL1_CHARGE_CODE.CHARGE_CODE    AND BL1_CHARGE_CODE.MESSAGE_CODE = MESSAGE_TEXT_TH.MESSAGE_CODE    AND BL1_CHARGE_CODE.MESSAGE_CODE = MESSAGE_TEXT_EN.MESSAGE_CODE    AND MESSAGE_TEXT_TH.MESSAGE_LANGUAGE ='TH' AND MESSAGE_TEXT_EN.MESSAGE_LANGUAGE='EN'  ");

		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		RowMapper<ChargeCodeInfoDAO> rowMapper = new ChargeCodeInfoRowModelMapperDAO();

		parameterSource.addValue("chargeCode", chargeCode);

		// Write Log DB
		WriteLogProvider writeLogProvider = new WriteLogProvider(uuid, "/getImmediateChargeCodeInfo", uuid,
				YAMLUtil.getMyConfig().getSpring().getProfiles());
		DBInputLogger dbInputLogger = new DBInputLogger();
		dbInputLogger.setSourceEP("/getImmediateChargeCodeInfo");
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
	public List<CycleControlInfoDAO> getCycleControlList(GetCycleControlListRequest request, String uuid)
			throws Exception {

		StringBuilder sql = new StringBuilder();
		List<CycleControlInfoDAO> result = new ArrayList<>();

		final String sourceEP = "/queryGetCycleControlList";
		final long START_TIME_METHOD = System.currentTimeMillis();
		long startTimeMillisecs = 0;
		long endTimeMillisecs = 0;

		String resultStatus = null;
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		ErrorMessageInfo messageInfo = null;
		try {
//        sql.append("	SELECT ");
//        sql.append("	    BILL_PRD_UP_LIMIT, ");
//        sql.append("	    TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(BILLING_REFERENCE,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') AS BILLING_REFERENCE, ");
//        sql.append("	    CSM_CYCLE.CYCLE_CODE, ");
//        sql.append("	    CYCLE_INSTANCE, ");
//        sql.append("	    CYCLE_SEQ_NO, ");
//        sql.append("	    CYCLE_YEAR, ");
//        sql.append("	    TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(RUN_DATE,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') AS RUN_DATE,  ");
//        sql.append("	    TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(START_DATE,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') AS START_DATE, ");
//        sql.append("	    TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(END_DATE,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') AS END_DATE, ");
//        sql.append("	    STATUS, ");
//        sql.append("	    CYCLE_DESC CYCLE_DESCRIPTION,       FREQUENCY CYCLE_FREQUENCY, BE CYCLE_BUSINESS_ENTITY , ");
//        sql.append("	    FREQUENCY_MULTIPLIER,  CYC_POPULATION_CD CYC_POPULATION_CODE ,'' CYCLE_BILL_DAY, CYCLE_CLOSE_DAY ");
//
			sql.append("	 SELECT       BL1_CYCLE_CODE.BILL_PRD_UP_LIMIT, ");
			sql.append(
					"	 TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(BL1_CYCLE_CODE.BILLING_REFERENCE,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') AS BILLING_REFERENCE, ");
			sql.append(
					"	 BL1_CYCLE_CODE.CYCLE_CODE,  BL1_CYCLE_CONTROL.CYCLE_INSTANCE, BL1_CYCLE_CONTROL.CYCLE_SEQ_NO, BL1_CYCLE_CONTROL.CYCLE_YEAR, ");
			sql.append(
					"	 TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(BL1_CYCLE_CONTROL.RUN_DATE,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') AS RUN_DATE,  ");
			sql.append(
					"	 TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(BL1_CYCLE_CONTROL.START_DATE,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') AS START_DATE,    ");
			sql.append(
					"	 TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(BL1_CYCLE_CONTROL.END_DATE,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') AS END_DATE,     ");
			sql.append("	 BL1_CYCLE_CONTROL.STATUS, BL1_CYCLE_CODE.CYCLE_DESC CYCLE_DESCRIPTION, ");
			sql.append("	 BL1_CYCLE_CODE.FREQUENCY CYCLE_FREQUENCY, BL1_CYCLE_CODE.BE CYCLE_BUSINESS_ENTITY ,  ");
			sql.append(
					"	 BL1_CYCLE_CODE.FREQUENCY_MULTIPLIER,  BL1_CYCLE_CODE.CYC_POPULATION_CODE ,BL1_CYCLE_CODE.CYCLE_BILL_DAY, BL1_CYCLE_CODE.CYCLE_CLOSE_DAY ");
			sql.append("	FROM ");
			sql.append("	    BL1_CYCLE_CODE, ");
			sql.append("	    BL1_CYCLE_CONTROL ");
			sql.append("	WHERE ");
			sql.append("	    BL1_CYCLE_CONTROL.CYCLE_INSTANCE = :cycleMonth ");
			sql.append("	    AND BL1_CYCLE_CONTROL.CYCLE_YEAR = :cycleYear ");
			sql.append("	    AND BL1_CYCLE_CODE.CYCLE_CODE = BL1_CYCLE_CONTROL.CYCLE_CODE ");
			sql.append("	ORDER BY ");
			sql.append("	    BL1_CYCLE_CODE.CYCLE_CODE  ");
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

			result = this.jdbcTemplate.query(sql.toString(), parameterSource, rowMapper);
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
