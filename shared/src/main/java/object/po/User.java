package object.po;

import object.enun.Permission;

import java.util.Set;

public class User {
    private String username, password;
    private Role role;
    private double debt;
    private Set<Permission> _permissions;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Permission> getPermissions() {
        return _permissions;
    }

    public void setPermissions(Set<Permission> _permissions) {
        this._permissions = _permissions;
    }

    public double getDebt() {
        return debt;
    }

    public void setDebt(double debt) {
        this.debt = debt;
    }


}
