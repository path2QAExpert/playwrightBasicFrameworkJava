# PlaywrightBasicFrameworkJava

This repository contains a basic framework for automated web UI testing using Playwright with Java. It demonstrates how to set up Playwright, launch a browser, navigate to a web page, perform user actions (like logging in and out), verify the results, and generate detailed reports using Allure.

## Prerequisites

- Java (JDK 8 or later)
- Maven

## Maven Dependencies

Add the following dependencies to your `pom.xml` file:

```xml
<dependencies>
    <dependency>
        <groupId>com.microsoft.playwright</groupId>
        <artifactId>playwright</artifactId>
        <version>1.49.0</version>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.11.4</version>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.11.4</version>
    </dependency>
    <dependency>
        <groupId>io.qameta.allure</groupId>
        <artifactId>allure-junit5</artifactId>
        <version>2.12.0</version>
    </dependency>
</dependencies>

i have added allure report as well. create a read me for this
Sure! Here’s a README.mdfile for your GitHub repository with the addition of Allure reporting:

markdown
# PlaywrightBasicFrameworkJava

This repository contains a basic framework for automated web UI testing using Playwright with Java. It demonstrates how to set up Playwright, launch a browser, navigate to a web page, perform user actions (like logging in and out), verify the results, and generate detailed reports using Allure.

## Prerequisites

- Java (JDK 8 or later)
- Maven

## Maven Dependencies

Add the following dependencies to your `pom.xml` file:

```xml
<dependencies>
    <dependency>
        <groupId>com.microsoft.playwright</groupId>
        <artifactId>playwright</artifactId>
        <version>1.49.0</version>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.11.4</version>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.11.4</version>
    </dependency>
    <dependency>
        <groupId>io.qameta.allure</groupId>
        <artifactId>allure-junit5</artifactId>
        <version>2.12.0</version>
    </dependency>
</dependencies>


Playwright Basic Framework
Below is the Java code for the Playwright basic framework:

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
        attachScreenshot("Perform login with " + username + " and " + password);
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

Run the tests:

mvn clean test

Generating and Viewing Allure Reports

Generate the Allure report:
mvn clean test

Serve the Allure report:
mvn allure:serve

i have added allure report as well. create a read me for this
Sure! Here’s a README.mdfile for your GitHub repository with the addition of Allure reporting:

markdown
# PlaywrightBasicFrameworkJava

This repository contains a basic framework for automated web UI testing using Playwright with Java. It demonstrates how to set up Playwright, launch a browser, navigate to a web page, perform user actions (like logging in and out), verify the results, and generate detailed reports using Allure.

## Prerequisites

- Java (JDK 8 or later)
- Maven

## Maven Dependencies

Add the following dependencies to your `pom.xml` file:

```xml
<dependencies>
    <dependency>
        <groupId>com.microsoft.playwright</groupId>
        <artifactId>playwright</artifactId>
        <version>1.49.0</version>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.11.4</version>
    </dependency>
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.11.4</version>
    </dependency>
    <dependency>
        <groupId>io.qameta.allure</groupId>
        <artifactId>allure-junit5</artifactId>
        <version>2.12.0</version>
    </dependency>
</dependencies>
Playwright Basic Framework
Below is the Java code for the Playwright basic framework:

java
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
        attachScreenshot("Perform login with " + username + " and " + password);
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
Running the Tests
Clone the repository:

bash
git clone <repository_url>
Navigate to the project directory:

bash
cd PlaywrightBasicFrameworkJava
Run the tests:

bash
mvn clean test
Generating and Viewing Allure Reports
Generate the Allure report:

bash
mvn allure:report
Serve the Allure report:

bash
mvn allure:serve

Explanation
Setup and Teardown: Initializes and closes Playwright and the browser.

Test Methods: Contains test cases for login and logout functionality and product list verification.

Step Methods: Contains reusable steps for navigation, login, verification, and logout.

Screenshot Attachments: Captures and attaches screenshots to the Allure report for detailed visibility.

Conclusion
This framework provides an excellent starting point for automated web UI testing using Playwright and Java, with detailed reporting using Allure. Feel free to modify and extend it to suit your testing needs.

