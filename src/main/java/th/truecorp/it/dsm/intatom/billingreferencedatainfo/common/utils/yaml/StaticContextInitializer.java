package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils.yaml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class StaticContextInitializer {
    @Autowired
    private YAMLConfig myConfig;
    @Autowired
    private ApplicationContext context;
    @PostConstruct
    public void init() {
        YAMLUtil.setMyConfig(myConfig);
    }
}
