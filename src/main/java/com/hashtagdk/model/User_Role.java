package com.hashtagdk.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by dawid on 7/25/17.
 */
@Entity
public class User_Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user_role")
    private Long id;

    @ManyToOne
    private User users;

    @ManyToOne
    private Role role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUsers() {
        return users;
    }

    public void setUsers(User users) {
        this.users = users;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
