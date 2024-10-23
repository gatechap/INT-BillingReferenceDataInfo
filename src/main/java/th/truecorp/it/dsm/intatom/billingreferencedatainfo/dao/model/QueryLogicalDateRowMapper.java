package th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryLogicalDateRowMapper implements RowMapper<QueryLogicalDateInfoDAO> {
    @Override
    public QueryLogicalDateInfoDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
        QueryLogicalDateInfoDAO modelInfo = new QueryLogicalDateInfoDAO();
        modelInfo.setLogicalDate(rs.getString("logical_date"));

        return modelInfo;
    }
}
