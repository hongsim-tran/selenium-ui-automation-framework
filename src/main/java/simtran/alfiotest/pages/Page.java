package simtran.alfiotest.pages;

import simtran.alfiotest.pages.loggedin.common.DatePickerPopup;
import simtran.alfiotest.pages.loggedin.common.Footer;
import simtran.alfiotest.pages.loggedin.common.Navigation;
import simtran.alfiotest.pages.loggedin.events.CopyEventPopup;
import simtran.alfiotest.pages.loggedin.events.CreateCategoryPopup;
import simtran.alfiotest.pages.loggedin.events.CreateEventPage;
import simtran.alfiotest.pages.loggedin.events.EventsPage;
import simtran.alfiotest.pages.loggedin.organizations.OrganizationsPage;
import simtran.alfiotest.pages.loggedin.others.*;
import simtran.alfiotest.pages.prelogin.LoginPage;
import simtran.core.base.BasePage;
import simtran.core.utils.MyLogger;

public class Page {

    private static <TPage extends BasePage> TPage getInstance(Class<TPage> pageClass) {
        try {
            return pageClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            MyLogger.error(e.getMessage());
        }
        return null;
    }

    public static Navigation navigation() {
        return getInstance(Navigation.class);
    }

    public static Footer footer() {
        return getInstance(Footer.class);
    }

    public static LoginPage loginPage() {
        return getInstance(LoginPage.class);
    }

    public static ApiKeysPage apiKeysPage() {
        return getInstance(ApiKeysPage.class);
    }

    public static ConfigurationPage configurationPage() {
        return getInstance(ConfigurationPage.class);
    }

    public static EventsPage eventsPage() {
        return getInstance(EventsPage.class);
    }

    public static ExtensionPage extensionPage() {
        return getInstance(ExtensionPage.class);
    }

    public static GroupsPage groupsPage() {
        return getInstance(GroupsPage.class);
    }

    public static OrganizationsPage organizationsPage() {
        return getInstance(OrganizationsPage.class);
    }

    public static SubscriptionsPage subscriptionsPage() {
        return getInstance(SubscriptionsPage.class);
    }

    public static UsersPage usersPage() {
        return getInstance(UsersPage.class);
    }

    public static EditAccountPage editAccountPage() {
        return getInstance(EditAccountPage.class);
    }

    public static CreateEventPage createEventPage() {
        return getInstance(CreateEventPage.class);
    }

    public static CreateCategoryPopup createCategoryPopup() {
        return getInstance(CreateCategoryPopup.class);
    }

    public static DatePickerPopup datePickerPopup() {
        return getInstance(DatePickerPopup.class);
    }

    public static CopyEventPopup copyEventPopup() {
        return getInstance(CopyEventPopup.class);
    }

}
