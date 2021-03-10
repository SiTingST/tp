package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FACULTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MATRICULATION_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICAL_DETAILS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHOOL_RESIDENCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_VACCINATION_STATUS;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_MATRICULATION_NUMBER + "MATRICULATION NUMBER "
            + PREFIX_FACULTY + "FACULTY "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_VACCINATION_STATUS + "VACCINATION STATUS "
            + PREFIX_MEDICAL_DETAILS + "MEDICAL DETAILS "
            + PREFIX_SCHOOL_RESIDENCE + "SCHOOL RESIDENCE \n"
            + PREFIX_NAME + "John Doe "
            + PREFIX_MATRICULATION_NUMBER + "A01234567X "
            + PREFIX_FACULTY + "SCI"
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_VACCINATION_STATUS + "Vaccinated "
            + PREFIX_MEDICAL_DETAILS + "peanut allergy "
            + PREFIX_SCHOOL_RESIDENCE + "PGPH \n";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
