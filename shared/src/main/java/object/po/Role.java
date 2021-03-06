package object.po;

import object.po.concrete_roles.AdminRole;
import object.po.concrete_roles.GraduateRole;
import object.po.concrete_roles.TeacherRole;
import object.po.concrete_roles.UnderGraduateRole;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Role implements Serializable {
    protected String type;
    protected int maximum;
    protected int dayLimit;
    protected Set<Category> _categories = new HashSet<>();

    public Role() {
    }

    public Role(String type, int maximum, int dayLimit, Set<Category> _categories) {
        this.type = type;
        this.maximum = maximum;
        this.dayLimit = dayLimit;
        this._categories = _categories;
    }

    public static String[] getTypeList() {
        return new String[]{"本科生", "研究生", "老师", "管理员"};
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (maximum != role.maximum) return false;
        if (dayLimit != role.dayLimit) return false;
        if (type != null ? !type.equals(role.type) : role.type != null) return false;
        return _categories != null ? _categories.equals(role._categories) : role._categories == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + maximum;
        result = 31 * result + dayLimit;
        result = 31 * result + (_categories != null ? _categories.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "type='" + type + '\'' +
                ", maximum=" + maximum +
                ", dayLimit=" + dayLimit +
                ", _categories=" + _categories +
                '}';
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
