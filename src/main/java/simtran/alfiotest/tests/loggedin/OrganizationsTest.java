package simtran.alfiotest.tests.loggedin;

import io.qameta.allure.Allure;
import org.testng.annotations.Test;
import simtran.alfiotest.datafactory.OrganizationDataFactory;
import simtran.alfiotest.models.OrganizationModel;
import simtran.alfiotest.pages.Page;
import simtran.alfiotest.pages.loggedin.organizations.OrganizationsPage;

import java.util.HashMap;

import static simtran.core.config.ConfigManager.config;

public class OrganizationsTest extends PreconditionBase {

    @Test(description = "Create new organization - Valid data in all fields")
    public void createOrganizationWithValidData() {
        deleteAllOrganizationsFromDB();

        OrganizationModel organization = OrganizationDataFactory.createValidOrganizationData();
        Page.navigation().clickOrganizationsMenu();

        createAnOrganization(organization);
        wait(1);
        HashMap<OrganizationsPage.OrgTableHeader, String> orgData = Page.organizationsPage().getOrgRow(0);

        Allure.step("Verify if the created organization information is correctly on the table");
        softAssert.assertEquals(orgData.get(OrganizationsPage.OrgTableHeader.NAME), organization.getOrgName());
        softAssert.assertEquals(orgData.get(OrganizationsPage.OrgTableHeader.EMAIL), organization.getOrgEmail());
        softAssert.assertEquals(orgData.get(OrganizationsPage.OrgTableHeader.DESCRIPTION), organization.getOrgDescription());

        softAssert.assertAll();
    }

    @Test(description = "Edit an organization - Valid data in all fields")
    public void editOrganizationWithValidData() {
        deleteAllOrganizationsFromDB();

        // Create a new organization and click edit button to edit it
        OrganizationModel organization = OrganizationDataFactory.createValidOrganizationData();
        Page.navigation().clickOrganizationsMenu();
        createAnOrganization(organization);

        Page.organizationsPage().clickEditButtonOfRow(0);

        Allure.step("Verify if organization information shown correctly in all fields of Edit form");
        softAssert.assertEquals(Page.organizationsPage().getOrgNameInputValue(), organization.getOrgName());
        softAssert.assertEquals(Page.organizationsPage().getOrgEmailInputValue(), organization.getOrgEmail());
        softAssert.assertEquals(Page.organizationsPage().getOrgDescriptionInputValue(), organization.getOrgDescription());
        softAssert.assertEquals(Page.organizationsPage().getSlugInputValue(), organization.getSlug());
        softAssert.assertEquals(Page.organizationsPage().getExternalIdInputValue(), organization.getExternalId());

        OrganizationModel editOrganization = OrganizationDataFactory.createValidOrganizationData();
        Page.organizationsPage()
                .inputOrgName(editOrganization.getOrgName())
                .inputOrgEmail(editOrganization.getOrgEmail())
                .inputOrgDescription(editOrganization.getOrgDescription())
                .inputSlug(editOrganization.getSlug())
                .inputExternalId(editOrganization.getExternalId())
                .clickSaveButton();

        wait(config(target).timeout());
        HashMap<OrganizationsPage.OrgTableHeader, String> orgData = Page.organizationsPage().getOrgRow(0);

        Allure.step("Verify if the organization is updated correctly");
        softAssert.assertEquals(orgData.get(OrganizationsPage.OrgTableHeader.NAME), editOrganization.getOrgName());
        softAssert.assertEquals(orgData.get(OrganizationsPage.OrgTableHeader.EMAIL), editOrganization.getOrgEmail());
        softAssert.assertEquals(orgData.get(OrganizationsPage.OrgTableHeader.DESCRIPTION), editOrganization.getOrgDescription());

        softAssert.assertAll();
    }
}
