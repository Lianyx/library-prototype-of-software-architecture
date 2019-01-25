package object.po;

import object.enun.Permission;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
    private String username, password;
    private Role role;
    private double debt;
    private Set<Permission> _permissions = new HashSet<>();

    public User() {
        role = Role.getRole("");
    }

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void addPermission(Permission permission) {
        _permissions.add(permission);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (Double.compare(user.debt, debt) != 0) return false;
        if (username != null ? !username.equals(user.username) : user.username != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (role != null ? !role.equals(user.role) : user.role != null) return false;
        return _permissions != null ? _permissions.equals(user._permissions) : user._permissions == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        temp = Double.doubleToLongBits(debt);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (_permissions != null ? _permissions.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", debt=" + debt +
                ", _permissions=" + _permissions +
                '}';
    }

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
