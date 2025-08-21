package Debie.cars.model;

public enum Transmission {
    AUTOMATIC("Automatic"),
    MANUAL("Manual"),
    CVT("CVT");

    private final String displayName;

    Transmission(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}