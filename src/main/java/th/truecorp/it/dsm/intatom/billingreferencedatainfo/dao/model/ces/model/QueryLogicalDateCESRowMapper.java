package th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.ces.model;

import org.springframework.jdbc.core.RowMapper;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.QueryLogicalDateInfoDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryLogicalDateCESRowMapper implements RowMapper<QueryLogicalDateInfoDAO> {
    @Override
    public QueryLogicalDateInfoDAO mapRow(ResultSet rs, int rowNum) throws SQLException {
        QueryLogicalDateInfoDAO modelInfo = new QueryLogicalDateInfoDAO();
        modelInfo.setLogicalDate(rs.getString("logical_date"));

        return modelInfo;
    }
}
