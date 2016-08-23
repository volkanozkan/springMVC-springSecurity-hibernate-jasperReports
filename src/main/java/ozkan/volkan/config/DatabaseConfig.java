package ozkan.volkan.config;

import javax.sql.DataSource;
import java.util.Properties;
import com.zaxxer.hikari.HikariDataSource;

import ozkan.volkan.model.Authorities;
import ozkan.volkan.model.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

	@Autowired
	private ApplicationContext appContext;

	@Bean(name = "DataSource")
	public HikariDataSource getDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
		dataSource.addDataSourceProperty("databaseName", "volkan_ozkan_obss");
		dataSource.addDataSourceProperty("portNumber", "3306");
		dataSource.addDataSourceProperty("serverName", "127.0.0.1");
		dataSource.addDataSourceProperty("user", "root");
		dataSource.addDataSourceProperty("password", "");
		return dataSource;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager manager = new HibernateTransactionManager();
		manager.setSessionFactory(hibernate5SessionFactoryBean().getObject());
		return manager;
	}

	@Bean
	public LocalSessionFactoryBean hibernate5SessionFactoryBean() {
		LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
		localSessionFactoryBean.setDataSource((DataSource) appContext.getBean("DataSource"));
		localSessionFactoryBean.setAnnotatedClasses(Users.class, Authorities.class);

		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("hibernate.hbm2ddl.auto", "create-drop");
		properties.put("hibernate.show_sql", "false");

		localSessionFactoryBean.setHibernateProperties(properties);
		return localSessionFactoryBean;
	}
}
