package simtran.alfiotest.pages.loggedin.common;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import simtran.core.base.BasePage;

public class DatePickerPopup extends BasePage {
    //**************** Page's locators ****************
    private final By fromDateInput = By.xpath("//input[@name='daterangepicker_start']");
    private final By toDateInput = By.xpath("//input[@name='daterangepicker_end']");
    private final By applyButton = By.xpath("//button[normalize-space()='Apply']");
    private final By cancelButton = By.xpath("//*[contains(@class,'daterangepicker')]//button[normalize-space()='Cancel']");
    private final By firstCalendarArrowLeftButton = By.xpath("//div[@class='calendar second left']//i[contains(@class,'fa-arrow-left')]");
    private final By firstCalendarArrowRightButton = By.xpath("//div[@class='calendar second left']//*[contains(@class,'fa-arrow-right')]");
    private final By firstCalendarHourDropdown = By.xpath("//div[@class='calendar second left']//select[@class='hourselect']");
    private final By firstCalendarMinuteDropdown = By.xpath("//div[@class='calendar second left']//select[@class='minuteselect']");
    private final By secondCalendarArrowLeftButton = By.xpath("//div[@class='calendar first right']//i[contains(@class,'fa-arrow-left')]");
    private final By secondCalendarArrowRightButton = By.xpath("//div[@class='calendar first right']//*[contains(@class,'fa-arrow-right')]");
    private final By secondCalendarHourDropdown = By.xpath("//div[@class='calendar first right']//select[@class='hourselect']");
    private final By secondCalendarMinuteDropdown = By.xpath("//div[@class='calendar first right']//select[@class='minuteselect']");
    int date;
    private final By firstCalendarDateButton = By.xpath(String.format("//*[@class='calendar second left']//*[@class='calendar-date']//td[normalize-space()='%s']", date));
    private final By secondCalendarDateButton = By.xpath(String.format("//*[@class='calendar first right']//*[@class='calendar-date']//td[normalize-space()='%s']", date));

    //**************** Page's attributes ****************
    public String getFromDateInputValue(By datePickerPopup) {
        return getValue(findChildElement(fromDateInput, datePickerPopup));
    }

    public String getToDateInputValue(By datePickerPopup) {
        return getValue(findChildElement(toDateInput, datePickerPopup));
    }

    public String getFirstCalendarHourDropdownValue(By datePickerPopup) {
        return getValue(findChildElement(firstCalendarHourDropdown, datePickerPopup));
    }

    public String getFirstCalendarMinuteDropdownValue(By datePickerPopup) {
        return getValue(findChildElement(firstCalendarMinuteDropdown, datePickerPopup));
    }

    public String getSecondCalendarHourDropdownValue(By datePickerPopup) {
        return getValue(findChildElement(secondCalendarHourDropdown, datePickerPopup));
    }

    public String getSecondCalendarMinuteDropdownValue(By datePickerPopup) {
        return getValue(findChildElement(secondCalendarMinuteDropdown, datePickerPopup));
    }

    //**************** Page's actions ****************
    @Step("Input a date into From Date field")
    public DatePickerPopup inputFromDate(String date, By datePickerPopup) {
        enterText(findChildElement(fromDateInput, datePickerPopup), date);
        return this;
    }

    @Step("Input a date into To Date field")
    public DatePickerPopup inputToDate(String date, By datePickerPopup) {
        enterText(findChildElement(toDateInput, datePickerPopup), date);
        return this;
    }

    @Step("Click the Apply button")
    public void clickApplyButton(By datePickerPopup) {
        click(findChildElement(applyButton, datePickerPopup));
    }

    @Step("Click the Cancel button")
    public void clickCancelButton(By datePickerPopup) {
        click(findChildElement(cancelButton, datePickerPopup));
    }

    @Step("Click the left arrow button of the From Date calendar")
    public DatePickerPopup clickFirstCalendarArrowLeftButton(By datePickerPopup) {
        click(findChildElement(firstCalendarArrowLeftButton, datePickerPopup));
        return this;
    }

    @Step("Click the right arrow button of the From Date calendar")
    public DatePickerPopup clickFirstCalendarArrowRightButton(By datePickerPopup) {
        click(findChildElement(firstCalendarArrowRightButton, datePickerPopup));
        return this;
    }

    @Step("Select a day of month from the From Date calendar")
    public DatePickerPopup clickFirstCalendarDateButton(int dayOfMonth, By datePickerPopup) {
        this.date = dayOfMonth;
        click(findChildElement(firstCalendarDateButton, datePickerPopup));
        return this;
    }

    @Step("Select an hour from the Hour dropdown of the From Date calendar")
    public DatePickerPopup selectFirstCalendarHourDropdown(int option, By datePickerPopup) {
        selectDropdown(findChildElement(firstCalendarHourDropdown, datePickerPopup), option);
        return this;
    }

    @Step("Select a minute from the Minute dropdown of the From Date calendar")
    public DatePickerPopup selectFirstCalendarMinuteDropdown(int option, By datePickerPopup) {
        selectDropdown(findChildElement(firstCalendarMinuteDropdown, datePickerPopup), option);
        return this;
    }

    @Step("Click the left arrow button of the To Date calendar")
    public DatePickerPopup clickSecondCalendarArrowLeftButton(By datePickerPopup) {
        click(findChildElement(secondCalendarArrowLeftButton, datePickerPopup));
        return this;
    }

    @Step("Click the right arrow button of the To Date calendar")
    public DatePickerPopup clickSecondCalendarArrowRightButton(By datePickerPopup) {
        click(findChildElement(secondCalendarArrowRightButton, datePickerPopup));
        return this;
    }

    @Step("Select a day of month from the To Date calendar")
    public DatePickerPopup clickSecondCalendarDateButton(int dayOfMonth, By datePickerPopup) {
        this.date = dayOfMonth;
        click(findChildElement(secondCalendarDateButton, datePickerPopup));
        return this;
    }

    @Step("Select an hour from the Hour dropdown of the To Date calendar")
    public DatePickerPopup selectSecondCalendarHourDropdown(int option, By datePickerPopup) {
        selectDropdown(findChildElement(secondCalendarHourDropdown, datePickerPopup), option);
        return this;
    }

    @Step("Select a minute from the Minute dropdown of the To Date calendar")
    public DatePickerPopup selectSecondCalendarMinuteDropdown(int option, By datePickerPopup) {
        selectDropdown(findChildElement(secondCalendarMinuteDropdown, datePickerPopup), option);
        return this;
    }
}
