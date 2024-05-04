package simtran.alfiotest.pages.loggedin.events;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import simtran.alfiotest.pages.Page;
import simtran.core.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class CreateEventPage extends BasePage {
    //**************** Page's locators ****************
    private final By pageTitle = By.cssSelector(".page-header h1");
    private final By copyFromPreviousEventButton = By.xpath("//button[normalize-space()='Copy from previous event']");
    private final By nameInput = By.id("displayName");
    private final By organizerDropdown = By.id("organizationId");
    private final By formatDropdown = By.id("format");
    private final By locationInput = By.id("location");
    private final By dateInput = By.id("date");
    private final By datePickerPopup = By.xpath("(//div[contains(@class,'daterangepicker dropdown-menu show-calendar')])[1]");
    private final By timezoneDropdown = By.id("timeZone");
    private final By descriptionInputs = By.id("description");
    private final By translationDropdown = By.id("single-button");
    private final By removeDescriptionButtons = By.xpath("//a[contains(@title,'remove') and contains(@ng-click,'removeDescription')]");
    private final By eventUrlInput = By.id("shortName");
    private final By websiteInput = By.id("websiteUrl");
    private final By termAndConditionUrlInput = By.id("termsAndConditionsUrl");
    private final By policyUrlInput = By.id("privacyPolicyUrl");
    private final By logoInput = By.xpath("//input[@type='file']");
    private final By logoErrorMessage = By.cssSelector(".alert.alert-danger.alert-form.ng-invalid");
    private final By entryFeeRadio = By.xpath("//input[@type='radio' and @name='freeOfCharge' and @data-ng-value='false']");
    private final By feeOfChargeRadio = By.xpath("//input[@type='radio' and @name='freeOfCharge' and @data-ng-value='true']");
    private final By maxTicketsInput = By.id("availableSeats");
    private final By regularPriceInput = By.id("regularPrice");
    private final By currencyInput = By.id("currency");
    private final By taxInput = By.id("vatPercentage");
    private final By vatIncludedCheckbox = By.id("vatIncluded");
    private final By onsitePaymentCheckbox = By.xpath("(//input[@type='checkbox' and @ng-click='updatePaymentProxies()'])[1]");
    private final By offlinePaymentCheckbox = By.xpath("(//input[@type='checkbox' and @ng-click='updatePaymentProxies()'])[2]");
    private final By paypalCheckbox = By.xpath("(//input[@type='checkbox' and @ng-click='updatePaymentProxies()'])[3]");
    private final By saferPayCheckbox = By.xpath("(//input[@type='checkbox' and @ng-click='updatePaymentProxies()'])[4]");
    private final By categoryErrorMessage = By.xpath("//div[contains(@class,'alert alert-danger')]/span");
    private final By addNewCategoryButton = By.xpath("//button[contains(text(),'Add new')]");
    private final By ticketCategoryTitle = By.xpath("//ticket-category-detail//h3");
    private final By resetValueButton = By.xpath("//button[contains(text(),'Reset values')]");
    private final By cancelButton = By.xpath("//*[@data-form-obj='editEvent']//button[contains(text(),'Cancel')]");
    private final By saveButton = By.xpath("//button[normalize-space()='Save']");
    private String language;
    private final By translationDropdownOption = By.xpath(String.format("//ul[@class='dropdown-menu']//*[contains(text(),'%s')]", language));

    //**************** Page's attributes ****************
    public String getPageTitle() {
        return getText(pageTitle);
    }

    public String getNameInputValue() {
        return getValue(nameInput);
    }

    public String getOrganizerDropdownValue() {
        return getValue(organizerDropdown);
    }

    public String getFormatDropdownValue() {
        return getValue(formatDropdown);
    }

    public String getLocationInputValue() {
        return getValue(logoInput);
    }

    public String getDateInputValue() {
        return getValue(dateInput);
    }

    public String getTimezoneDropdownValue() {
        return getValue(timezoneDropdown);
    }

    public List<String> getDescriptionInputs() {
        List<String> descriptionInputs = new ArrayList<>();
        for (WebElement description : findElements(descriptionInputs)) {
            descriptionInputs.add(getValue(description));
        }
        return descriptionInputs;
    }

    public String getEventUrlInputValue() {
        return getValue(eventUrlInput);
    }

    public String getWebsiteInputValue() {
        return getValue(websiteInput);
    }

    public String getTermAndConditionUrlInputValue() {
        return getValue(termAndConditionUrlInput);
    }

    public String getPolicyUrlInputValue() {
        return getValue(policyUrlInput);
    }

    public String getLogoErrorMessage() {
        return getText(logoErrorMessage);
    }

    public String getMaxTicketsInputValue() {
        return getValue(maxTicketsInput);
    }

    public String getRegularPriceInputValue() {
        return getValue(regularPriceInput);
    }

    public String getCurrencyInputValue() {
        return getValue(currencyInput);
    }

    public String getTaxInputValue() {
        return getValue(taxInput);
    }

    public String getCategoryErrorMessage() {
        return getText(categoryErrorMessage);
    }

    public String getTicketCategoryTitle() {
        return getText(ticketCategoryTitle);
    }

    //**************** Page's actions ****************
    @Step
    public CopyEventPopup clickCopyFromPreviousEventButton() {
        click(copyFromPreviousEventButton);
        return Page.copyEventPopup();
    }

    @Step
    public CreateEventPage inputName(String name) {
        enterText(nameInput, name);
        return this;
    }

    @Step
    public CreateEventPage selectOrganizationDropdown(String option) {
        selectDropdown(organizerDropdown, option);
        return this;
    }

    @Step
    public CreateEventPage selectFormatDropdown(String option) {
        selectDropdown(formatDropdown, option);
        return this;
    }

    @Step
    public CreateEventPage inputLocation(String location) {
        enterText(locationInput, location);
        return this;
    }

    @Step
    public CreateEventPage clickDateInput() {
        click(dateInput);
        return this;
    }

    @Step
    public CreateEventPage inputDate(String fromDate, String toDate) {
        Page.datePickerPopup()
                .inputFromDate(fromDate, datePickerPopup)
                .inputToDate(toDate, datePickerPopup)
                .clickApplyButton(datePickerPopup);
        return this;
    }

    @Step
    public CreateEventPage inputDate(String dateTime) {
        enterText(dateInput, dateTime);
        return this;
    }

    @Step
    public CreateEventPage selectTimezoneDropdown(String option) {
        selectDropdown(timezoneDropdown, option);
        return this;
    }

    @Step
    public CreateEventPage inputDescriptionIndex(String description, int index) {
        enterText(findElements(descriptionInputs).get(index), description);
        return this;
    }

    @Step
    public CreateEventPage clickTranslationDropdown() {
        click(translationDropdown);
        return this;
    }

    @Step
    public CreateEventPage selectTranslationOption(String language) {
        this.language = language;
        click(translationDropdownOption);
        return this;
    }

    @Step
    public CreateEventPage clickRemoveDescriptionButtonIndex(int index) {
        click(findElements(removeDescriptionButtons).get(index));
        return this;
    }

    @Step
    public CreateEventPage inputEventUrl(String eventUrl) {
        enterText(eventUrlInput, eventUrl);
        return this;
    }

    @Step
    public CreateEventPage inputWebsite(String website) {
        enterText(websiteInput, website);
        return this;
    }

    @Step
    public CreateEventPage inputTermAndConditionUrl(String url) {
        enterText(termAndConditionUrlInput, url);
        return this;
    }

    @Step
    public CreateEventPage inputPolicyUrl(String policyUrl) {
        enterText(policyUrlInput, policyUrl);
        return this;
    }

    @Step
    public CreateEventPage uploadLogoImage(String filePath) {
        enterText(logoInput, filePath);
        return this;
    }

    @Step
    public CreateEventPage uploadLogoImage2(String filePath) {
        // Todo
//        WebElement element = findElement(logoInput);
//        JavascriptExecutor jsexec = (JavascriptExecutor) DriverManager.getDriver();
//        jsexec.executeScript("arguments[0].scrollIntoView(true);", element);
//        jsexec.executeScript("arguments[0].style.visibility = 'visible'", element);
//
//        enterText(element, filePath);

        return this;
    }

    @Step
    public CreateEventPage clickEntryFeeRadio() {
        click(entryFeeRadio);
        return this;
    }

    @Step
    public CreateEventPage clickFreeOfChargeRadio() {
        click(feeOfChargeRadio);
        return this;
    }

    @Step
    public CreateEventPage inputMaxTickets(String maxTickets) {
        enterText(maxTicketsInput, maxTickets);
        return this;
    }

    @Step
    public CreateEventPage inputRegularPrice(String price) {
        enterText(regularPriceInput, price);
        return this;
    }

    @Step
    public CreateEventPage inputCurrency(String currency) {
        enterText(currencyInput, currency);
        return this;
    }

    @Step
    public CreateEventPage inputTax(String tax) {
        enterText(taxInput, tax);
        return this;
    }

    @Step
    public CreateEventPage clickVatIncludedCheckbox() {
        click(vatIncludedCheckbox);
        return this;
    }

    @Step
    public CreateEventPage clickOnsitePaymentCheckbox() {
        click(onsitePaymentCheckbox);
        return this;
    }

    @Step
    public CreateEventPage clickOfflinePaymentCheckbox() {
        click(offlinePaymentCheckbox);
        return this;
    }

    @Step
    public CreateEventPage clickPaypalCheckbox() {
        click(paypalCheckbox);
        return this;
    }

    @Step
    public CreateEventPage clickSaferPayCheckbox() {
        click(saferPayCheckbox);
        return this;
    }

    @Step
    public CreateCategoryPopup clickAddNewCategoryButton() {
        click(addNewCategoryButton);
        return Page.createCategoryPopup();
    }

    @Step
    public CreateEventPage clickResetValueButton() {
        click(resetValueButton);
        return this;
    }

    @Step
    public EventsPage clickCancelButton() {
        click(cancelButton);
        return Page.eventsPage();
    }

    @Step
    public EventsPage clickSaveButton() {
        click(saveButton);
        return Page.eventsPage();
    }
}
