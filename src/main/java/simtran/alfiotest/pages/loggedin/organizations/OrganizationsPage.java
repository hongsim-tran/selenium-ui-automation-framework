package simtran.alfiotest.pages.loggedin.organizations;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import simtran.core.base.BasePage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrganizationsPage extends BasePage {
    //**************** Page's locators ****************
    private final By pageTitle = By.xpath("//div[@class='container']/h1");
    private final By addNewButton = By.cssSelector(".btn.btn-success[ui-sref='organizations.new']");
    private final By orgTableHeader = By.cssSelector("table[class*='table'] thead");
    private final By orgTableRows = By.cssSelector("tbody tr");
    private final By namesTable = By.cssSelector("tbody tr td:nth-child(1)");
    private final By descriptionsTable = By.cssSelector("tbody tr td:nth-child(2)");
    private final By emailsTable = By.cssSelector("tbody tr td:nth-child(3)");
    private final By editButtons = By.cssSelector("tbody tr td:nth-child(4)");

    private final By nameInput = By.id("org-name");
    private final By emailInput = By.id("org-email");
    private final By descriptionInput = By.id("org-description");
    private final By slugInput = By.id("slug");
    private final By externalIdInput = By.id("externalId");
    private final By cancelButton = By.xpath("//button[contains(text(),'Cancel')]");
    private final By saveButton = By.xpath("//button[contains(text(),'Save')]");


    //**************** Page's attributes ****************
    public String getPageTitle() {
        return getText(pageTitle);
    }

    public List<HashMap<OrgTableHeader, String>> getOrgTable() {
        List<HashMap<OrgTableHeader, String>> orgTable = new ArrayList<>();
        List<WebElement> orgNames = findElements(namesTable);
        List<WebElement> orgDescriptions = findElements(descriptionsTable);
        List<WebElement> orgEmails = findElements(emailsTable);

        for (int i = 0; i < orgNames.size(); i++) {
            HashMap<OrgTableHeader, String> map = new HashMap<>();
            map.put(OrgTableHeader.NAME, getText(orgNames.get(i)));
            map.put(OrgTableHeader.DESCRIPTION, getText(orgDescriptions.get(i)));
            map.put(OrgTableHeader.EMAIL, getText(orgEmails.get(i)));
            orgTable.add(map);
        }
        return orgTable;
    }

    public HashMap<OrgTableHeader, String> getOrgRow(int index) {
        List<WebElement> orgNames = findElements(namesTable);
        List<WebElement> orgDescriptions = findElements(descriptionsTable);
        List<WebElement> orgEmails = findElements(emailsTable);

        HashMap<OrgTableHeader, String> map = new HashMap<>();
        map.put(OrgTableHeader.NAME, getText(orgNames.get(index)));
        map.put(OrgTableHeader.DESCRIPTION, getText(orgDescriptions.get(index)));
        map.put(OrgTableHeader.EMAIL, getText(orgEmails.get(index)));

        return map;
    }

    public String getOrgNameInputValue() {
        return getValue(nameInput);
    }

    public String getOrgEmailInputValue() {
        return getValue(emailInput);
    }

    public String getOrgDescriptionInputValue() {
        return getValue(descriptionInput);
    }

    public String getSlugInputValue() {
        return getValue(slugInput);
    }

    public String getExternalIdInputValue() {
        return getValue(externalIdInput);
    }

    //**************** Page's actions ****************
    @Step
    public OrganizationsPage clickAddNewButton() {
        click(addNewButton);
        return this;
    }

    @Step
    public OrganizationsPage clickEditButtonOfRow(int index) {
        click(findElements(editButtons).get(index));
        return this;
    }

    @Step
    public OrganizationsPage inputOrgName(String name) {
        enterText(nameInput, name);
        return this;
    }

    @Step
    public OrganizationsPage inputOrgEmail(String email) {
        enterText(emailInput, email);
        return this;
    }

    @Step
    public OrganizationsPage inputOrgDescription(String description) {
        enterText(descriptionInput, description);
        return this;
    }

    @Step
    public OrganizationsPage inputSlug(String slug) {
        enterText(slugInput, slug);
        return this;
    }

    @Step
    public OrganizationsPage inputExternalId(String externalId) {
        enterText(externalIdInput, externalId);
        return this;
    }

    @Step
    public OrganizationsPage clickCancelButton() {
        click(cancelButton);
        return this;
    }

    @Step
    public OrganizationsPage clickSaveButton() {
        click(saveButton);
        return this;
    }

    public enum OrgTableHeader {
        NAME,
        DESCRIPTION,
        EMAIL
    }
}
