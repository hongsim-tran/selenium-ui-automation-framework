package simtran.alfiotest.datafactory;

import net.datafaker.Faker;
import simtran.alfiotest.models.OrganizationModel;

public class OrganizationDataFactory {
    static Faker faker = new Faker();

    public static OrganizationModel createValidOrganizationData() {
        OrganizationModel org = new OrganizationModel();
        org.setOrgName(faker.company().name());
        org.setOrgEmail(faker.internet().emailAddress());
        org.setOrgDescription(faker.lorem().paragraph(5));
        org.setSlug(faker.internet().slug());
        org.setExternalId(faker.idNumber().valid());
        return org;
    }
}
