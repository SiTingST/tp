package seedu.address.logic.commands.statscommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.statscommands.StatsCommandFaculty.MESSAGE_STATS_FAILURE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Faculty;

public class StatsCommandFacultyTest {
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        StatsCommandFaculty firstCommand = new StatsCommandFaculty(new Faculty("SCI"));
        StatsCommandFaculty secondCommand = new StatsCommandFaculty(new Faculty("SCI"));
        assertTrue(firstCommand.equals(secondCommand));

        StatsCommandFaculty thirdCommand = new StatsCommandFaculty(new Faculty("COM"));
        StatsCommandFaculty fourthCommand = new StatsCommandFaculty(new Faculty("USP"));
        assertFalse(thirdCommand.equals(fourthCommand));
    }

    // test no avaliable data
    @Test
    public void execute_typicalPersons_noAvailableData() {
        StatsCommandFaculty statsCommandFaculty = new StatsCommandFaculty(new Faculty("YNC"));
        assertCommandFailure(statsCommandFaculty, expectedModel, MESSAGE_STATS_FAILURE);
    }

    // test success
    @Test
    public void execute_typicalPersons_success() {
        String expectedOutput = "Percentage USP vaccinated: 100.00%";
        StatsCommandFaculty statsCommandFaculty = new StatsCommandFaculty(new Faculty("USP"));
        assertCommandSuccess(statsCommandFaculty, expectedModel, expectedOutput, expectedModel);
    }
}