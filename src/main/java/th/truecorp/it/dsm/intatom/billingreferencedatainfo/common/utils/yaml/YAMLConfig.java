package th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils.yaml;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils.yaml.model.Server;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils.yaml.model.ServiceClient;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils.yaml.model.Spring;

import java.util.HashMap;
import java.util.List;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
@Getter
@Setter
@ToString
public class YAMLConfig {

    private Server server;
    private Spring spring;
    private List<ServiceClient> service;

    public HashMap<String, ServiceClient> getServiceMap() {
        HashMap<String, ServiceClient> serviceHashMap = new HashMap<>();
        for (ServiceClient serviceObj : getService()) {
            serviceHashMap.put(serviceObj.getName(), serviceObj);
        }
        return serviceHashMap;
    }

    public ServiceClient getServiceObj(String name) {

        return getServiceMap().get(name);
    }

}
