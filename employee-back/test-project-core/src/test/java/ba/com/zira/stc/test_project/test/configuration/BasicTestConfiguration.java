package ba.com.zira.stc.test_project.test.configuration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@SpringBootTest(classes = TestNGWithSpringApplication.class)
public abstract class BasicTestConfiguration extends AbstractTestNGSpringContextTests {

}
