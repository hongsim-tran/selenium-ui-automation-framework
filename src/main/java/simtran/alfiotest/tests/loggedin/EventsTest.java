package simtran.alfiotest.tests.loggedin;

import org.testng.annotations.Test;
import simtran.alfiotest.datafactory.EventCategoryDataFactory;
import simtran.alfiotest.datafactory.EventDataFactory;
import simtran.alfiotest.datafactory.OrganizationDataFactory;
import simtran.alfiotest.models.EventCategoryModel;
import simtran.alfiotest.models.EventModel;
import simtran.alfiotest.models.OrganizationModel;
import simtran.alfiotest.pages.Page;

public class EventsTest extends PreconditionBase {
    String timezone = "Asia/Ho_Chi_Minh";

    @Test(description = "Create new event category - Valid data in all fields")
    public void createEventCategoryWithValidData() {

        EventCategoryModel category = EventCategoryDataFactory.createValidEventCategoryData();

        Page.navigation()
                .clickEventsMenu()
                .clickFirstCreateNewEventButton()
                .inputMaxTickets("1");
        createAnEventCategory(category);

        softAssert.assertEquals(Page.createEventPage().getTicketCategoryTitle(), category.getName());
        softAssert.assertAll();
    }

    @Test(description = "Create new event - Valid data in all fields", enabled = false)
    public void createEventWithValidData() {
        OrganizationModel organization = OrganizationDataFactory.createValidOrganizationData();
        EventCategoryModel category = EventCategoryDataFactory.createValidEventCategoryData();
        EventModel event = EventDataFactory.createValidEventData();
        event.setOrganizer(organization.getOrgName());

        // Create an organization
        Page.navigation().clickOrganizationsMenu();
        createAnOrganization(organization);

        Page.navigation().clickEventsMenu();
        createAnEvent(event, category);
    }
}
