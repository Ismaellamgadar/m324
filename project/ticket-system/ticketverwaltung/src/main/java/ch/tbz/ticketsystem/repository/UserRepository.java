package ch.tbz.ticketsystem.repository;

public interface UserRepository {
    boolean doesUserExist(Long userId);
}