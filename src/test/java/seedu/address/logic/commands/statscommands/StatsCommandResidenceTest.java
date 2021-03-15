package seedu.address.logic.commands.statscommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DETAILS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_FACULTY_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MATRIC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.statscommands.StatsCommandResidence.MESSAGE_STATS_FAILURE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.SchoolResidence;
import seedu.address.testutil.PersonBuilder;

public class StatsCommandResidenceTest {
    private static final String VALID_VACCINATED_STATUS_BOB = "vaccinated";
    private static final Person DOES_NOT_LIVE_ON_CAMPUS_BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withFaculty(VALID_FACULTY_BOB).withVacStatus(VALID_VACCINATED_STATUS_BOB)
            .withMedDetails(VALID_DETAILS_BOB).withMatric(VALID_MATRIC_BOB)
            .withSchoolRes("DOES_NOT_LIVE_ON_CAMPUS").build();
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        StatsCommandResidence firstCommand = new StatsCommandResidence(new SchoolResidence("RVRC"));
        StatsCommandResidence secondCommand = new StatsCommandResidence(new SchoolResidence("RVRC"));
        assertTrue(firstCommand.equals(secondCommand));

        StatsCommandResidence thirdCommand = new StatsCommandResidence(new SchoolResidence("CAPT"));
        StatsCommandResidence fourthCommand = new StatsCommandResidence(new SchoolResidence("KRH"));
        assertFalse(thirdCommand.equals(fourthCommand));
    }

    // test no avaliable data
    @Test
    public void execute_typicalPersons_noAvailableData() {
        StatsCommandResidence statsCommandResidence = new StatsCommandResidence(new SchoolResidence("UTR"));
        assertCommandFailure(statsCommandResidence, expectedModel, MESSAGE_STATS_FAILURE);
    }

    // test success
    @Test
    public void execute_typicalPersons_success() {
        String expectedOutput = "Percentage RVRC vaccinated: 33.33%";
        StatsCommandResidence statsCommandResidence = new StatsCommandResidence(new SchoolResidence("RVRC"));
        assertCommandSuccess(statsCommandResidence, expectedModel, expectedOutput, expectedModel);
    }

    // test not on campus no data
    @Test
    public void execute_typicalPersonsNotOnCampus_noAvailableData() {
        SchoolResidence notOnCampus = new SchoolResidence("DOES_NOT_LIVE_ON_CAMPUS");
        StatsCommandResidence statsCommandResidence = new StatsCommandResidence(notOnCampus);
        assertCommandFailure(statsCommandResidence, expectedModel, MESSAGE_STATS_FAILURE);
    }

    // test not on campus success
    @Test
    public void execute_typicalPersonsNotOnCampus_success() {

        AddressBook ab = getTypicalAddressBook();
        ab.addPerson(DOES_NOT_LIVE_ON_CAMPUS_BOB);

        Model newModel = new ModelManager(ab, new UserPrefs());
        String expectedOutput = "Percentage DOES NOT LIVE ON CAMPUS vaccinated: 100.00%";
        SchoolResidence notOnCampus = new SchoolResidence("DOES_NOT_LIVE_ON_CAMPUS");
        StatsCommandResidence statsCommandResidence = new StatsCommandResidence(notOnCampus);
        assertCommandSuccess(statsCommandResidence, newModel, expectedOutput, newModel);
    }


}
