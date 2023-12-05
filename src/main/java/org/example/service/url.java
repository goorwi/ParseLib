package org.example.service;

public enum url {
    habr,
    theatreDram,
    theatreSpas;

    public static url getUrlByInt(int choice) {
        return switch (choice) {
            case 1 -> habr;
            case 2 -> theatreDram;
            case 3 -> theatreSpas;
            default -> null;
        };
    }
}
