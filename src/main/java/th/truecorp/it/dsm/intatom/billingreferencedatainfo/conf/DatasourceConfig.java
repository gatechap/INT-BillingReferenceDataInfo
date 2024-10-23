package th.truecorp.it.dsm.intatom.billingreferencedatainfo.conf;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils.yaml.YAMLConfig;
import th.truecorp.it.dsm.intatom.billingreferencedatainfo.common.utils.yaml.model.Datasource;

@Configuration
public class DatasourceConfig {

	@Autowired
	private YAMLConfig myConfig;

	NamedParameterJdbcTemplate JdbcTemplate;
	NamedParameterJdbcTemplate SecondaryJdbcTemplate;

	private Properties oracleProperties(Datasource datasource) {
		Properties properties = new Properties();
		properties.put("oracle.jdbc.ReadTimeout", datasource.getHikari().getReadTimeout());
		return properties;
	}

	@PostConstruct
	private void setDataSourcePROD() {
		Datasource primaryDatasource = myConfig.getSpring().getDatasource();
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setMaximumPoolSize(primaryDatasource.getHikari().getMaximumPoolSize());
		hikariConfig.setConnectionTimeout(primaryDatasource.getHikari().getConnectionTimeout());
		hikariConfig.setIdleTimeout(primaryDatasource.getHikari().getIdleTimeout());
		hikariConfig.setDriverClassName(primaryDatasource.getDriverClassName());
		hikariConfig.setJdbcUrl(primaryDatasource.getUrl());
		hikariConfig.setUsername(primaryDatasource.getUsername());
		hikariConfig.setPassword(primaryDatasource.getPassword());
		hikariConfig.setDataSourceProperties(oracleProperties(primaryDatasource));
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		JdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

		Datasource secondaryDatasource = myConfig.getSpring().getSecondaryDatasource();
		HikariConfig secondaryHikariConfig = new HikariConfig();
		secondaryHikariConfig.setMaximumPoolSize(secondaryDatasource.getHikari().getMaximumPoolSize());
		secondaryHikariConfig.setConnectionTimeout(secondaryDatasource.getHikari().getConnectionTimeout());
		secondaryHikariConfig.setIdleTimeout(secondaryDatasource.getHikari().getIdleTimeout());
		secondaryHikariConfig.setDriverClassName(secondaryDatasource.getDriverClassName());
		secondaryHikariConfig.setJdbcUrl(secondaryDatasource.getUrl());
		secondaryHikariConfig.setUsername(secondaryDatasource.getUsername());
		secondaryHikariConfig.setPassword(secondaryDatasource.getPassword());
		secondaryHikariConfig.setDataSourceProperties(oracleProperties(secondaryDatasource));
		HikariDataSource secondaryDataSource = new HikariDataSource(secondaryHikariConfig);
		SecondaryJdbcTemplate = new NamedParameterJdbcTemplate(secondaryDataSource);

	}

	@Bean(name = "OracleProdBilling")
	public NamedParameterJdbcTemplate OracleProdBilling() {
		return JdbcTemplate;
	}

	@Bean(name = "dataSourceCES")
	public NamedParameterJdbcTemplate getDataSourceCES() {
		return SecondaryJdbcTemplate;
	}
}