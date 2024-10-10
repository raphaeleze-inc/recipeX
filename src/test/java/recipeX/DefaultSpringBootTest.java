package recipeX;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import recipeX.boot.Application;

@SpringBootTest(classes = Application.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class DefaultSpringBootTest {

}