package object.enun;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Role {
    TEACHER(15, 40, Category.values()),
    GRAD(10, 20, Category.CS, Category.HISTORY),
    UNDERGRAD(5, 10, Category.values()),
    ADMIN(0, 0),
    GUEST(0, 0);

    private int maximum;
    private int period;
    private Set<Category> permittedCategories;

    Role(int maximum, int period, Category... cs) {
        this.permittedCategories = new HashSet<>(Arrays.asList(cs));
        this.maximum = maximum;
        this.period = period;
    }

    public boolean accessible(Category c) {
        return permittedCategories.contains(c);
    }

    public int getMaximum() {
        return maximum;
    }

    public int getPeriod() {
        return period;
    }
}
