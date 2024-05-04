package simtran.alfiotest.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationModel {
    private String orgName;
    private String orgEmail;
    private String orgDescription;
    private String slug;
    private String externalId;
}
