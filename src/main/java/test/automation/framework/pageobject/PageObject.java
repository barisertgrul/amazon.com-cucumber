package test.automation.framework.pageobject;

import org.openqa.selenium.WebDriver;
import test.automation.framework.utils.WebDriverUtils;

public abstract class PageObject {

    protected final WebDriver browser;

    public PageObject(WebDriver browser) {
        if (browser == null) {
            this.browser = WebDriverUtils.getBrowser();
        } else {
            this.browser = browser;
        }
    }

    public PageObject() {
        this(null);
    }

    public abstract void navigateTo();

    public abstract boolean isPresent();

}
