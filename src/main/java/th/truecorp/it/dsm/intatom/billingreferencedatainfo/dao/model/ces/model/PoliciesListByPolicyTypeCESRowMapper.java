package th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.ces.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import th.truecorp.it.dsm.intatom.billingreferencedatainfo.dao.model.PoliciesListByPolicyTypeInfo;

public class PoliciesListByPolicyTypeCESRowMapper implements RowMapper<PoliciesListByPolicyTypeInfo> {
	@Override
	public PoliciesListByPolicyTypeInfo mapRow(ResultSet rs, int rownum) throws SQLException {
		PoliciesListByPolicyTypeInfo query = new PoliciesListByPolicyTypeInfo();

		query.setDescription(rs.getString("DESCRIPTION"));
		query.setLanguage(rs.getString("LANG"));
		query.setPolicyId(rs.getString("POLICYID"));
		query.setPolicyName(rs.getString("POLICYNAME"));
		query.setPolicyQualifier(rs.getString("POLICYQUALIFIER"));
		query.setPolicyType(rs.getString("POLICYTYPE"));
		query.setPolicyValue(rs.getString("POLICYVALUE"));

		return query;
	}
}
