package StepDefinition;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class step_definition {
    public static WebDriver driver;

    @Given("^Start Driver and Browser$")
    public void start_Driver_And_Browserstart() {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    @When("^Navigate to website$")
    public void navigateToWebsite() {
        driver.get("http://amazon.com/");
    }

    @And("^I land on homepage$")
    public void iLandOnHomepage() {
        Assert.assertEquals("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more", driver.getTitle());
    }

    @When("^I click on signin button$")
    public void iClickOnSigninButton() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[1]/header/div/div[1]/div[2]/div/a[2]")).click();
    }

    @And("^I enter email address$")
    public void iEnterEmailAddress() throws Throwable {
        driver.findElement(By.id("ap_email")).sendKeys("baris93ertugrul@hotmail.com");
    }

    @And("^I click on Continue button$")
    public void iClickOnContinueButton() {
        driver.findElement(By.id("continue")).click();
    }

    @And("^I enter password$")
    public void iEnterPassword() throws Throwable {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.id("ap_password")).sendKeys("Spopenpo1.");
    }

    @And("^I click on SignIn Button$")
    public void iClickOnSignInButton() {
        driver.findElement(By.id("signInSubmit")).click();
    }

    @Then("^I should be logged in$")
    public void iShouldBeLoggedInToAmazonCom() {
        Assert.assertNotEquals("Hello, Sign in",driver.findElement(By.xpath("/html/body/div[1]/header/div/div[1]/div[2]/div/a[2]/span[1]")));
    }


    @Given("^I am on homepage$")
    public void iAmOnHomepage() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Assert.assertEquals("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more", driver.getTitle());
    }

    @When("^I enter text on search box$")
    public void iEnterTextOnSearchBox() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[1]/header/div/div[1]/div[3]/div/form/div[3]/div[1]/input")).sendKeys("samsung");
    }

    @And("^I click on search button$")
    public void iClickOnSearchButton() {
        driver.findElement(By.xpath("/html/body/div[1]/header/div/div[1]/div[3]/div/form/div[2]/div/input")).click();

    }

    @Then("^I should see search results$")
    public void iShouldSeeSearchResults() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Assert.assertEquals("\"samsung\"", driver.findElement(By.xpath("/html/body/div[1]/div[2]/span/span/h1/div/div[1]/div/div/span[3]")).getText());
    }

    @When("^I click on page number on pagination$")
    public void iClickOnPageNumberOnPagination() {
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div/span[8]/div/span/div/div/ul/li[3]")).click();
    }

    @Then("^I should see second page of search results$")
    public void iShouldSeeSecondPageOfSearchResults() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals("2", driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div/span[8]/div/span/div/div/ul/li[3]/a")).getText());
    }

    @When("^I click on third item on second page of results$")
    public void iClickOnThirdItemOnSecondPageOfResults() {
        driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div[2]/div/span[4]/div[1]/div[3]/div/span/div/div/div[2]/div[1]/div/div/span/a")).click();
    }

    @Then("^I should see item detail page is opened$")
    public void iShouldSeeItemDetailPageIsOpened() {
        Assert.assertNotEquals("Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more", driver.getTitle());
    }


    @Given("^I land on item detail page$")
    public void iLandOnItemDetailPage() {
        WebDriverWait wait= new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("urelement")));
    }

    @When("^I click on add to list button$")
    public void iClickOnAddToListButton() {

        driver.findElement(By.id("add-to-wishlist-button-submit")).click();

    }


    @Then("^I should see create list window is opened$")
    public void iShouldSeeCreateListWindowIsOpened() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals("Create List", driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/span[3]/span/span/span")).getText());
    }

    @When("^I click on wish list button$")
    public void iClickOnWishListButton() {
        driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[1]/div[2]/div[2]/div[2]/div/div/span/div/label/span")).click();
    }

    @And("^I click on create list button$")
    public void iClickOnCreateListButton() {
        driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/form/div[2]/span[3]/span")).click();
    }

    @Then("^I should see item added message on screen$")
    public void iShouldSeeItemAddedMessageOnScreen() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals("1 item added to Wish List", driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/div[3]/span")).getText());

    }

    @When("^I click on view list on list create popup$")
    public void iClickOnContinueShopping() {
        driver.findElement(By.xpath("/html/body/div[6]/div/div/div/div[2]/div[3]/div/div[2]/span[1]/span/a")).click();

    }

    @Then("^I should see wishlist window is opened$")
    public void iShouldSeeWishlistWindowIsOpened() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals("Check out my list on Amazon", driver.findElement(By.xpath("/html/head/meta[10]")).getText());

    }

    @When("^I click on delete item button on first item on the list$")
    public void iClickOnDeleteItemButtonOnFirstItemOnTheList() {
        driver.findElements(By.id("g-items")).get(0).findElement(By.linkText("Delete item")).click();
    }

    @Then("^I should see deleted text on screen$")
    public void iShouldSeeDeletedTextOnScreen() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals("Deleted", driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div/div/div[2]/div[7]/div[3]/div/ul/li/span/div/div[2]/div/div/div")).getText());
    }

    @Given("^I land on  wish list page$")
    public void iLandOnWishListPage() {
        Assert.assertEquals("Wish List", driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div/div/div[2]/div[4]/div/div/div/div/span[1]")).getText());

    }

    @When("^I click on manage list button$")
    public void iClickOnManageListButton() {
        Actions action = new Actions(driver);
        WebElement we = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div/div/div/div[2]/div[4]/div/div/div/div/div[1]/span[2]/a/div[2]"));
        action.moveToElement(we).build().perform();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div/div/ul/li[1]/span/span/a")).click();
    }

    @Then("^I should see manage list window is opened$")
    public void iShouldSeeManageListWindowIsOpened() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertEquals("Manage list", driver.findElement(By.xpath("/html/body/div[7]/div/div/header/h4")).getText());
    }

    @When("^I click on delete list button$")
    public void iClickOnDeleteListButton() {
        driver.findElement(By.xpath("/html/body/div[7]/div/div/div/div[1]/form/div[1]/div/div/span/span/span/input")).click();
    }

    @And("^I click on yes button$")
    public void iClickOnYesButton() {
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[9]/div/div/div/div[1]/div[1]")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("/html/body/div[9]/div/div/div/div[1]/div[2]/span[3]/span/span/input")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        Assert.assertEquals("Lists",driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[2]/div/div[1]/div[1]/span[1]")).getText());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.quit();
    }
}
