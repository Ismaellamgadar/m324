package ch.tbz.ticketverwaltung.repository;

public interface UserRepository {
    boolean doesUserExist(Long userId);
}
