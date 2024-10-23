package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils.yaml.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EnvironmentCtrl {
	private boolean enableRedis;
	private String prepaidCatalogueIndicator;
	private String defaultBillingSystem;
	private String defaultBillingSystemRef;
	private int goldenDbVersion;

	public boolean isPrepaidCatalogueIndicator() {
		return "Y".equals(this.prepaidCatalogueIndicator);
	}
}
