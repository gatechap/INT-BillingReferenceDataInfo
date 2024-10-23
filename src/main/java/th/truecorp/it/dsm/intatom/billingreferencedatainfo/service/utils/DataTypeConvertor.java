package th.truecorp.it.dsm.intatom.billingreferencedatainfo.service.utils;

import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.response.CycleCodeInfo;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.controllers.model.response.CycleInfo;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.CycleControlInfoDAO;


public class DataTypeConvertor 
{
	public static CycleInfo convertToCycleInfo(CycleControlInfoDAO daoInfo) throws Exception
	{
		CycleInfo result = null;
		
		if (daoInfo != null)
		{
			result = new CycleInfo();
			String sCycleInstance = (daoInfo.getCycleInstance() == null) ? null : daoInfo.getCycleInstance().toString();
			result.setCycleInstance(sCycleInstance);
			
			String sCycleSeqNo = (daoInfo.getCycleSeqNo() == null) ? null : daoInfo.getCycleSeqNo().toString();
			result.setCycleSeqNo(sCycleSeqNo);
			
			String sCycleYear = (daoInfo.getCycleYear() == null) ? null : daoInfo.getCycleYear().toString();
			result.setCycleYear(sCycleYear);
			result.setEndDate(daoInfo.getEndDate());
			
			String sRunDate = (daoInfo.getRunDate() == null) ? "" : daoInfo.getRunDate().toString();
			result.setRunDate(sRunDate);
			
			result.setStartDate(daoInfo.getStartDate());
			result.setStatus(daoInfo.getStatus());
			
			CycleCodeInfo cycleCode = convertToCycleCodeInfo(daoInfo);
			result.setCycleCode(cycleCode);
		}
		
		return result;
	} // end method
	private static CycleCodeInfo convertToCycleCodeInfo(CycleControlInfoDAO daoInfo) throws Exception
	{
		CycleCodeInfo result = null;
		
		if (daoInfo != null)
		{
			result = new CycleCodeInfo();
			
			String sBillPrdUpLimit = (daoInfo.getBillPrdUpLimit() == null) ? null : daoInfo.getBillPrdUpLimit().toString();
			result.setBillPrdUpLimit(sBillPrdUpLimit);
			
			result.setBillingReference(daoInfo.getBillingReference());
			String sCycleBillDay = (daoInfo.getCycleBillDay() == null) ? null : daoInfo.getCycleBillDay().toString();
			result.setCycleBillDay(sCycleBillDay);
			
			String sCycleBusinessEntity = (daoInfo.getCycleBusinessEntity() == null) ? null : daoInfo.getCycleBusinessEntity().toString();
			result.setCycleBusinessEntity(sCycleBusinessEntity);
			
     		String sCycleCloseDay = (daoInfo.getCycleCloseDay() == null) ? null : daoInfo.getCycleCloseDay().toString();
			result.setCycleCloseDay(sCycleCloseDay);
			
			String sCycleCode = (daoInfo.getCycleCode() == null) ? null : daoInfo.getCycleCode().toString();
			result.setCycleCode(sCycleCode);
			
			result.setCycleDescription(daoInfo.getCycleDescription());
			result.setCycleFrequency(daoInfo.getCycleFrequency());
			
			String sCycleFrequencyMultiplier = (daoInfo.getCycleFrequencyMultiplier() == null) ? null : daoInfo.getCycleFrequencyMultiplier().toString();
			result.setCycleFrequencyMultiplier(sCycleFrequencyMultiplier);
			
			result.setCyclePopulationCode(daoInfo.getCyclePopulationCode());
		}
		
		return result;
	} // end method
} // end class
