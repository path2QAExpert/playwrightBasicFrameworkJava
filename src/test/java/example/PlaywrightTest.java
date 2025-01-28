package example;

import com.microsoft.playwright.*;
import io.qameta.allure.Attachment;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import java.nio.file.Paths;

public class PlaywrightTest {
    private static Playwright playwright;
    private static Browser browser;
    private Page page;

    @BeforeAll
    public static void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @BeforeEach
    public void setupPage() {
        page = browser.newPage();
    }

    @AfterEach
    public void closePage() {
        page.close();
    }

    @AfterAll
    public static void teardown() {
        browser.close();
        playwright.close();
    }

    @Test
    @Description("TC01_Test login and logout functionality on Sauce Demo site - Standard User")
    public void testLoginAndLogout() {
        navigateToLoginPage();
        performLogin("standard_user", "secret_sauce"); // Replace with actual credentials
        verifyHomePage();
        performLogout();
    }

    @Test
    @Description("TC02_Test login and logout functionality on Sauce Demo site - Visual User")
    public void testProductListPage() {
        navigateToLoginPage();
        performLogin("standard_user", "secret_sauce"); // Replace with actual credentials
        verifyProductList();
    }

    @Step("Navigate to login page")
    public void navigateToLoginPage() {
        page.navigate("https://www.saucedemo.com/");
        attachScreenshot("Navigate to login page");
    }

    @Step("Perform login with username: {0} and password: {1}")
    public void performLogin(String username, String password) {
        page.fill("#user-name", username);
        page.fill("#password", password);
        page.click("#login-button");
        page.waitForSelector(".inventory_list");
        page.waitForTimeout(2000);
        attachScreenshot("Perform login with"+ username +"and"+ password);
    }

    @Step("Verify home page")
    public void verifyHomePage() {
        Assertions.assertTrue(page.title().contains("Swag Labs"));
        attachScreenshot("Verify home page");
    }

    @Step("Verify product list page")
    public void verifyProductList() {
        Assertions.assertTrue(page.isVisible(".inventory_item"));
        attachScreenshot("Verify product list page");
    }

    @Step("Perform logout")
    public void performLogout() {
        page.click("#react-burger-menu-btn");
        page.waitForSelector("#logout_sidebar_link");
        page.click("#logout_sidebar_link");
        page.waitForTimeout(2000);
        Assertions.assertTrue(page.title().contains("Swag Labs"));
        attachScreenshot("Perform logout");
    }

    @Attachment(value = "{0}", type = "image/png")
    public byte[] attachScreenshot(String name) {
        return page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(name.replaceAll("[^a-zA-Z0-9]", "_") + ".png")).setFullPage(true));
    }
}
