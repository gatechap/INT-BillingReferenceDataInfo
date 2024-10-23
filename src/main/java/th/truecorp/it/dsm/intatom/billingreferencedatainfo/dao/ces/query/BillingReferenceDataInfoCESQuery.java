package th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.ces.query;

public class BillingReferenceDataInfoCESQuery {

	public static String queryPoliciesListByPolicyTypeCES(int goldenDbVersion) {
		StringBuilder sb = new StringBuilder();

		if (goldenDbVersion == 1 || goldenDbVersion == 2) {

			sb.append(" SELECT DESCRIPTION ");
			sb.append(" 	,LANG ");
			sb.append(" 	,POLICYID ");
			sb.append(" 	,POLICYNAME ");
			sb.append(" 	,POLICYQUALIFIER ");
			sb.append(" 	,POLICYTYPE ");
			sb.append(" 	,POLICYVALUE ");
			sb.append(" FROM POLICIES ");
			sb.append(" WHERE POLICYTYPE IN ( :policyTypeList ) ");

		} else {
			// use new table/fields (waiting for billing team)
		}

		return sb.toString();
	}

	public static String queryAllSplitRuleListCES(int goldenDbVersion) {
		StringBuilder paramSql = new StringBuilder();

        // TABLE splitRule
		if (goldenDbVersion == 1) {

			paramSql.append(" SELECT        ");
            paramSql.append("     SPLIT_RULE.code AS SPLIT_CODE,       ");
            paramSql.append("     SPLIT_RULE.levels AS SPLIT_LEVEL, ");
            paramSql.append("     SPLIT_RULE.value AS SPLIT_VALUE,       ");
            paramSql.append("     SPLIT_RULE.includeInd AS INCLUDE_IND,    ");
            paramSql.append("     SPLIT_RULE.way AS SPLIT_WAY,       ");
            paramSql.append("     SPLIT_RULE.quant AS QUANTITY,       ");
            paramSql.append("     SPLIT_RULE.workInd AS WORK_IND,       ");
            paramSql.append("     SPLIT_RULE.exceedInd AS EXCEED_IND,     ");
            paramSql.append("     SPLIT_RULE.description AS SPLIT_DESCRIPTION,     ");
            paramSql.append("     TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(SPLIT_RULE.effDate,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') EFFECTIVE_DATE,    ");
            paramSql.append("     TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(SPLIT_RULE.expDate,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') EXPIRATION_DATE    ");
            paramSql.append(" FROM splitRule SPLIT_RULE   ");
            paramSql.append(" WHERE SPLIT_RULE.effDate <= TO_DATE(:logicalDate1, 'DD/MM/YYYY')  ");
            paramSql.append("     AND (SPLIT_RULE.expDate IS NULL  ");
            paramSql.append("     OR SPLIT_RULE.expDate >= TO_DATE(:logicalDate2, 'DD/MM/YYYY'))  ");
            paramSql.append(" ORDER BY SPLIT_RULE.code,SPLIT_RULE.description ");

        } else if (goldenDbVersion == 2){
            // use new table/fields (waiting for billing team)
            // SPLITRULE	CODE	SPLITRULE	SPLITCODE
            // SPLITRULE	EFFDATE	SPLITRULE	EFFECTIVEDATE
            // SPLITRULE	EXPDATE	SPLITRULE	EXPIRATIONDATE
            // SPLITRULE	VALUE	SPLITRULE	SPLITVALUE
            // SPLITRULE	WAY	SPLITRULE	SPLITWAY
            // SPLITRULE	QUANT	SPLITRULE	QUANTITY
            // SPLITRULE	LEVELS	SPLITRULE	SPLITLEVEL

            paramSql.append(" SELECT        ");
            paramSql.append("     SPLIT_RULE.SPLITCODE AS SPLIT_CODE,       ");
            paramSql.append("     SPLIT_RULE.SPLITLEVEL AS SPLIT_LEVEL, ");
            paramSql.append("     SPLIT_RULE.SPLITVALUE AS SPLIT_VALUE,       ");
            paramSql.append("     SPLIT_RULE.includeInd AS INCLUDE_IND,    ");
            paramSql.append("     SPLIT_RULE.SPLITWAY AS SPLIT_WAY,       ");
            paramSql.append("     SPLIT_RULE.QUANTITY AS QUANTITY,       ");
            paramSql.append("     SPLIT_RULE.workInd AS WORK_IND,       ");
            paramSql.append("     SPLIT_RULE.exceedInd AS EXCEED_IND,     ");
            paramSql.append("     SPLIT_RULE.description AS SPLIT_DESCRIPTION,     ");
            paramSql.append("     TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(SPLIT_RULE.EFFECTIVEDATE,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') EFFECTIVE_DATE,    ");
            paramSql.append("     TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(SPLIT_RULE.EXPIRATIONDATE,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') EXPIRATION_DATE    ");
            paramSql.append(" FROM splitRule SPLIT_RULE   ");
            paramSql.append(" WHERE SPLIT_RULE.EFFECTIVEDATE <= TO_DATE(:logicalDate1, 'DD/MM/YYYY')  ");
            paramSql.append("     AND (SPLIT_RULE.EXPIRATIONDATE IS NULL  ");
            paramSql.append("     OR SPLIT_RULE.EXPIRATIONDATE >= TO_DATE(:logicalDate2, 'DD/MM/YYYY'))  ");
            paramSql.append(" ORDER BY SPLIT_RULE.SPLITCODE,SPLIT_RULE.description ");



        } else {}

        return paramSql.toString();
    }

    public static String querySplitRuleBySplitCodeCES(int goldenDbVersion) {
        StringBuilder paramSql = new StringBuilder();

        if (goldenDbVersion == 1) {

            paramSql.append(" SELECT        ");
            paramSql.append("     SPLIT_RULE.code AS SPLIT_CODE,       ");
            paramSql.append("     SPLIT_RULE.levels AS SPLIT_LEVEL, ");
            paramSql.append("     SPLIT_RULE.value AS SPLIT_VALUE,       ");
            paramSql.append("     SPLIT_RULE.includeInd AS INCLUDE_IND,    ");
            paramSql.append("     SPLIT_RULE.way AS SPLIT_WAY,       ");
            paramSql.append("     SPLIT_RULE.quant AS QUANTITY,       ");
            paramSql.append("     SPLIT_RULE.workInd AS WORK_IND,       ");
            paramSql.append("     SPLIT_RULE.exceedInd AS EXCEED_IND,     ");
            paramSql.append("     SPLIT_RULE.description AS SPLIT_DESCRIPTION,     ");
            paramSql.append("     TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(SPLIT_RULE.effDate,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') EFFECTIVE_DATE,    ");
            paramSql.append("     TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(SPLIT_RULE.expDate,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') EXPIRATION_DATE    ");
            paramSql.append(" FROM splitRule SPLIT_RULE   ");
            paramSql.append(" WHERE SPLIT_RULE.code = :splitCode  ");
            paramSql.append(" 	AND SPLIT_RULE.effDate <= TO_DATE(:logicalDate1, 'DD/MM/YYYY')  ");
            paramSql.append("     AND (SPLIT_RULE.expDate IS NULL  ");
            paramSql.append("     OR SPLIT_RULE.expDate >= TO_DATE(:logicalDate2, 'DD/MM/YYYY'))  ");
            paramSql.append(" ORDER BY SPLIT_RULE.code,SPLIT_RULE.description ");
        } else if (goldenDbVersion == 2) {
            // use new table/fields (waiting for billing team)
            // SPLITRULE	CODE	SPLITRULE	SPLITCODE
            // SPLITRULE	EFFDATE	SPLITRULE	EFFECTIVEDATE
            // SPLITRULE	EXPDATE	SPLITRULE	EXPIRATIONDATE
            // SPLITRULE	VALUE	SPLITRULE	SPLITVALUE
            // SPLITRULE	WAY	SPLITRULE	SPLITWAY
            // SPLITRULE	QUANT	SPLITRULE	QUANTITY
            // SPLITRULE	LEVELS	SPLITRULE	SPLITLEVEL

            paramSql.append(" SELECT        ");
            paramSql.append("     SPLIT_RULE.SPLITCODE AS SPLIT_CODE,       ");
            paramSql.append("     SPLIT_RULE.SPLITLEVEL AS SPLIT_LEVEL, ");
            paramSql.append("     SPLIT_RULE.SPLITVALUE AS SPLIT_VALUE,       ");
            paramSql.append("     SPLIT_RULE.includeInd AS INCLUDE_IND,    ");
            paramSql.append("     SPLIT_RULE.SPLITWAY AS SPLIT_WAY,       ");
            paramSql.append("     SPLIT_RULE.QUANTITY AS QUANTITY,       ");
            paramSql.append("     SPLIT_RULE.workInd AS WORK_IND,       ");
            paramSql.append("     SPLIT_RULE.exceedInd AS EXCEED_IND,     ");
            paramSql.append("     SPLIT_RULE.description AS SPLIT_DESCRIPTION,     ");
            paramSql.append("     TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(SPLIT_RULE.EFFECTIVEDATE,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') EFFECTIVE_DATE,    ");
            paramSql.append("     TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(SPLIT_RULE.EXPIRATIONDATE,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') EXPIRATION_DATE    ");
            paramSql.append(" FROM splitRule SPLIT_RULE   ");
            paramSql.append(" WHERE SPLIT_RULE.SPLITCODE = :splitCode  ");
            paramSql.append(" 	AND SPLIT_RULE.EFFECTIVEDATE <= TO_DATE(:logicalDate1, 'DD/MM/YYYY')  ");
            paramSql.append("     AND (SPLIT_RULE.EXPIRATIONDATE IS NULL  ");
            paramSql.append("     OR SPLIT_RULE.EXPIRATIONDATE >= TO_DATE(:logicalDate2, 'DD/MM/YYYY'))  ");
            paramSql.append(" ORDER BY SPLIT_RULE.SPLITCODE,SPLIT_RULE.description ");
        } else {}

        return paramSql.toString();
    }

    public static String queryLogicalDateCES(int goldenDbVersion) {
        StringBuilder paramSql = new StringBuilder();

        if (goldenDbVersion == 1 || goldenDbVersion == 2) {

            paramSql.append(" select to_char(logicalDate,'DD/MM/YYYY') logical_date ");
            paramSql.append(" from logicalDate where logicalDateType = 'O' and expDate is null ");
        } else {
            // use new table/fields (waiting for billing team)
        }

        return paramSql.toString();
    }


    public static String getChargeCodeInfoCES(int goldenDbVersion) {
        StringBuilder sql = new StringBuilder();

        if (goldenDbVersion == 1) {

            sql.append(" SELECT  chargeCode.chargeCode AS CHARGE_CODE ");
            sql.append("     , chargeCode.description AS CHARGE_CODE_DESC ");
            sql.append("     , chargeCode.revenueCode AS REVENUE_CODE ");
            sql.append("     , chargeCode.groupType AS GROUP_TYPE ");
            sql.append("     , chargeCode.billPresentInd AS BILL_PRESENT_IND ");
            sql.append("     , MESSAGE_TEXT_TH.messageText AS Bill_TH ");
            sql.append("     , MESSAGE_TEXT_EN.messageText AS Bill_EN   ");
            sql.append(" FROM chargeCode ");
            sql.append("     , billChargeCode ");
            sql.append("     , message MESSAGE_TEXT_TH ");
            sql.append("     , message MESSAGE_TEXT_EN  ");
            sql.append(" WHERE chargeCode.chargeCode = :chargeCode  ");
            sql.append("     AND chargeCode.revenueCode IN ('OC','RC')     ");
            sql.append("     AND chargeCode.groupType  IN ('I','C','P')  ");
            sql.append("     AND chargeCode.chargeCode = billChargeCode.billChargeCode ");
            sql.append("     AND billChargeCode.messageCode = MESSAGE_TEXT_TH.messageCode    ");
            sql.append("     AND billChargeCode.messageCode = MESSAGE_TEXT_EN.messageCode  ");
            sql.append("     AND MESSAGE_TEXT_TH.messageLanguage ='TH' ");
            sql.append("     AND MESSAGE_TEXT_EN.messageLanguage ='EN' ");

        } else if (goldenDbVersion == 2)  {
            // use new table/fields (waiting for billing team)
            // CHARGECODE	DESCRIPTION	CHARGECODE	CHARGECODEDESC
            // CHARGECODE	MANUALCREATEDIND	CHARGECODE	MANUALOCCREATEIND
            // CHARGECODE	NEW FIELD NAME	CHARGECODE	LINEOFBUSINESS
            // BILLCHARGECODE	BILLCHARGECODE	BILLCHARGECODE	CHARGECODE
            // BILLCHARGECODE	NEW FIELD NAME	BILLCHARGECODE	LINEOFBUSINESS
            sql.append(" SELECT  chargeCode.chargeCode AS CHARGE_CODE ");
            sql.append("     , chargeCode.CHARGECODEDESC AS CHARGE_CODE_DESC ");
            sql.append("     , chargeCode.revenueCode AS REVENUE_CODE ");
            sql.append("     , chargeCode.groupType AS GROUP_TYPE ");
            sql.append("     , chargeCode.billPresentInd AS BILL_PRESENT_IND ");
            sql.append("     , MESSAGE_TEXT_TH.messageText AS Bill_TH ");
            sql.append("     , MESSAGE_TEXT_EN.messageText AS Bill_EN   ");
            sql.append(" FROM chargeCode ");
            sql.append("     , billChargeCode ");
            sql.append("     , message MESSAGE_TEXT_TH ");
            sql.append("     , message MESSAGE_TEXT_EN  ");
            sql.append(" WHERE chargeCode.chargeCode = :chargeCode  ");
            sql.append("     AND chargeCode.revenueCode IN ('OC','RC')     ");
            sql.append("     AND chargeCode.groupType  IN ('I','C','P')  ");
            sql.append("     AND chargeCode.chargeCode = billChargeCode.CHARGECODE ");
            sql.append("     AND billChargeCode.messageCode = MESSAGE_TEXT_TH.messageCode    ");
            sql.append("     AND billChargeCode.messageCode = MESSAGE_TEXT_EN.messageCode  ");
            sql.append("     AND MESSAGE_TEXT_TH.messageLanguage ='TH' ");
            sql.append("     AND MESSAGE_TEXT_EN.messageLanguage ='EN' ");
        } else {
        }

        return sql.toString();
    }


    public static String getCycleControlListCES(int goldenDbVersion) {
        StringBuilder sql = new StringBuilder();

        if (goldenDbVersion == 1) {

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
            sql.append(" SELECT billingCycle.limitBillingPeriod AS BILL_PRD_UP_LIMIT ");
            sql.append("     , TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(billingCycle.reference,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') AS BILLING_REFERENCE ");
            sql.append("     , billingCycle.cycleCode AS CYCLE_CODE ");
            sql.append("     , billingCycleControl.cycleInstance AS CYCLE_INSTANCE ");
            sql.append("     , billingCycleControl.cycleSeqNo AS CYCLE_SEQ_NO ");
            sql.append("     , billingCycleControl.cycleYear AS CYCLE_YEAR ");
            sql.append("     , TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(billingCycleControl.runDate,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') AS RUN_DATE ");
            sql.append("     , TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(billingCycleControl.startDate,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') AS START_DATE ");
            sql.append("     , TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(billingCycleControl.endDate,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') AS END_DATE ");
            sql.append("     , billingCycleControl.status AS STATUS ");
            sql.append("     , billingCycle.description AS CYCLE_DESCRIPTION ");
            sql.append("     , billingCycle.frequency AS CYCLE_FREQUENCY ");
            sql.append("     , billingCycle.businessEntity AS CYCLE_BUSINESS_ENTITY ");
            sql.append("     , billingCycle.frequencyMultiplier AS FREQUENCY_MULTIPLIER ");
            sql.append("     , billingCycle.populatedCycle AS CYC_POPULATION_CODE ");
            sql.append("     , billingCycle.billCycle AS CYCLE_BILL_DAY ");
            sql.append("     , billingCycle.closeCycle AS CYCLE_CLOSE_DAY  ");
            sql.append(" FROM billingCycle ");
            sql.append("     , billingCycleControl  ");
            sql.append(" WHERE billingCycleControl.cycleInstance = :cycleMonth  ");
            sql.append("     AND billingCycleControl.cycleYear = :cycleYear  ");
            sql.append("     AND billingCycle.cycleCode = billingCycleControl.cycleCode  ");
            sql.append(" ORDER BY billingCycle.cycleCode  ");

        } else if (goldenDbVersion == 2) {
            // use new table/fields (waiting for billing team)
            // BILLINGCYCLE	REFERENCE	BILLINGCYCLE	BILLINGREFERENCE
            // BILLINGCYCLE	LIMITBILLINGPERIOD	BILLINGCYCLE	BILLPRDUPLIMIT
            // BILLINGCYCLE	CLOSECYCLE	BILLINGCYCLE	CYCLECLOSEDAY
            // BILLINGCYCLE	POPULATEDCYCLE	BILLINGCYCLE	CYCPOPULATIONCODE
            // BILLINGCYCLE	PARTITIONNUMBER	BILLINGCYCLE	NUMBERPARTITIONS
            // BILLINGCYCLE	BILLCYCLE	BILLINGCYCLE	CYCLEBILLDAY
            // BILLINGCYCLE	NEW FIELD NAME	BILLINGCYCLE	CYCLEGROUPCODE
            // BILLINGCYCLE	NEW FIELD NAME	BILLINGCYCLE	ZONEDETAILS
            // BILLINGCYCLECONTROL	CYCLEINSTANCE	BILLINGCYCLECONTROL	CYCLEMONTH

            sql.append(" SELECT billingCycle.BILLPRDUPLIMIT AS BILL_PRD_UP_LIMIT ");
            sql.append("     , TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(billingCycle.BILLINGREFERENCE,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') AS BILLING_REFERENCE ");
            sql.append("     , billingCycle.cycleCode AS CYCLE_CODE ");
            sql.append("     , billingCycleControl.cycleMonth AS CYCLE_INSTANCE ");
            sql.append("     , billingCycleControl.cycleSeqNo AS CYCLE_SEQ_NO ");
            sql.append("     , billingCycleControl.cycleYear AS CYCLE_YEAR ");
            sql.append("     , TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(billingCycleControl.runDate,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') AS RUN_DATE ");
            sql.append("     , TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(billingCycleControl.startDate,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') AS START_DATE ");
            sql.append("     , TO_CHAR(TO_TIMESTAMP_TZ(TO_CHAR(billingCycleControl.endDate,'DD/MM/RRRR HH24:MI:SS'),'DD/MM/RRRR HH24:MI:SS.FF3'),'RRRR-MM-DD\"T\"HH24:MI:SS.FF3TZH:TZM') AS END_DATE ");
            sql.append("     , billingCycleControl.status AS STATUS ");
            sql.append("     , billingCycle.description AS CYCLE_DESCRIPTION ");
            sql.append("     , billingCycle.frequency AS CYCLE_FREQUENCY ");
            sql.append("     , billingCycle.businessEntity AS CYCLE_BUSINESS_ENTITY ");
            sql.append("     , billingCycle.frequencyMultiplier AS FREQUENCY_MULTIPLIER ");
            sql.append("     , billingCycle.CYCPOPULATIONCODE AS CYC_POPULATION_CODE ");
            sql.append("     , billingCycle.CYCLEBILLDAY AS CYCLE_BILL_DAY ");
            sql.append("     , billingCycle.CYCLECLOSEDAY AS CYCLE_CLOSE_DAY  ");
            sql.append(" FROM billingCycle ");
            sql.append("     , billingCycleControl  ");
            sql.append(" WHERE billingCycleControl.cycleMonth = :cycleMonth  ");
            sql.append("     AND billingCycleControl.cycleYear = :cycleYear  ");
            sql.append("     AND billingCycle.cycleCode = billingCycleControl.cycleCode  ");
            sql.append(" ORDER BY billingCycle.cycleCode  ");


        }

        return sql.toString();
    }


}
