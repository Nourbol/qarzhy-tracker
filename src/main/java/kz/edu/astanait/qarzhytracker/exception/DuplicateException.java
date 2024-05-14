package kz.edu.astanait.qarzhytracker.exception;

public class DuplicateException extends RuntimeException {

    public DuplicateException(final String message) {
        super(message);
    }

    public static DuplicateException existingCategoryName() {
        return new DuplicateException("Another category already has the same name");
    }
}
