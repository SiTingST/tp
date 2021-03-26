package seedu.student.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.student.commons.exceptions.DataConversionException;
import seedu.student.model.ReadOnlyAppointmentBook;
import seedu.student.model.ReadOnlyStudentBook;
import seedu.student.model.ReadOnlyUserPrefs;
import seedu.student.model.UserPrefs;
import seedu.student.storage.appointment.AppointmentBookStorage;

/**
 * API of the Storage component
 */
public interface Storage extends StudentBookStorage, AppointmentBookStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getStudentBookFilePath();

    @Override
    Optional<ReadOnlyStudentBook> readStudentBook() throws DataConversionException, IOException;

    @Override
    void saveStudentBook(ReadOnlyStudentBook studentBook) throws IOException;

    @Override
    Path getAppointmentBookFilePath();

    @Override
    Optional<ReadOnlyAppointmentBook> readAppointmentBook() throws DataConversionException, IOException;

    @Override
    void saveAppointmentBook(ReadOnlyAppointmentBook appointmentBook) throws IOException;

}
