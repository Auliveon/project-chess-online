package com.ChessOnline.model;

import com.ChessOnline.constant.SqlNamespace;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = SqlNamespace.USER_TABLE_NAME)
public class User implements SqlNamespace {

    @Id
    @Column(name = CLN_USER_LOGIN, nullable = false, length = 100, unique = true)
    private String login;

    @Column(name = CLN_USER_PASSWORD, nullable = false)
    private String password;

    @Column(name = CLN_USER_FIRST_NAME, nullable = false, length = 100)
    private String firstName;

    @Column(name = CLN_USER_LAST_NAME, nullable = false, length = 100)
    private String lastName;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinTable(name = USER_ROLES_TABLE_NAME)
    private List<Role> roles = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    @Column(name = CLN_USER_STATUS, nullable = false, columnDefinition = "varchar(20) default 'ACTIVE'")
    private Status status;

    @Column(name = "email")
    private String email;

    public User() {
    }

    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.roles.add(new Role("USER", "Пользователь"));
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Transient
    public String getRolesStr() {
        return roles.stream().map(Role::getName).collect(Collectors.joining(","));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return login.equals(user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

}
