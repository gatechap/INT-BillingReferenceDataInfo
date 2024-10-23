package th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChargeCodeInfoRowModelMapperDAO implements RowMapper<ChargeCodeInfoDAO> {

    @Override
    public ChargeCodeInfoDAO mapRow(ResultSet rs, int rowNum)throws SQLException {
        ChargeCodeInfoDAO resultDAO = new ChargeCodeInfoDAO();
        resultDAO.setChargeCode(rs.getString("CHARGE_CODE"));
        resultDAO.setChargeCodeDesc(rs.getString("CHARGE_CODE_DESC"));
        resultDAO.setRevenueCode(rs.getString("REVENUE_CODE"));
        resultDAO.setGroupType(rs.getString("GROUP_TYPE"));
        resultDAO.setL9BillPresentInd(rs.getString("L9_BILL_PRESENT_IND"));
        resultDAO.setBillTh(rs.getString("BILL_TH"));
        resultDAO.setBillEn(rs.getString("BILL_EN"));
        return resultDAO;
    }

}
