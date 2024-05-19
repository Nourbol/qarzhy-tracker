package kz.edu.astanait.qarzhytracker;

import kz.edu.astanait.qarzhytracker.configuration.OpenAiDefaultSettingsConfigProperties;
import kz.edu.astanait.qarzhytracker.configuration.TransactionFilterConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableCaching
@EnableFeignClients
@EnableConfigurationProperties({
    TransactionFilterConfigProperties.class,
    OpenAiDefaultSettingsConfigProperties.class
})
@SpringBootApplication
public class QarzhyTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QarzhyTrackerApplication.class, args);
    }

}
