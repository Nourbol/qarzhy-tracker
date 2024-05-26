package kz.edu.astanait.qarzhytracker.exception;

import kz.edu.astanait.qarzhytracker.domain.ErrorType;

public class AlreadyExistsException extends ServiceException {

    public AlreadyExistsException(final ErrorType errorType, final String message) {
        super(errorType, message);
    }

    public static AlreadyExistsException existingCategoryName() {
        return new AlreadyExistsException(ErrorType.CATEGORY_NAME_TAKEN, "Another category already has the same name");
    }
}
