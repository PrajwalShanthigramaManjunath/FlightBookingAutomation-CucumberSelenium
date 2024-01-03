package CucumberFramework.steps;

import CucumberFramework.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.time.Duration;
import java.util.List;


public class FlightBookingSteps {

    private WebDriver driver;

    public FlightBookingSteps() {
        this.driver = DriverSingleton.getDriver();
    }

    @Given("User navigates to the Ryanair website")
    public void user_navigates_to_the_ryanair_website() {
        // Write code here that turns the phrase above into concrete actions
        driver.get("https://www.ryanair.com/ie/en/");
    }

    @When("User accepts cookies")
    public void user_accepts_cookies() {
        driver.findElement(By.cssSelector("button[data-ref='cookie.accept-all']")).click();
    }

    @When("User searches for a one-way flight")
    public void user_searches_for_a_one_way_flight() {
        driver.findElement(By.cssSelector("label[for='ry-radio-button--0']")).click();
    }
    @When("User selects the departure {string}")
    public void user_selects_the_departure(String location) throws InterruptedException {


        System.out.println("User has selected the departure airport as " + location);

        // Find the "From" input field by its ID

        WebElement fromInput = driver.findElement(By.id("input-button__departure"));

        // Clear input field

        fromInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, location);

        Thread.sleep(3000);

        //Verifying if the entered airport is present

        List<WebElement> airportElements = driver.findElements(By.xpath("//div[@class='list__airports-scrollable-container small-height']"));

        boolean locationFound = false;
        WebElement desiredElement = null;

        for (WebElement element : airportElements) {
            String elementText = element.getText().toLowerCase();
            if (elementText.contains(location.toLowerCase())) {
                locationFound = true;
                break;
            }
        }

        if (!locationFound) {
            throw new RuntimeException("Given departure airport " + location + " is not present in the search results.");
        } else {
            fromInput.sendKeys(Keys.ENTER);;
        }

    }

    @When("User selects the destination {string}")
    public void user_selects_the_destination(String location) throws Throwable {

        System.out.println("User has selected the destination airport as " + location);

        // Find the "From" input field by its ID

        WebElement fromInput = driver.findElement(By.id("input-button__destination"));

        // Clear input field

        fromInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE, location);

        Thread.sleep(3000);

        //Verifying if the entered airport is present

        List<WebElement> airportElements = driver.findElements(By.xpath("//div[@class='list__airports-scrollable-container large-height']"));

        boolean locationFound = false;
        WebElement desiredElement = null;

        for (WebElement element : airportElements) {
            String elementText = element.getText().toLowerCase();
            if (elementText.contains(location.toLowerCase())) {
                locationFound = true;
                break;
            }
        }

        if (!locationFound) {
            throw new RuntimeException("Given destination airport " + location + " is not present in the search results.");
        } else {
            fromInput.sendKeys(Keys.ENTER);
        }
    }

    @When("User selects the departure date {string}")
    public void user_selects_the_departure_date(String date) throws Throwable {
        driver.findElement(By.cssSelector("[data-ref='input-button__dates-from']")).click();
        Thread.sleep(8000);

        {
            WebElement selectedDateElement = findDateElement(date);
            if (selectedDateElement != null) {
                selectedDateElement.click();
            } else {
                throw new RuntimeException("The specified date is not available");
            }
        }
//        driver.findElement(By.cssSelector("div:nth-of-type(7) > .m-toggle__month")).click();
//        driver.findElement(By.cssSelector(".datepicker__calendars .datepicker__calendar:nth-child(3) .ng-star-inserted:nth-of-type(2) [tabindex]")).click();

    }
    private WebElement findDateElement(String date) {
        List<WebElement> dateElements = driver.findElements(By.cssSelector(".calendar-body__cell[tabindex='0']"));
        for (WebElement element : dateElements) {
            if (element.getAttribute("data-id").equals(date)) {
                return element;
            }
        }
        return null;
    }


    @When("User specifies the number of adults")
    public void user_specifies_the_number_of_adults() throws Throwable {
        driver.findElement(By.cssSelector(".passengers__confirm-button.ry-button--anchor.ry-button--anchor-blue")).click();
    }

    @When("User initiates the search")
    public void user_initiates_the_search() {
        // Write code here that turns the phrase above into concrete actions
        driver.findElement(By.cssSelector("button[aria-label='Search']")).click();
    }


    @Then("User should land on the flight search results page")
    public void user_should_land_on_the_flight_search_results_page() {
        try {
            Thread.sleep(3000); // Adding a delay of 3 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement reviewAndPay = driver.findElement(By.xpath("//span[contains(text(), 'Review & Pay')]"));
        Assert.assertTrue(reviewAndPay.isDisplayed());
    }


    @When("User selects a flight and chooses the fare")
    public void user_selects_a_flight_and_chooses_the_fare() throws Throwable {

        Thread.sleep(3000);
        driver.findElement(By.xpath("//button[normalize-space()='Select']")).click();
        Thread.sleep(4000);

    }

    @When("User confirms the Basic travel light option")
    public void user_confirms_the_basic_travel_light_option() throws Throwable {

        try {
            WebElement element = driver.findElement(By.xpath("//div[@class='fare-table__fare-column-border fare-table__fare-column-border--regular fare-table__fare-column-border--not-recommended']"));
            element.click();
            Thread.sleep(4000);
        } catch (NoSuchElementException e) {

            e.printStackTrace();
        }
    }


    @Then("Regular is ideal pop-up should appear")
    public void regular_is_ideal_pop_up_should_appear() {
        WebElement element = driver.findElement(By.xpath("//*[contains(text(), 'Continue with Basic')]"));
        Assert.assertTrue(element.isDisplayed(), "Continue with Basic text is not displayed");
    }

    @Then("User continues with Basic fare")
    public void user_continues_with_basic_fare() throws InterruptedException {
        driver.findElement(By.cssSelector(".fare-footer__submit-btn.ry-button--outline-light-blue")).click();
        Thread.sleep(4000);
    }
    @Then("User opts to log in later")
    public void user_opts_to_log_in_later() throws InterruptedException {

        driver.findElement(By.cssSelector(".login-touchpoint__login-later.title-m-lg.title-m-sm")).click();
        Thread.sleep(4000);

    }

    @Then("User selects the title")
    public void user_selects_the_title() throws Throwable {
        Thread.sleep(4000);
        driver.findElement(By.cssSelector(".dropdown__toggle.body-l-lg.body-l-sm")).click();
        driver.findElement(By.cssSelector("button[class='dropdown-item__link dropdown-item__link--highlighted']")).click();
    }

    @Then("User enters the First Name")
    public void user_enters_the_First_Name() throws Throwable {
        driver.findElement(By.xpath("//input[@id='form.passengers.ADT-0.name']")).sendKeys("TestFirst");
    }

    @Then("User enters the Last Name")
    public void user_enters_the_Last_Name() throws Throwable {
        driver.findElement(By.xpath("//input[@id='form.passengers.ADT-0.surname']")).sendKeys("TestLast");
        Thread.sleep(2000);
    }

    @When("User proceeds")
    public void user_proceeds() throws InterruptedException {
        driver.findElement(By.xpath("//button[normalize-space()='Continue']")).click();
        Thread.sleep(2000);
        clickNoThanksOrContinue();
    }

    @When("User adds recommended seats")
    public void user_adds_recommended_seats() throws InterruptedException {
        driver.findElement(By.xpath("//button[normalize-space()='Add recommended seats']")).click();
        clickNoThanksOrContinue();

    }

    @When("User selects 20 kg baggage options")
    public void user_selects_20_kg_baggage_options() throws InterruptedException {
        // Code to select baggage options


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement baggageLabel = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("label[for='ry-radio-button--0']")));
        baggageLabel.click();
        WebElement counterButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".counter__button-wrapper--enabled")));
        counterButton.click();
        clickNoThanksOrContinue();

//        driver.findElement(By.cssSelector("label[for='ry-radio-button--0']")).click();
//        driver.findElement(By.cssSelector(".counter__button-wrapper--enabled")).click();
    }

    @Then("User continues to Car Hire page")
    public void user_continues_to_car_hire_page() throws InterruptedException {

        driver.findElement(By.xpath("//button[normalize-space()='Continue']")).click();
        // Wait for the "No thanks" option to appear and click it if present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        clickNoThanksOrContinue();
    }

    @Then("User proceeds from Extras page")
    public void user_proceeds_from_extras_page() throws InterruptedException {
        clickNoThanksOrContinue();
        clickContinueButton();
    }

    @Then("User proceeds towards payment")
    public void user_proceeds_towards_payment() throws InterruptedException {
        clickNoThanksOrContinue();
        clickContinueButton();
    }

    @Then("Login pop-up appears before payment")
    public void login_pop_up_appears_before_payment() {
        WebElement loginText = driver.findElement(By.xpath("//*[contains(text(), 'Log in')]"));
        Assert.assertNotNull(loginText, "Log in to continue text is not present on the page");
    }

    private void clickNoThanksOrContinue() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        try {
            WebElement noThanksButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), 'No thanks') or contains(text(), 'No, thanks')]")));
            noThanksButton.click();
            Thread.sleep(2000);
        } catch (TimeoutException e) {
            System.out.println("No thanks option not found, proceeding with next action");
            Thread.sleep(2000);
        }
    }
    private void clickContinueButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        boolean continueClicked = true;
        while (continueClicked) {
            try {
                WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Continue']")));
                continueButton.click();
                Thread.sleep(2000);
            } catch (TimeoutException | InterruptedException e) {
                continueClicked = false;
            }
        }
    }



}
