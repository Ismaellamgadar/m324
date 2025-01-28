package ch.tbz.ticketverwaltung.dto;

public class UserResponseDto {
    private boolean exists;

    public boolean isExists() {
        return exists;
    }

    public void setExists(boolean exists) {
        this.exists = exists;
    }
}
