package th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CycleControlRowMapper implements RowMapper<CycleControlInfoDAO> {
    @Override
    public CycleControlInfoDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
        CycleControlInfoDAO modelInfo = new CycleControlInfoDAO();
        modelInfo.setBillPrdUpLimit(rs.getShort("BILL_PRD_UP_LIMIT"));
        modelInfo.setBillingReference(rs.getString("BILLING_REFERENCE"));
        modelInfo.setCycleBillDay(rs.getShort("CYCLE_BILL_DAY"));
        modelInfo.setCycleBusinessEntity(rs.getInt("CYCLE_BUSINESS_ENTITY"));
        modelInfo.setCycleCloseDay(rs.getShort("CYCLE_CLOSE_DAY"));
        modelInfo.setCycleCode(rs.getShort("CYCLE_CODE"));
        modelInfo.setCycleDescription(rs.getString("CYCLE_DESCRIPTION"));
        modelInfo.setCycleFrequency(rs.getString("CYCLE_FREQUENCY"));
        modelInfo.setCycleFrequencyMultiplier(rs.getShort("FREQUENCY_MULTIPLIER"));
        modelInfo.setCyclePopulationCode(rs.getString("CYC_POPULATION_CODE"));
        modelInfo.setCycleInstance(rs.getShort("CYCLE_INSTANCE"));
        modelInfo.setCycleSeqNo(rs.getInt("CYCLE_SEQ_NO"));
        modelInfo.setCycleYear(rs.getShort("CYCLE_YEAR"));
        modelInfo.setEndDate(rs.getString("END_DATE"));
        modelInfo.setRunDate(rs.getString("RUN_DATE"));
        modelInfo.setStartDate(rs.getString("START_DATE"));
        modelInfo.setStatus(rs.getString("STATUS"));
        return modelInfo;
    }
}
