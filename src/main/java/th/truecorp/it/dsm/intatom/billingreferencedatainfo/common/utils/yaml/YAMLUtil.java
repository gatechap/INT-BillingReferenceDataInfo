package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils.yaml;

public class YAMLUtil {
	private YAMLUtil() {
		// TODO Auto-generated constructor stub
	}

	private static YAMLConfig myConfig = new YAMLConfig();

	public static void setMyConfig(YAMLConfig myConfig) {
		YAMLUtil.myConfig = myConfig;
	}

	public static YAMLConfig getMyConfig() {
		return myConfig;
	}

}
