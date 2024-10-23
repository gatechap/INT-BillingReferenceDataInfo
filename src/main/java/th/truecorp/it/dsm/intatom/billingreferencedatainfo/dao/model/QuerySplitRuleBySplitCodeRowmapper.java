package th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QuerySplitRuleBySplitCodeRowmapper implements RowMapper<QuerySplitRuleBySplitCodeInfoDAO> {
    @Override
    public QuerySplitRuleBySplitCodeInfoDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
        QuerySplitRuleBySplitCodeInfoDAO modelInfo = new QuerySplitRuleBySplitCodeInfoDAO();
        modelInfo.setSplitCode(rs.getString("SPLIT_CODE"));
        modelInfo.setSplitLevel(rs.getString("SPLIT_LEVEL"));
        modelInfo.setSplitValue(rs.getString("SPLIT_VALUE"));
        modelInfo.setIncludeInd(rs.getString("INCLUDE_IND"));
        modelInfo.setSplitWay(rs.getString("SPLIT_WAY"));
        modelInfo.setQuantity(rs.getBigDecimal("QUANTITY"));
        modelInfo.setWorkInd(rs.getString("WORK_IND"));
        modelInfo.setExceedInd(rs.getString("EXCEED_IND"));
        modelInfo.setSplitDescription(rs.getString("SPLIT_DESCRIPTION"));
        modelInfo.setSplitEffectiveDate(rs.getString("EFFECTIVE_DATE"));
        modelInfo.setSplitExpirationDate(rs.getString("EXPIRATION_DATE"));


        return modelInfo;
    }
}
