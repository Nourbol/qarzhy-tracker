package kz.edu.astanait.qarzhytracker.domain;

public record UserRegistrationRequest(String name,
                                      String email,
                                      String password) {
}
