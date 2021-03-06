/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.eklub.boot;

import java.util.EnumSet;
import java.util.List;
import java.util.Properties;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.Conventions;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import rs.fon.eklub.constants.ConfigKeys;
import rs.fon.eklub.core.interactors.AdminInteractor;
import rs.fon.eklub.core.interactors.CategoryInteractor;
import rs.fon.eklub.core.interactors.GroupInteractor;
import rs.fon.eklub.core.interactors.MemberInteractor;
import rs.fon.eklub.core.interactors.MembershipFeeInteractor;
import rs.fon.eklub.core.interactors.PaymentInteractor;
import rs.fon.eklub.core.interactors.StatInteractor;
import rs.fon.eklub.core.interactors.TrainingInteractor;
import rs.fon.eklub.core.services.CategoryService;
import rs.fon.eklub.core.validators.GroupValidator;
import rs.fon.eklub.core.validators.MemberValidator;
import rs.fon.eklub.core.validators.PaymentValidator;
import rs.fon.eklub.core.validators.TrainingValidator;
import rs.fon.eklub.dao.implementation.CategoryDao;
import rs.fon.eklub.dao.implementation.EmployeeDao;
import rs.fon.eklub.dao.implementation.GroupDao;
import rs.fon.eklub.dao.implementation.MemberDao;
import rs.fon.eklub.dao.implementation.MembershipFeeDao;
import rs.fon.eklub.dao.implementation.PaymentDao;
import rs.fon.eklub.dao.implementation.StatDao;
import rs.fon.eklub.dao.implementation.TrainingDao;
import rs.fon.eklub.filters.CORSFilter;
import rs.fon.eklub.util.Config;
import rs.fon.eklub.json.converters.JsonHttpConverter;
import rs.fon.eklub.security.MethodSecurityConfig;
import rs.fon.eklub.security.OAuth2ResourceConfig;

/**
 *
 * @author milos
 */
@EnableAutoConfiguration
@Configuration
@Import({OAuth2ResourceConfig.class, MethodSecurityConfig.class})
@ComponentScan(basePackages = "rs.fon.eklub")
public class Main extends WebMvcConfigurationSupport implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        registerServletFilter(servletContext, new CORSFilter());
    }

    protected FilterRegistration.Dynamic registerServletFilter(ServletContext servletContext, Filter filter) {
        String filterName = Conventions.getVariableName(filter);
        FilterRegistration.Dynamic registration = servletContext.addFilter(filterName, filter);
        registration.setAsyncSupported(true);
        registration.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
        return registration;
    }

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
                new GroupValidator());
    }

    @Autowired
    @Bean
    public MemberInteractor getMemberInteractor(SessionFactory sessionFactory) {
        return new MemberInteractor(new MemberDao(sessionFactory),
                new MemberValidator());
    }

    @Autowired
    @Bean
    public TrainingInteractor getTrainingInteractor(SessionFactory sessionFactory) {
        return new TrainingInteractor(new TrainingDao(sessionFactory),
                new TrainingValidator());
    }

    @Autowired
    @Bean
    public MembershipFeeInteractor getMembershipFeeInteractor(SessionFactory sessionFactory) {
        return new MembershipFeeInteractor(new MembershipFeeDao(sessionFactory));
    }

    @Autowired
    @Bean
    public PaymentInteractor getPaymentInteractor(SessionFactory sessionFactory) {
        return new PaymentInteractor(new PaymentDao(sessionFactory),
                new PaymentValidator());
    }

    @Autowired
    @Bean
    public AdminInteractor getAdminInteractor(SessionFactory sessionFactory) {
        return new AdminInteractor(new EmployeeDao(sessionFactory));
    }
    
    @Autowired
    @Bean
    public StatInteractor getStatInteractor(SessionFactory sessionFactory) {
        return new StatInteractor(new StatDao(sessionFactory));
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Config.getInstance().getValue(ConfigKeys.DatabaseConfigKeys.DB_DRIVER));
        dataSource.setUrl(Config.getInstance().getValue(ConfigKeys.DatabaseConfigKeys.DB_CONNECTION_STRING));
        dataSource.setUsername(Config.getInstance().getValue(ConfigKeys.DatabaseConfigKeys.DB_USER));
        dataSource.setPassword(Config.getInstance().getValue(ConfigKeys.DatabaseConfigKeys.DB_PASSWORD));
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
        sessionBuilder.addResource("mappings/Training.hbm.xml");
        sessionBuilder.addResource("mappings/Payment.hbm.xml");
        sessionBuilder.addResource("mappings/Attendance.hbm.xml");
        sessionBuilder.addResource("mappings/Employee.hbm.xml");
        sessionBuilder.addResource("mappings/EmployeeEngagement.hbm.xml");
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
//        MappingJackson2HttpMessageConverter jsonConverter = new JsonHttpConverter();
//        return jsonConverter;
//    }
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new JsonHttpConverter());
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
