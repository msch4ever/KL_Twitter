package cz.los.KL_Twitter.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User implements PersistenceEntity {

    private Long userId;
    private String login;
    private String nickname;
    private LocalDateTime dateRegistered;
    private LocalDate dateOfBirth;
    private String about;
    private Set<User> followers;
    private Set<User> following;

    public User() {}

    public User(String login, String nickname) {
        this.login = login;
        this.nickname = nickname;
        this.dateRegistered = LocalDateTime.now();
        this.followers = new HashSet<>();
        this.following = new HashSet<>();
    }

    public User(Long userId, String login, String nickname, LocalDateTime dateRegistered, LocalDate dateOfBirth,
                String about, Set<User> followers, Set<User> following) {
        this.userId = userId;
        this.login = login;
        this.nickname = nickname;
        this.dateRegistered = dateRegistered;
        this.dateOfBirth = dateOfBirth;
        this.about = about;
        this.followers = followers;
        this.following = following;
    }

    public Long getId() {
        return userId;
    }

    @Override
    public void setId(Long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDateTime getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(LocalDateTime dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Set<User> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<User> followers) {
        this.followers = followers;
    }

    public Set<User> getFollowing() {
        return following;
    }

    public void setFollowing(Set<User> following) {
        this.following = following;
    }

    public void addToFollowing(User userToFollow) {
        this.following.add(userToFollow);
    }

    public void addToFollowers(User follower) {
        this.following.add(follower);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (!Objects.equals(userId, user.userId)) {
            return false;
        }
        return login.equals(user.login);
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + login.hashCode();
        return result;
    }
}
