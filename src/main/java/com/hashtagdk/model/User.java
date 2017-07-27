package com.hashtagdk.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by dawid on 7/13/17.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user")
    private long id;

    @NotNull
    @Size(min=3, max=20)
    private String login;

    @NotNull
    @Size(min=4, max=32)
    @Transient
    private String password;
    @Transient
    private String password2;

    private String passwordHash;
    private String email;


    private Boolean enabled;
    /* !USER ROLE! */
    @OneToMany
    private List<User_Role> user_roles;

    public List<User_Role> getUser_roles() {
        return user_roles;
    }

    public void setUser_roles(List<User_Role> user_roles) {
        this.user_roles = user_roles;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @OneToOne
    private UserStat userStat;

    @OneToMany(mappedBy = "user")
    private List<Topic> topics;

    @OneToMany(mappedBy = "user")
    private List<UserTopicState> userTopicStates;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
    @OneToMany(mappedBy = "user")
    private List<PostApprobation> postApprobations;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public UserStat getUserStat() {
        return userStat;
    }

    public void setUserStat(UserStat userStat) {
        this.userStat = userStat;
    }

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }



    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopics(List<Topic> topics) {
        this.topics = topics;
    }

    public List<UserTopicState> getUserTopicStates() {
        return userTopicStates;
    }

    public void setUserTopicStates(List<UserTopicState> userTopicStates) {
        this.userTopicStates = userTopicStates;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<PostApprobation> getPostApprobations() {
        return postApprobations;
    }

    public void setPostApprobations(List<PostApprobation> postApprobations) {
        this.postApprobations = postApprobations;
    }

}
