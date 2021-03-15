package seedu.address.logic.commands.statscommands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.statscommands.StatsCommandNus.MESSAGE_STATS_FAILURE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class StatsCommandNusTest {
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        StatsCommandNus firstCommand = new StatsCommandNus();
        StatsCommandNus secondCommand = new StatsCommandNus();
        assertTrue(firstCommand.equals(secondCommand));
    }

    @Test
    public void execute_typicalPersons_successful() {
        String expectedOutput = "Percentage NUS vaccinated: 42.86%";
        StatsCommandNus statsCommandNus = new StatsCommandNus();
        assertCommandSuccess(statsCommandNus, expectedModel, expectedOutput, expectedModel);
    }

    @Test
    public void execute_typicalPersons_noAvailableData() {
        StatsCommandNus statsCommandNus = new StatsCommandNus();
        Model emptyModel = new ModelManager(new AddressBook(), new UserPrefs());
        assertCommandFailure(statsCommandNus, emptyModel, MESSAGE_STATS_FAILURE);
    }

}
