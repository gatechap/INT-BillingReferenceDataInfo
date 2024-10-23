package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public class DAOUtils {

	public static String getParameterString(MapSqlParameterSource parameterSource) {
		String result = "";
		for (String name : parameterSource.getParameterNames()) {
			result += "," + name + "="
					+ (parameterSource.getValue(name) != null ? String.valueOf(parameterSource.getValue(name)) : "");
		}
		if (result != null && result.trim().length() > 0) {
			result = result.substring(1);
		}
		return result;
	}

}
