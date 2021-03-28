package seedu.student.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.student.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.student.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.student.logic.commands.CommandTestUtil.showStudentWithMatricNum;
import static seedu.student.testutil.TypicalMatricNumbers.MATRIC_NUMBER_FIRST_STUDENT;
import static seedu.student.testutil.TypicalMatricNumbers.MATRIC_NUMBER_FOURTH_STUDENT;
import static seedu.student.testutil.TypicalMatricNumbers.MATRIC_NUMBER_SECOND_STUDENT;
import static seedu.student.testutil.TypicalStudents.getTypicalStudentBook;

import org.junit.jupiter.api.Test;

import seedu.student.commons.core.Messages;
import seedu.student.model.Model;
import seedu.student.model.ModelManager;
import seedu.student.model.UserPrefs;
import seedu.student.model.student.MatriculationNumber;
import seedu.student.model.student.Student;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteStudentCommand}.
 */
public class DeleteStudentCommandTest {

    private Model model = new ModelManager(getTypicalStudentBook(), new UserPrefs());

    @Test
    public void execute_validMatricNumUnfilteredList_success() {
        MatriculationNumber matricNumberToDelete = new MatriculationNumber(MATRIC_NUMBER_FOURTH_STUDENT);
        Student studentToDelete = model.getStudent(matricNumberToDelete);
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(matricNumberToDelete);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete);

        ModelManager expectedModel = new ModelManager(model.getStudentBook(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);

        assertCommandSuccess(deleteStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidMatricNumUnfilteredList_throwsCommandException() {
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(new MatriculationNumber(
                MATRIC_NUMBER_FIRST_STUDENT));

        assertCommandFailure(deleteStudentCommand, model, Messages.MESSAGE_NONEXISTENT_MATRIC_NUM);
    }

    @Test
    public void execute_validMatricNumFilteredList_success() {
        MatriculationNumber matricNumberToDelete = new MatriculationNumber(MATRIC_NUMBER_FOURTH_STUDENT);
        showStudentWithMatricNum(model, matricNumberToDelete);

        Student studentToDelete = model.getStudent(matricNumberToDelete);
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(matricNumberToDelete);

        String expectedMessage = String.format(DeleteStudentCommand.MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete);

        Model expectedModel = new ModelManager(model.getStudentBook(), new UserPrefs());
        expectedModel.deleteStudent(studentToDelete);
        showNoStudent(expectedModel);

        assertCommandSuccess(deleteStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteStudentCommand deleteFirstCommand = new DeleteStudentCommand(new MatriculationNumber(
                MATRIC_NUMBER_FIRST_STUDENT));
        DeleteStudentCommand deleteSecondCommand = new DeleteStudentCommand(new MatriculationNumber(
                MATRIC_NUMBER_SECOND_STUDENT));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteStudentCommand deleteFirstCommandCopy = new DeleteStudentCommand(new MatriculationNumber(
                MATRIC_NUMBER_FIRST_STUDENT));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoStudent(Model model) {
        model.updateFilteredStudentList(p -> false);

        assertTrue(model.getFilteredStudentList().isEmpty());
    }
}
