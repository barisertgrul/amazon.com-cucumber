package test.automation.framework;

import org.junit.After;
import test.automation.framework.utils.WebDriverUtils;

public abstract class BaseWebAutomationTest {


    @After
    public void tearDown() {
        WebDriverUtils.shutDown();
    }

}
