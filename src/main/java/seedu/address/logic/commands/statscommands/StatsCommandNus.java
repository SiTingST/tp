package seedu.address.logic.commands.statscommands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class StatsCommandNus extends StatsCommand {
    public static final String MESSAGE_STATS_SUCCESS = "Percentage NUS vaccinated: %.2f%%";

    @Override
    public CommandResult execute(Model model) throws CommandException { // NUS
        requireNonNull(model);
        List<Person> studentList = model.getAddressBook().getPersonList();
        int totalStudents = studentList.size();
        int counter = 0;

        try {
            for (Person p : studentList) {
                if (p.isVaccinated()) {
                    counter++;
                }
            }
            float stats = (float) counter / totalStudents;
            return new CommandResult(String.format(MESSAGE_STATS_SUCCESS, stats * 100));
        } catch (Exception e) {
            throw new CommandException(MESSAGE_STATS_FAILURE);
        }
    }
}
