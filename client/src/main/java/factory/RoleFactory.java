package factory;

import object.po.Role;
import object.po.concrete_roles.AdminRole;
import object.po.concrete_roles.GraduateRole;
import object.po.concrete_roles.TeacherRole;
import object.po.concrete_roles.UnderGraduateRole;

public class RoleFactory {
    public static Role getRole(String type) {
        switch (type) {
            case "本科生":
                return new UnderGraduateRole();
            case "研究生":
                return new GraduateRole();
            case "老师":
                return new TeacherRole();
            case "管理员":
                return new AdminRole();
            default:
                return new Role();
        }
    }
}
