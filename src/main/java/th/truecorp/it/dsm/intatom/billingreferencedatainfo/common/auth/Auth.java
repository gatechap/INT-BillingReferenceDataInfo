package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.auth;

import java.util.Base64;

import th.truecorp.it.esd.ei.commonlib.utils.StringUtils;


public class Auth {

	public static String getXUser(String xAuth, String xUser) {

		AuthenUserInfo authInfo = getAuthenUser(xAuth);
		String xUsername = "";
		if (authInfo != null && !StringUtils.isEmpty(authInfo.getUsername())) {
			xUsername = authInfo.getUsername();
		} else {
			xUsername = xUser;
		}

		return xUsername;
	}

	private static AuthenUserInfo getAuthenUser(String auth) {
		AuthenUserInfo authenUserInfo = new AuthenUserInfo();
		if (!StringUtils.isEmpty(auth)) {
			byte[] decodedBytes = Base64.getDecoder().decode(auth.substring(6));
			String decodedString = new String(decodedBytes);
			String[] splitted = decodedString.split(":");
			if (splitted != null && splitted.length > 1) {
				authenUserInfo.setUsername(splitted[0].trim());
				authenUserInfo.setPassword(splitted[1].trim());
			}
		} else {
			authenUserInfo = null;
		}
		return authenUserInfo;
	}

}
