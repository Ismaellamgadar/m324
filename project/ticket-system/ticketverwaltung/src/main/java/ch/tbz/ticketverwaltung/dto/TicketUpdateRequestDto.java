package ch.tbz.ticketverwaltung.dto;

public class TicketUpdateRequestDto {
    private String dateString;
    private String type;

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
