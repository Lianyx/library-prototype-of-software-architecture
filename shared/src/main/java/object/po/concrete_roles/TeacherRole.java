package object.po.concrete_roles;

import object.po.Category;
import object.po.Role;

import java.util.HashSet;

public class TeacherRole extends Role {

    public TeacherRole() {
        this.type = "老师";
        this.maximum = 15;
        this.dayLimit = 40;
        this._categories = new HashSet<>();
        _categories.add(new Category("MATH", "数学"));
        _categories.add(new Category("CS", "计算机"));
        _categories.add(new Category("HS", "历史"));
        _categories.add(new Category("CL", "古典学"));
    }
}
