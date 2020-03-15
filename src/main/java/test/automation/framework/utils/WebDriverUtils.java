package test.automation.framework.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.automation.framework.config.ConfigReader;
import test.automation.framework.pageobject.PageObject;

import java.util.concurrent.TimeUnit;

public final class WebDriverUtils {

    public static final long DEFAULT_TIMEOUT = 10000L;

    protected JavascriptExecutor js;

    private static ThreadLocal<WebDriver> browser = new ThreadLocal<>();

    private WebDriverUtils() {
    }

    public static WebDriver getBrowser() {
        if (browser.get() == null) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setJavascriptEnabled(true);
            desiredCapabilities.setCapability("takesScreenshot", true);
            if ("chrome".equals(ConfigReader.getPlatform())) {
                browser.set(launchChrome(desiredCapabilities));
            } else if ("firefox".equals(ConfigReader.getPlatform())) {
                browser.set(launchFirefox(desiredCapabilities));
            } else {
                throw new RuntimeException("Unknown browser: " + ConfigReader.getPlatform());
            }

            browser.get().manage().window().maximize();
            browser.get().manage().timeouts().implicitlyWait(2L, TimeUnit.SECONDS);
        }

        return browser.get();
    }

    private static WebDriver launchFirefox(DesiredCapabilities desiredCapabilities) {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(new FirefoxOptions().merge(desiredCapabilities));
    }

    private static WebDriver launchChrome(DesiredCapabilities desiredCapabilities) {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(new ChromeOptions().merge(desiredCapabilities));
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    public static void waitForURLToContainSubstring(WebDriver browser, String substring) throws Exception {
        long deadline = System.currentTimeMillis() + DEFAULT_TIMEOUT;
        if (browser == null) {
            browser = getBrowser();
        }

        while (!browser.getCurrentUrl().contains(substring) && System.currentTimeMillis() < deadline) {
            sleep(500L);
        }

        if (!browser.getCurrentUrl().contains(substring)) {
            String msg = "Violated expectation: Expected URL (" + browser.getCurrentUrl() + ")  to contain \"" + substring + "\"";
            throw new Exception(msg);
        }
    }

    public static void waitForURLNotToContainSubstring(WebDriver browser, String substring) throws Exception {
        long deadline = System.currentTimeMillis() + DEFAULT_TIMEOUT;
        if (browser == null) {
            browser = getBrowser();
        }

        while (browser.getCurrentUrl().contains(substring) && System.currentTimeMillis() < deadline) {
            sleep(500L);
        }

        if (browser.getCurrentUrl().contains(substring)) {
            String msg = "Violated expectation: Expected URL (" + browser.getCurrentUrl() + ") -->not<-- to contain \"" + substring + "\"";
            throw new Exception(msg);
        }
    }

    public static void waitForURLToBe(WebDriver browser, String url) throws Exception {
        long deadline = System.currentTimeMillis() + DEFAULT_TIMEOUT;
        if (browser == null) {
            browser = getBrowser();
        }

        while (!browser.getCurrentUrl().equals(url) && System.currentTimeMillis() < deadline) {
            sleep(500L);
        }

        if (!browser.getCurrentUrl().equals(url)) {
            String msg = "Violated expectation: Expected URL (" + browser.getCurrentUrl() + ") to be \"" + url + "\"";
            throw new Exception(msg);
        }
    }

    public static void waitForPresent(WebDriver browser, PageObject pageObject) throws Exception {
        long deadline = System.currentTimeMillis() + DEFAULT_TIMEOUT;
        if (browser == null) {
            getBrowser();
        }

        while (!pageObject.isPresent() && System.currentTimeMillis() < deadline) {
            sleep(500L);
        }

        if (!pageObject.isPresent()) {
            String msg = "Violated expectation: Page not present: " + pageObject.getClass().getName();
            throw new Exception(msg);
        }
    }

    public static void waitForElementExists(WebDriver browser, By locator) {
        WebDriverWait wait = new WebDriverWait(browser, DEFAULT_TIMEOUT / 1000L);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForElementExists(WebDriver browser, By locator, int timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(browser, (long) timeOutInSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static void waitForElementVisible(WebDriver browser, By locator) {
        WebDriverWait wait = new WebDriverWait(browser, DEFAULT_TIMEOUT / 1000L);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        wait.until(ExpectedConditions.visibilityOf(browser.findElement(locator)));
    }

    public static void waitForURLToBe(String url) throws Exception {
        waitForURLToBe(null, url);
    }

    public static void waitForURLNotToContainSubstring(String substring) throws Exception {
        waitForURLNotToContainSubstring(null, substring);
    }

    public static void waitForURLToContainSubstring(String url) throws Exception {
        waitForURLToContainSubstring(null, url);
    }

    public static void shutDown() {
        if (getBrowser() != null) {
            getBrowser().quit();
            browser.remove();
        }
    }

    public static void sendKeysWithDelay(WebDriver browser, WebElement element, CharSequence keys, long delay) {
        if (delay == -1L) {
            delay = 100L;
        }

        new Actions(browser);

        for (int i = 0; i < keys.length(); ++i) {
            sleep(delay);
            element.sendKeys(keys.subSequence(i, i + 1));
        }
    }

    public static void sendKeysWithDelay(WebElement element, CharSequence keys, long delay) {
        sendKeysWithDelay(getBrowser(), element, keys, delay);
    }

    public static void goBack() {
        getBrowser().navigate().back();
    }

    public static void goForward() {
        getBrowser().navigate().forward();
    }

    public static String getCurrentUrl() {
        return getBrowser().getCurrentUrl();
    }

    public static Alert getAlert() {
        Alert result;
        try {
            WebDriverWait wait = new WebDriverWait(getBrowser(), 2L);
            wait.until(ExpectedConditions.alertIsPresent());
            result = getBrowser().switchTo().alert();
        } catch (Exception ignored) {
            result = null;
        }

        return result;
    }

    protected void waitForAjaxLoad() {
        long startTime = System.currentTimeMillis();
        long estimatedTime;
        try {
            Thread.sleep(250);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }

        boolean stillRunnigPageLoad = (Boolean) js
                .executeScript("return window.jQuery != undefined && jQuery.active == 0");
        try {
            while (!stillRunnigPageLoad) {
                stillRunnigPageLoad = (Boolean) js
                        .executeScript("return window.jQuery != undefined && jQuery.active == 0");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException ignored) {
                }
                estimatedTime = System.currentTimeMillis() - startTime;

                if (estimatedTime >= 12000) {
                    stillRunnigPageLoad = true;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
