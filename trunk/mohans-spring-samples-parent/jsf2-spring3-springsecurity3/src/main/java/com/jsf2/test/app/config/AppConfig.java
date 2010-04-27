package com.jsf2.test.app.config;

import com.jsf2.test.common.persistence.AppEngineEntityManagerFactory;
import com.jsf2.test.common.web.FacesUtils;
import com.jsf2.test.common.web.LongParameterResolver;
import com.jsf2.test.common.web.Messenger;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;

/**
 *
 * @author Dimitar Makariev
 */
@Configuration
public class AppConfig {

    @Bean
    EntityManagerFactory entityManagerFactory() {
        final AppEngineEntityManagerFactory factoryBean = new AppEngineEntityManagerFactory("person-pu");
        return factoryBean.entityManagerFactory();
    }

    @Bean
    JpaTransactionManager transactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory());
        return transactionManager;
    }

    @Bean
    LongParameterResolver stringResolver() {
        return new LongParameterResolver() {

            public Long resolve(String name) {
                final String param = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(name);
                if (null == param) {
                    return null;
                }
                try {
                    return Long.valueOf(param);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        };
    }

    @Bean
    Messenger facesMessenger() {
        return new Messenger() {

            public void addSuccessMessage(String msg) {
                FacesUtils.addSuccessMessage(msg);
            }

            public void addErrorMessage(String msg) {
                FacesUtils.addErrorMessage(msg);
            }
        };
    }
}
