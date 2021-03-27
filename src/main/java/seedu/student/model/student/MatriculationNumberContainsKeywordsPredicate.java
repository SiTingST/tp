package seedu.student.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Matriculation Number} matches any of the keywords given.
 */
public class MatriculationNumberContainsKeywordsPredicate implements Predicate<Student> {
    private final String keyword;

    public MatriculationNumberContainsKeywordsPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Student student) {
        return keyword.equals((student.getMatriculationNumber().toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MatriculationNumberContainsKeywordsPredicate // instanceof handles nulls
                && keyword.equals(((MatriculationNumberContainsKeywordsPredicate) other).keyword)); // state check
    }

}
