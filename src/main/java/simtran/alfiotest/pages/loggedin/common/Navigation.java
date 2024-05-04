package simtran.alfiotest.pages.loggedin.common;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import simtran.alfiotest.pages.Page;
import simtran.alfiotest.pages.loggedin.events.EventsPage;
import simtran.alfiotest.pages.loggedin.organizations.OrganizationsPage;
import simtran.alfiotest.pages.loggedin.others.*;
import simtran.alfiotest.pages.prelogin.LoginPage;
import simtran.core.base.BasePage;

public class Navigation extends BasePage {

    //**************** Page's locators ****************
    private final By alfioLogo = By.cssSelector("img[alt='Alf.io']");
    private final By eventsMenu = By.xpath("//a[contains(text(),'Events')]");
    private final By subscriptionsMenu = By.xpath("//a[contains(text(),'Subscriptions')]");
    private final By organizationsMenu = By.xpath("//a[contains(text(),'Organizations')]");
    private final By usersMenu = By.xpath("//a[contains(text(),'Users')]");
    private final By apiKeysMenu = By.xpath("//a[contains(text(),'Api Keys')]");
    private final By groupsMenu = By.xpath("//a[contains(text(),'Groups')]");
    private final By configurationMenu = By.xpath("//a[contains(text(),'Configuration')]");
    private final By extensionMenu = By.xpath("//a[contains(text(),'Extension')]");

    private final By loggedInUserText = By.cssSelector(".nav.navbar-nav li[role='presentation']:first-child");
    private final By editAccountButton = By.cssSelector(".nav.navbar-nav li[role='presentation']:nth-child(2)");
    private final By logoutButton = By.cssSelector(".nav.navbar-nav li[role='presentation']:last-child");

    //**************** Page's attributes ****************
    public String getLoggedInUserText() {
        return getText(loggedInUserText);
    }

    //**************** Page's actions ****************
    @Step
    public EventsPage clickEventsMenu() {
        click(eventsMenu);
        return Page.eventsPage();
    }

    @Step
    public SubscriptionsPage clickSubscriptionMenu() {
        click(subscriptionsMenu);
        return Page.subscriptionsPage();
    }

    @Step
    public OrganizationsPage clickOrganizationsMenu() {
        click(organizationsMenu);
        return Page.organizationsPage();
    }

    @Step
    public UsersPage clickUsersMenu() {
        click(usersMenu);
        return Page.usersPage();
    }

    @Step
    public ApiKeysPage clickApiKeysMenu() {
        click(apiKeysMenu);
        return Page.apiKeysPage();
    }

    @Step
    public GroupsPage clickGroupsMenu() {
        click(groupsMenu);
        return Page.groupsPage();
    }

    @Step
    public ConfigurationPage clickConfigurationMenu() {
        click(configurationMenu);
        return Page.configurationPage();
    }

    @Step
    public ExtensionPage clickExtensionMenu() {
        click(extensionMenu);
        return Page.extensionPage();
    }

    @Step
    public EditAccountPage clickEditAccountButton() {
        click(editAccountButton);
        return Page.editAccountPage();
    }

    @Step
    public LoginPage clickLogoutButton() {
        click(logoutButton);
        return Page.loginPage();
    }
}
