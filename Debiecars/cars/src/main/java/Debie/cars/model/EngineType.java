package Debie.cars.model;

public enum EngineType {
    GASOLINE("Gasoline"),
    DIESEL("Diesel"),
    ELECTRIC("Electric"),
    HYBRID("Hybrid"),
    PLUG_IN_HYBRID("Plug-in Hybrid");

    private final String displayName;

    EngineType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}