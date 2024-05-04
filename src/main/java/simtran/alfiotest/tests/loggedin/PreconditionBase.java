package simtran.alfiotest.tests.loggedin;

import org.openqa.selenium.Cookie;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;
import simtran.alfiotest.dbqueries.Queries;
import simtran.alfiotest.models.EventCategoryModel;
import simtran.alfiotest.models.EventModel;
import simtran.alfiotest.models.OrganizationModel;
import simtran.alfiotest.pages.Page;
import simtran.alfiotest.pages.loggedin.events.CreateEventPage;
import simtran.alfiotest.pages.loggedin.events.EventsPage;
import simtran.alfiotest.pages.loggedin.organizations.OrganizationsPage;
import simtran.core.base.BaseTest;
import simtran.core.utils.DBConnection;
import simtran.core.utils.MyLogger;
import simtran.core.utils.RandomGenerator;
import simtran.core.wdm.DriverManager;

import java.util.Set;

import static simtran.core.config.ConfigManager.config;

public class PreconditionBase extends BaseTest {
    public final String imgFilePath = System.getProperty("user.dir") + "src/resources/test-data/image-test.png";
    private Set<Cookie> cookies;
    protected String target;

    @BeforeClass(alwaysRun = true)
    @Parameters ({"target", "browser"})
    protected void getLoginCookies(String target, String browser) {
        MyLogger.debug("Start getting cookies...!");
        driver = new DriverManager().createDriverInstance(browser);
        DriverManager.setDriver(driver, target);

        DriverManager.getDriver().get(config(target).baseUrl());

        Page.loginPage()
                .inputUsername(config(target).username())
                .inputPassword(config(target).password())
                .clickCaptchaCheckbox()
                .clickLoginButton();

        cookies = DriverManager.getDriver().manage().getCookies();
        this.target = target;

        MyLogger.debug("End getting cookies...!");
        DriverManager.quit();
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters ({"target", "browser"})
    @Override
    public void setup(String target, String browser) {
        softAssert = new SoftAssert();

        MyLogger.debug("Start driver...!");
        driver = new DriverManager().createDriverInstance(browser);
        DriverManager.setDriver(driver, target);

        DriverManager.getDriver().get(config(target).baseUrl());

        MyLogger.debug("Add cookies ...");
        for (Cookie cookie : cookies) {
            DriverManager.getDriver().manage().addCookie(cookie);
        }

        MyLogger.debug("Open the web page at: " + config(target).baseUrl());
        DriverManager.getDriver().get(config(target).baseUrl());
    }

    @AfterMethod(alwaysRun = true)
    @Override
    protected void tearDown() {
        MyLogger.debug("Delete all cookies ...");
        DriverManager.getDriver().manage().deleteAllCookies();

        MyLogger.debug("Stop driver...!");
        DriverManager.quit();
    }

    public void deleteAllOrganizationsFromDB() {
        MyLogger.debug("Delete all organizations from database");
        DBConnection.getConnection(target);
        DBConnection.executeUpdate(Queries.deleteAllInvoiceSequences);
        DBConnection.executeUpdate(Queries.deleteAllOrganization);
        DBConnection.closeConnection();
    }

    public OrganizationsPage createAnOrganization(OrganizationModel organization) {
        Page.organizationsPage()
                .clickAddNewButton()
                .inputOrgName(organization.getOrgName())
                .inputOrgEmail(organization.getOrgEmail())
                .inputOrgDescription(organization.getOrgDescription())
                .inputSlug(organization.getSlug())
                .inputExternalId(organization.getExternalId())
                .clickSaveButton();

        return Page.organizationsPage();
    }

    public CreateEventPage createAnEventCategory(EventCategoryModel category) {
        Page.createEventPage()
                .clickAddNewCategoryButton()
                .inputName(category.getName())
                .clickAddDescriptionDropdown()
                .clickDescriptionOption("English")
                .inputDescriptionToFieldIndex(category.getDescription(), 0)
                .selectVisibilityDropdown(category.getVisibility())
                .selectStrategyDropdown(category.getStrategy());
        if (category.getStrategy().equals(EventCategoryModel.Strategy.FIXED.toString()))
            Page.createCategoryPopup()
                    .inputMaxTickets(Integer.toString(category.getNumberOfTickets()));
        Page.createCategoryPopup()
                .inputDate(category.getDateTime())
                .inputPrice(Double.toString(category.getPrice()))
                .inputCode(category.getCode())
                .selectValidityDropdown(category.getValidity());
        if (category.getValidity().equals(EventCategoryModel.Validity.CUSTOM.toString()))
            Page.createCategoryPopup()
                    .inputValidFromDate(category.getValidFromDate())
                    .inputValidToDate(category.getValidTodDate());

        Page.createCategoryPopup()
                .selectCheckInAllowedDropdown(category.getCheckIn());

        if (category.getCheckIn().equals(EventCategoryModel.CheckIn.CUSTOM.toString()))
            Page.createCategoryPopup()
                    .inputCheckInFromDate(category.getCheckInFromDate())
                    .inputCheckInToDate(category.getCheckInToDate());

        Page.createCategoryPopup()
                .selectBadgeColorDropdown(category.getBadgeColor())
                .clickSaveButton();

        return Page.createEventPage();
    }

    public EventsPage createAnEvent(EventModel event, EventCategoryModel category) {
        Page.eventsPage()
                .clickFirstCreateNewEventButton()
                .inputName(event.getName())
                .selectOrganizationDropdown(event.getOrganizer())
                .selectFormatDropdown(RandomGenerator.getRandomValueOfEnum(EventModel.Format.class).toString());
        if (event.getFormat().equals(EventModel.Format.INPERSON.toString()) || event.getFormat().equals(EventModel.Format.HYBRID.toString())) {
            Page.createEventPage()
                    .inputLocation(event.getLocation());
        }
        Page.createEventPage()
                .inputDate(event.getDateTime())
                .selectTimezoneDropdown("Asia/Ho_Chi_Minh")
                .inputDescriptionIndex(event.getDescription(), 0)
                .inputEventUrl(event.getEventUrl())
                .inputWebsite(event.getWebsiteLink())
                .inputTermAndConditionUrl(event.getTermConditionUrl())
                .inputPolicyUrl(event.getPolicyUrl())
                .uploadLogoImage(imgFilePath);
        if (event.getIsFeeRequested()) {
            Page.createEventPage()
                    .clickEntryFeeRadio()
                    .inputRegularPrice(Double.toString(event.getRegularPrice()))
                    .inputCurrency(event.getCurrency())
                    .inputTax(Double.toString(event.getTax()));
            if (event.getTaxIncluded())
                Page.createEventPage().clickVatIncludedCheckbox();
            if (event.getOnsitePayment())
                Page.createEventPage().clickOnsitePaymentCheckbox();
            if (event.getOfflinePayment())
                Page.createEventPage().clickOfflinePaymentCheckbox();
            if (event.getPaypal())
                Page.createEventPage().clickPaypalCheckbox();
            if (event.getSaferPay())
                Page.createEventPage().clickSaferPayCheckbox();
        }
        Page.createEventPage()
                .inputMaxTickets(Integer.toString(event.getMaxTickets()));

        createAnEventCategory(category)
                .clickSaveButton();
        return Page.eventsPage();
    }
}
