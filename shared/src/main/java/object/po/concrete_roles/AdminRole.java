package object.po.concrete_roles;

import object.po.Category;
import object.po.Role;

import java.util.HashSet;

public class AdminRole extends Role {

    public AdminRole() {
        this.type = "管理员";
        this.maximum = 0;
        this.dayLimit = 0;
        this._categories = new HashSet<>();
    }
}
