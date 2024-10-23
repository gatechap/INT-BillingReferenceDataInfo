package th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PoliciesListByPolicyTypeRowMapper implements RowMapper<PoliciesListByPolicyTypeInfo> {
	@Override
	public PoliciesListByPolicyTypeInfo mapRow(ResultSet rs, int rownum) throws SQLException {
		PoliciesListByPolicyTypeInfo query = new PoliciesListByPolicyTypeInfo();

		query.setDescription(rs.getString("DESCRIPTION"));
		query.setLanguage(rs.getString("LANGUAGE"));
		query.setPolicyId(rs.getString("POLICY_ID"));
		query.setPolicyName(rs.getString("POLICY_NAME"));
		query.setPolicyQualifier(rs.getString("POLICY_QUALIFIER"));
		query.setPolicyType(rs.getString("POLICY_TYPE"));
		query.setPolicyValue(rs.getString("POLICY_VALUE"));

		return query;
	}
}
