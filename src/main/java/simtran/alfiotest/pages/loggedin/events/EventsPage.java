package simtran.alfiotest.pages.loggedin.events;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import simtran.alfiotest.pages.Page;
import simtran.core.base.BasePage;

public class EventsPage extends BasePage {

    //**************** Page's locators ****************
    private final By pageTitle = By.xpath("//div[@class='container ng-scope']/h1");
    private final By firstCreateNewEventButton = By.xpath("//a[contains(text(),'create new event')]");
    private final By secondCreateNewEventButton = By.xpath("//a[contains(text(),'Create new event')]");
    private final By loadExpiredEventsButton = By.xpath("//button[text()='Load expired events']");

    //**************** Page's attributes ****************
    public String getPageTitle() {
        return getText(pageTitle);
    }

    //**************** Page's actions ****************
    @Step("Click the first Create New Event button")
    public CreateEventPage clickFirstCreateNewEventButton() {
        click(firstCreateNewEventButton);
        return Page.createEventPage();
    }

    @Step("Click the second Create New Event button")
    public CreateEventPage clickSecondCreateNewEventButton() {
        click(secondCreateNewEventButton);
        return Page.createEventPage();
    }

    @Step("Click the Load Expired Events button")
    public EventsPage clickLoadExpiredEventsButton() {
        click(loadExpiredEventsButton);
        return this;
    }
}
