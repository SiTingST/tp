package seedu.student.model;

import javafx.collections.ObservableList;
import seedu.student.model.appointment.SameDateAppointmentList;

public interface ReadOnlyAppointmentBook {
    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<SameDateAppointmentList> getAppointmentList();
}
