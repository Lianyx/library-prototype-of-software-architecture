package object.po.concrete_roles;

import object.po.Category;
import object.po.Role;

import java.util.HashSet;

public class GraduateRole extends Role {

    public GraduateRole() {
        this.type = "研究生";
        this.maximum = 5;
        this.dayLimit = 20;
        this._categories = new HashSet<>();
        _categories.add(new Category("MATH", "数学"));
        _categories.add(new Category("CS", "计算机"));
        _categories.add(new Category("HS", "历史"));
    }
}
