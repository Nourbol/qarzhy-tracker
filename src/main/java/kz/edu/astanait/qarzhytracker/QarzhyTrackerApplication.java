package kz.edu.astanait.qarzhytracker;

import kz.edu.astanait.qarzhytracker.configuration.TransactionFilterConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(TransactionFilterConfigProperties.class)
@SpringBootApplication
public class QarzhyTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QarzhyTrackerApplication.class, args);
    }

}
