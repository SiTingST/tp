package seedu.student.model;

import javafx.collections.ObservableList;
import seedu.student.model.appointment.Appointment;
import seedu.student.model.appointment.SameDateAppointmentList;
import seedu.student.model.appointment.UniqueAppointmentList;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class AppointmentBook implements ReadOnlyAppointmentBook {

    private final UniqueAppointmentList appointments;
    {
        appointments = new UniqueAppointmentList();

    }

    public AppointmentBook() {}

    /**
     * Creates an AppointmentBook using the Students in the {@code toBeCopied}
     */
    public AppointmentBook(ReadOnlyAppointmentBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Replaces the contents of the appointment list with {@code appointments}.
     * {@code students} must not contain overlapping appointments.
     */
    public void setAppointments(List<SameDateAppointmentList> appointments) {
        this.appointments.setAppointments(appointments);
    }

    /**
     * Resets the existing data of this {@code AppointmentBook} with {@code newData}.
     */
    public void resetData(ReadOnlyAppointmentBook newData) {
        requireNonNull(newData);

        setAppointments(newData.getAppointmentList());
    }

    //// appointment-level operations

    /**
     * Returns true if an appointment with the same identity as {@code appointment} exists in the address book.
     */
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
    }

    /**
     * Returns true if an appointment with overlapping time with {@code appointment} exists in the address book.
     */
    public boolean hasOverlappingAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.hasOverlappingAppointment(appointment);
    }

    /**
     * Adds an appointment to the address book.
     * The appointment must not already exist in the address book.
     */
    public void addAppointment(Appointment a) {
        appointments.add(a);
    }

    /**
     * Replaces the given appointment {@code target} in the list with {@code editedAppointment}.
     * {@code target} must exist in the address book.
     * The appointment identity of {@code editedAppointment} must not be the same as another existing
     * appointment in the address book.
     */
    public void setAppointment(Appointment target, Appointment editedAppointment) {
        requireNonNull(editedAppointment);

        appointments.setAppointment(target, editedAppointment);
    }

    @Override
    public ObservableList<SameDateAppointmentList> getAppointmentList() {
        return appointments.asUnmodifiableObservableList();
    }

}
