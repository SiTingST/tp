package seedu.student.model;

import javafx.collections.ObservableList;
import seedu.student.model.student.Student;

/**
 * Unmodifiable view of a studentbook
 */
public interface ReadOnlyStudentBook {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

}
