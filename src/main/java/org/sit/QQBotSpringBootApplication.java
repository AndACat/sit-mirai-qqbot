package org.sit;

import org.sit.handler.login.QQBotLoginHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author WangZhen
 * @Date 2023/11/4 10:01
 */
@SpringBootApplication(scanBasePackages = "org.sit")
class QQBotSpringBootApplication {
    public static void main(String[] args) {
        try {
            ConfigurableApplicationContext run = SpringApplication.run(QQBotSpringBootApplication.class, args);
            QQBotLoginHandler bean = run.getBean(QQBotLoginHandler.class);
            bean.login();
        }catch (Throwable e){
            e.printStackTrace();
        }

    }
}
