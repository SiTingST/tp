package seedu.student.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.student.model.Model.PREDICATE_SHOW_ALL_APPOINTMENTS;
import static seedu.student.model.Model.PREDICATE_SHOW_ALL_APPOINTMENT_LISTS;

import java.util.function.Predicate;

import seedu.student.model.Model;
import seedu.student.model.student.Faculty;
import seedu.student.model.student.SchoolResidence;
import seedu.student.model.student.Student;
import seedu.student.model.student.VaccinationStatus;

/**
 * Finds and lists all students in student book whose student entries field matches the argument keyword.
 * Keyword matching is case sensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    private static String vaccinationStatus = VaccinationStatus.getStringVaccinationStatus();
    private static String faculties = Faculty.getStringFaculties();
    private static String residences = SchoolResidence.getStringResidences();

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays the list of student of the requested entity: \n"
            + "Vaccination Status - Parameters: " + vaccinationStatus + " (case-insensitive)" + "\n"
            + "Faculties - Parameters: " + faculties + " (case-sensitive)" + "\n"
            + "Residences - Parameters: " + residences + " (case-sensitive)" + "\n"
            + "Please enter only one parameter." + " Examples: " + COMMAND_WORD + " vaccinated, "
            + COMMAND_WORD + " COM, " + COMMAND_WORD + " PGPH " + "\n";

    public static final String MESSAGE_STUDENTS_ARE_LISTED = "All %s students listed.";
    public static final String MESSAGE_NO_STUDENTS_ARE_LISTED = "No %s students exist in VAX@NUS's record.";
    private String input;

    private final Predicate<Student> predicate;

    public FilterCommand(Predicate<Student> predicate, String input) {
        this.predicate = predicate;
        this.input = input;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredStudentList(predicate);
        model.updateFilteredAppointmentList(PREDICATE_SHOW_ALL_APPOINTMENT_LISTS, PREDICATE_SHOW_ALL_APPOINTMENTS);
        if (model.getFilteredStudentList().size() == 0) {
            return new CommandResult(
                    String.format(MESSAGE_NO_STUDENTS_ARE_LISTED, input, model.getFilteredStudentList().size()));
        } else {
            return new CommandResult(
                    String.format(MESSAGE_STUDENTS_ARE_LISTED, input, model.getFilteredStudentList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}
