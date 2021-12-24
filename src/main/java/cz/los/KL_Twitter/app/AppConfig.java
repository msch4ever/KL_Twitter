package cz.los.KL_Twitter.app;

import cz.los.KL_Twitter.App;
import cz.los.KL_Twitter.config.DaoType;
import cz.los.KL_Twitter.persistence.AuthDao;
import cz.los.KL_Twitter.persistence.SessionDao;
import cz.los.KL_Twitter.persistence.TweetDao;
import cz.los.KL_Twitter.persistence.UserDao;
import cz.los.KL_Twitter.persistence.factory.DaoAbstractFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.util.Arrays;
import java.util.Objects;

@Configuration
@ComponentScan(basePackageClasses = App.class)
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("kl-twitter.yaml"));
        configurer.setProperties(Objects.requireNonNull(yaml.getObject()));
        return configurer;
    }

    @Bean
    public UserDao userDao(DaoAbstractFactory abstractFactory) {
        return abstractFactory.createUserDao();
    }

    @Bean
    public TweetDao tweetDao(DaoAbstractFactory abstractFactory) {
        return abstractFactory.createTweetDao();
    }

    @Bean
    public SessionDao sessionDao(DaoAbstractFactory abstractFactory) {
        return abstractFactory.createSessionDao();
    }

    @Bean
    public AuthDao authDao(DaoAbstractFactory abstractFactory) {
        return abstractFactory.createAuthDao();
    }

    @Bean
    public DaoType daoType(@Value("${kl-twitter.daoType}") String daoTypeValue) {
        return Arrays.stream(DaoType.values())
                .filter(it -> it.value().equalsIgnoreCase(daoTypeValue))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Bean
    public Boolean initDb(@Value("${kl-twitter.initDb}") Boolean initDb) {
        return initDb;
    }

    @Bean
    public Boolean populateDb(@Value("${kl-twitter.populateDb}") Boolean populateDb) {
        return populateDb;
    }

}
