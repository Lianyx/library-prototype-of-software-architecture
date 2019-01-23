package object.po;

import java.util.HashSet;
import java.util.Set;

public class Role {
    private String type;
    private int maximum;
    private int dayLimit;
    private Set<Category> _categories = new HashSet<>();

    public Role() {
    }

    public Role(String type, int maximum, int dayLimit, Set<Category> _categories) {
        this.type = type;
        this.maximum = maximum;
        this.dayLimit = dayLimit;
        this._categories = _categories;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaximum() {
        return maximum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public int getDayLimit() {
        return dayLimit;
    }

    public void setDayLimit(int dayLimit) {
        this.dayLimit = dayLimit;
    }

    public Set<Category> getCategories() {
        return _categories;
    }

    public void setCategories(Set<Category> categories) {
        this._categories = categories;
    }
}
