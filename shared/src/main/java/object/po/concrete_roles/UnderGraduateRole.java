package object.po.concrete_roles;

import object.po.Category;
import object.po.Role;

import java.util.HashSet;

public class UnderGraduateRole extends Role {

    public UnderGraduateRole() {
        this.type = "本科生";
        this.maximum = 2;
        this.dayLimit = 10;
        this._categories = new HashSet<>();
        _categories.add(new Category("MATH", "数学"));
        _categories.add(new Category("CS", "计算机"));
    }
}
