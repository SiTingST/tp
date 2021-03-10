package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Faculty;
import seedu.address.model.person.MatriculationNumber;
import seedu.address.model.person.MedicalDetails;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.SchoolResidence;
import seedu.address.model.person.VaccinationStatus;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Person person) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(person.getName());
        descriptor.setMatriculationNumber(person.getMatriculationNumber());
        descriptor.setFaculty(person.getFaculty());
        descriptor.setPhone(person.getPhone());
        descriptor.setEmail(person.getEmail());
        descriptor.setAddress(person.getAddress());
        descriptor.setVaccinationStatus(person.getVaccinationStatus());
        descriptor.setMedicalDetails(person.getMedicalDetails());
        descriptor.setSchoolResidence(person.getSchoolResidence());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code MatricuationNumber} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withMatric(String matric) {
        descriptor.setMatriculationNumber(new MatriculationNumber(matric));
        return this;
    }

    /**
     * Sets the {@code Faculty} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withFaculty(String faculty) {
        descriptor.setFaculty(new Faculty(faculty));
        return this;
    }
    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Sets the {@code vaccinationStatus} of the {@code vaccinationStatus} that we are building.
     */
    public EditPersonDescriptorBuilder withVacStatus(String vaccinationStatus) {
        descriptor.setVaccinationStatus(new VaccinationStatus(vaccinationStatus));
        return this;
    }

    /**
     * Sets the {@code medicalDetails} of the {@code medicalDetails} that we are building.
     */
    public EditPersonDescriptorBuilder withMedDetails(String medicalDetails) {
        descriptor.setMedicalDetails(new MedicalDetails(medicalDetails));
        return this;
    }

    /**
     * Sets the {@code schoolResidence} of the {@code schoolResidence} that we are building.
     */
    public EditPersonDescriptorBuilder withSchoolRes(String schoolResidence) {
        descriptor.setSchoolResidence(new SchoolResidence(schoolResidence));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
