package seedu.student.logic.parser;

import static seedu.student.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.List;

import seedu.student.logic.commands.FilterCommand;
import seedu.student.logic.parser.exceptions.ParseException;
import seedu.student.model.student.Faculty;
import seedu.student.model.student.FacultyContainsKeywords;
import seedu.student.model.student.SchoolResidence;
import seedu.student.model.student.SchoolResidenceContainsKeywords;
import seedu.student.model.student.VaccinationStatus;
import seedu.student.model.student.VaccinationStatusContainsKeywords;

/**
 * Parses input arguments and creates a new FilterCommand object
 */
public class FilterCommandParser implements Parser<FilterCommand> {

    //VACCINATED_STATUS contains VACCINATED and NOT_VACCINATED
    private static final List<String> VACCINATED_STATUS = VaccinationStatus.getVaccinationStatusAbbreviation();
    private static final List<String> FACULTY = Faculty.getFacultyAbbreviation();
    private static final List<String> SCHOOL_RESIDENCE = SchoolResidence.getResidenceAbbreviation();
    private static final String UNVACCINATED_STATUS = "NOT_VACCINATED";

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterCommand parse(String args) throws ParseException {

        String condition = args.trim();
        boolean isValidVaccinationStatus = true;

        //Ensure that "not_vaccinated" or "NOT_VACCINATED" is an invalid input
        if (condition.contains("_") || condition.equals(condition.toUpperCase())) {
            isValidVaccinationStatus = false;
        }

        //Change input string to match "NOT_VACCINATED" in VACCINATED_STATUS
        if (condition.equals("not vaccinated")) {
            condition = UNVACCINATED_STATUS;
        }

        if (VACCINATED_STATUS.contains((condition.toUpperCase())) && isValidVaccinationStatus) {
            return new FilterCommand(new VaccinationStatusContainsKeywords(condition));
        } else if (FACULTY.contains(condition)) {
            return new FilterCommand(new FacultyContainsKeywords(condition));
        } else if (SCHOOL_RESIDENCE.contains(condition)) {
            return new FilterCommand(new SchoolResidenceContainsKeywords(condition));
        } else {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }
    }
}
