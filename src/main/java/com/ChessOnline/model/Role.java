package com.ChessOnline.model;

import com.ChessOnline.constant.SqlNamespace;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = SqlNamespace.ROLE_TABLE_NAME)
public class Role implements SqlNamespace {

    @Id
    @Column(name = CLN_ID)
    private String pid;

    @Column(name = CLN_ROLE_DESCRIPTION)
    private String description;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users = new ArrayList<>();

    public Role() {
    }

    public Role(final String name, final String description) {
        pid = name;
        this.description = description;
    }

    @Transient
    public String getName() {
        return getPid();
    }

    public String getDescription() {
        return description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(final List<User> users) {
        this.users = users;
    }

    @Transient
    public String getAuthority() {
        return getName();
    }

    public void setName(final String name) {
        setPid(name);
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return pid.equals(role.pid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pid);
    }

    /**
     * Сравнение ролей по PID (по наименованию роли).
     *
     * @param obj роль, с которой производится сравнение
     * @return целое число, меньшее/равное/большее 0
     */
    public int compareTo(final Object obj) {
        return getPid().compareTo(((Role) obj).getPid());
    }
}
