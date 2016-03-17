/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.boot;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Properties;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import rs.fon.eklub.core.data.DataAccessService;
import rs.fon.eklub.core.entities.Category;
import rs.fon.eklub.core.interactors.AdminInteractor;
import rs.fon.eklub.core.interactors.CategoryInteractor;
import rs.fon.eklub.core.interactors.GroupInteractor;
import rs.fon.eklub.core.interactors.MemberInteractor;
import rs.fon.eklub.core.interactors.MembershipFeeInteractor;
import rs.fon.eklub.core.interactors.PaymentInteractor;
import rs.fon.eklub.core.interactors.TrainingInteractor;
import rs.fon.eklub.core.services.CategoryService;
import rs.fon.eklub.core.validators.MockGroupValidator;
import rs.fon.eklub.core.validators.MockMemberValidator;
import rs.fon.eklub.core.validators.MockPaymentValidator;
import rs.fon.eklub.core.validators.MockTrainingValidator;
import rs.fon.eklub.dao.implementation.CategoryDao;
import rs.fon.eklub.dao.implementation.GroupDao;
import rs.fon.eklub.dao.implementation.MemberDao;
import rs.fon.eklub.dao.implementation.MembershipFeeDao;
import rs.fon.eklub.dao.mock.MockAdminRepository;
import rs.fon.eklub.dao.mock.MockCategoryRepository;
import rs.fon.eklub.dao.mock.MockGroupRepository;
import rs.fon.eklub.dao.mock.MockMemberRepository;
import rs.fon.eklub.dao.mock.MockMembershipFeeRepository;
import rs.fon.eklub.dao.mock.MockPaymentRepository;
import rs.fon.eklub.dao.mock.MockTrainingRepository;
import rs.fon.eklub.util.Json2HttpMapper;

/**
 *
 * @author milos
 */
@Configuration
@ComponentScan(basePackages = "rs.fon.eklub")
@EnableAutoConfiguration
public class Main extends WebMvcConfigurationSupport {

    @Bean
    protected ServletContextListener listener() {
        return new ServletContextListener() {
            @Override
            public void contextInitialized(ServletContextEvent sce) {
                System.out.println("ServletContext initialized");
            }

            @Override
            public void contextDestroyed(ServletContextEvent sce) {
                System.out.println("ServletContext destroyed");
            }
        };
    }
  
    @Autowired
    @Bean
    public CategoryService getCategoryInteractor(SessionFactory sessionFactory) {
        return new CategoryInteractor(new CategoryDao(sessionFactory));
    }
 
    @Autowired
    @Bean
    public GroupInteractor getGroupInteractor(SessionFactory sessionFactory) {
        return new GroupInteractor(new GroupDao(sessionFactory),
                new MockGroupValidator());
    }

    @Autowired
    @Bean
    public MemberInteractor getMemberInteractor(SessionFactory sessionFactory) {
        return new MemberInteractor(new MemberDao(sessionFactory),
                new MockMemberValidator());
    }

    @Bean
    public TrainingInteractor getTrainingInteractor() {
        return new TrainingInteractor(new MockTrainingRepository(),
                new MockTrainingValidator());
    }

    @Autowired
    @Bean
    public MembershipFeeInteractor getMembershipFeeInteractor(SessionFactory sessionFactory) {
        return new MembershipFeeInteractor(new MembershipFeeDao(sessionFactory));
    }

    @Bean
    public PaymentInteractor getPaymentInteractor() {
        return new PaymentInteractor(new MockPaymentRepository(),
                new MockPaymentValidator());
    }

    @Bean
    public AdminInteractor getAdminInteractor() {
        return new AdminInteractor(new MockAdminRepository());
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/eKlub?zeroDateTimeBehavior=convertToNull");
        dataSource.setUsername("eklub");
        dataSource.setPassword("basketball@2016");
        return dataSource;
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.addProperties(getHibernateProperties());
        sessionBuilder.addResource("mappings/Category.hbm.xml");
        sessionBuilder.addResource("mappings/Member.hbm.xml");
        sessionBuilder.addResource("mappings/Group.hbm.xml");
        sessionBuilder.addResource("mappings/MembershipFee.hbm.xml");
        return sessionBuilder.buildSessionFactory();
    }

//    @Autowired
//    @Bean(name = "transactionManager")
//    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
//        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
//        return transactionManager;
//    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.format_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.globally_quoted_identifiers", "true");
        return properties;
    }

//    @Bean
//    public MappingJackson2HttpMessageConverter json2HttpMapper() {
//        MappingJackson2HttpMessageConverter jsonConverter = new Json2HttpMapper();
//        return jsonConverter;
//    }
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new Json2HttpMapper());
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
