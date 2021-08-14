package by.mironenko.domen;

public enum Roles {
    USER(1),
    CUSTOMER(1),
    ADMIN(2),
    PROVIDER(2),
    SUPER_ADMIN(3);

    private final int level;

    Roles(final int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
