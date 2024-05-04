package simtran.alfiotest.pages.loggedin.events;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import simtran.alfiotest.pages.Page;
import simtran.core.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class CreateCategoryPopup extends BasePage {

    //**************** Page's locators ****************
    private final By nameInput = By.id("name");
    private final By addDescriptionDropdown = By.xpath("//div[@class='modal-body']//button[@id='single-button']");
    private final By descriptionDropdownOptions = By.xpath("//form[@name='categoryForm']//div[contains(@class,'dropdown')]//li[@role='menuitem']");
    private final By descriptionInputs = By.xpath("//form[@name='categoryForm']//textarea[contains(@id,'description')]");
    private final By visibilityDropdown = By.id("tokenGenerationRequested");
    private final By strategyDropdown = By.id("bounded");
    private final By maxTicketInput = By.id("maxTickets");
    private final By dateInput = By.id("dateString");
    private final By datePickerPopup = By.xpath("(//div[contains(@class,'daterangepicker dropdown-menu show-calendar')])[2]");
    private final By priceInput = By.id("price");
    private final By codeInput = By.id("code");
    private final By validityDropdown = By.id("validityType");
    private final By validFromDateInput = By.id("customValidityStartToString");
    private final By validToDateInput = By.id("customValidityEndToString");
    private final By checkInAllowedDropdown = By.id("checkInAllowed");
    private final By multiCheckInDropdown = By.id("ticketCheckInStrategy");
    private final By checkInFromDateInput = By.id("validCheckInFromString");
    private final By checkInToDateInput = By.id("validCheckInToString");
    private final By badgeColorDropdown = By.id("badgeColor");
    private final By cancelButton = By.xpath("//form[@name='categoryForm']//button[normalize-space()='Cancel']");
    private final By saveButton = By.xpath("//form[@name='categoryForm']//button[normalize-space()='Save']");

    //**************** Page's attributes ****************
    public String getNameInputValue() {
        return getValue(nameInput);
    }

    public List<String> getAllDescriptionDropdownOptionsText() {
        List<String> options = new ArrayList<>();
        for (WebElement optionEl : findElements(descriptionDropdownOptions)) {
            options.add(getText(optionEl));
        }
        return options;
    }

    public List<String> getDescriptionInputValues() {
        List<String> descriptions = new ArrayList<>();
        for (WebElement descriptionEl : findElements(descriptionInputs)) {
            descriptions.add(getValue(descriptionEl));
        }
        return descriptions;
    }

    public String getVisibilityInputValue() {
        return getValue(visibilityDropdown);
    }

    public String getStrategyInputValue() {
        return getValue(strategyDropdown);
    }

    public String getMaxTicketInputValue() {
        return getValue(maxTicketInput);
    }

    public String getDateInputValue() {
        return getValue(dateInput);
    }

    public String getPriceInputValue() {
        return getValue(priceInput);
    }

    public String getCodeInputValue() {
        return getValue(codeInput);
    }

    public String getValidityInputValue() {
        return getValue(validityDropdown);
    }

    public String getValidFromDateInputValue() {
        return getValue(validFromDateInput);
    }

    public String getValidToDateInputValue() {
        return getValue(validToDateInput);
    }

    public String getCheckInAllowedInputValue() {
        return getValue(checkInAllowedDropdown);
    }

    public String getCheckInFromDateInputValue() {
        return getValue(checkInFromDateInput);
    }

    public String getCheckInToDateInputValue() {
        return getValue(checkInToDateInput);
    }

    public String getMultiCheckInInputValue() {
        return getValue(multiCheckInDropdown);
    }

    public String getBadgeColorInputValue() {
        return getValue(badgeColorDropdown);
    }

    //**************** Page's actions ****************
    @Step("Input a name into the category name")
    public CreateCategoryPopup inputName(String name) {
        enterText(nameInput, name);
        return this;
    }

    @Step("Click the Add description dropdown")
    public CreateCategoryPopup clickAddDescriptionDropdown() {
        click(addDescriptionDropdown);
        return this;
    }

    @Step("Select a description option from Description dropdown")
    public CreateCategoryPopup clickDescriptionOption(String option) {
        for (WebElement element : findElements(descriptionDropdownOptions)) {
            if (getText(element).equals(option))
                click(element);
        }
        return this;
    }

    @Step("Input description into Description field")
    public CreateCategoryPopup inputDescriptionToFieldIndex(String text, int index) {
        enterText(findElements(descriptionInputs).get(index), text);
        return this;
    }

    @Step("Select a visibility option from the dropdown")
    public CreateCategoryPopup selectVisibilityDropdown(String option) {
        selectDropdown(visibilityDropdown, option);
        return this;
    }

    @Step("Select a strategy option from the dropdown")
    public CreateCategoryPopup selectStrategyDropdown(String option) {
        selectDropdown(strategyDropdown, option);
        return this;
    }

    @Step
    public CreateCategoryPopup inputMaxTickets(String noOfTicket) {
        enterText(maxTicketInput, noOfTicket);
        return this;
    }

    @Step("Click the date input field")
    public CreateCategoryPopup clickDateInput() {
        click(dateInput);
        return this;
    }

    @Step("Input From date and To date into the Date field")
    public CreateCategoryPopup inputDate(String fromDate, String toDate) {
        Page.datePickerPopup()
                .inputFromDate(fromDate, datePickerPopup)
                .inputToDate(toDate, datePickerPopup)
                .clickApplyButton(datePickerPopup);
        return this;
    }

    @Step
    public CreateCategoryPopup inputDate(String dateTime) {
        enterText(dateInput, dateTime);
        return this;
    }

    @Step("Input price into the Price field")
    public CreateCategoryPopup inputPrice(String price) {
        enterText(priceInput, price);
        return this;
    }

    @Step("Input a code into the Code field")
    public CreateCategoryPopup inputCode(String code) {
        enterText(codeInput, code);
        return this;
    }

    @Step("Select an option from Ticket is valid dropdown")
    public CreateCategoryPopup selectValidityDropdown(String option) {
        selectDropdown(validityDropdown, option);
        return this;
    }

    @Step
    public CreateCategoryPopup inputValidFromDate(String date) {
        enterText(validFromDateInput, date);
        return this;
    }

    @Step
    public CreateCategoryPopup inputValidToDate(String date) {
        enterText(validToDateInput, date);
        return this;
    }

    @Step("Select an option from Check in can be done dropdown")
    public CreateCategoryPopup selectCheckInAllowedDropdown(String option) {
        selectDropdown(checkInAllowedDropdown, option);
        return this;
    }

    @Step("Select an option from Multi-day checkin dropdown")
    public CreateCategoryPopup selectMultiCheckInDropdown(String option) {
        selectDropdown(multiCheckInDropdown, option);
        return this;
    }

    @Step
    public CreateCategoryPopup inputCheckInFromDate(String date) {
        enterText(checkInFromDateInput, date);
        return this;
    }

    @Step
    public CreateCategoryPopup inputCheckInToDate(String date) {
        enterText(checkInToDateInput, date);
        return this;
    }

    @Step("Select an option from Badge color dropdown")
    public CreateCategoryPopup selectBadgeColorDropdown(String option) {
        selectDropdown(badgeColorDropdown, option);
        return this;
    }

    @Step("Click the Cancel button")
    public CreateEventPage clickCancelButton() {
        click(cancelButton);
        return Page.createEventPage();
    }

    @Step("Click the Save button")
    public CreateEventPage clickSaveButton() {
        click(saveButton);
        return Page.createEventPage();
    }
}
