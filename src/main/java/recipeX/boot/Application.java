package recipeX.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication(scanBasePackages = "recipeX")
@EnableReactiveMongoRepositories(basePackages = "recipeX.mongo")
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

}
