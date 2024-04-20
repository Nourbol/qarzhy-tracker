package kz.edu.astanait.qarzhytracker.exception;

public class FailedObjectConstructionException extends RuntimeException {

    public FailedObjectConstructionException(final Exception exception) {
        super(exception);
    }
}
