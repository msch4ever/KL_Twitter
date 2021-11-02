package cz.los.KL_Twitter.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class User implements PersistenceEntity {

    private Long userId;
    private String nickname;
    private String login;
    private final LocalDate dateRegistered;
    private LocalDate dateOfBirth;
    private String about;
    private List<User> followers;
    private List<User> following;

    public User(String nickname, LocalDate dateOfBirth, String about) {
        this.nickname = nickname;
        this.dateRegistered = LocalDate.now();
        this.dateOfBirth = dateOfBirth;
        this.about = about;
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public User(String nickname) {
        this.nickname = nickname;
        this.login = nickname;
        this.dateRegistered = LocalDate.now();
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public User(String login, String nickname) {
        this.nickname = nickname;
        this.login = login;
        this.dateRegistered = LocalDate.now();
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();


    public void addToFollowing(User userToFollow) {
        this.following.add(userToFollow);
    }

    public User(User other) {
        this.userId = other.userId;
        this.nickname = other.nickname;
        this.dateRegistered = other.dateRegistered;
        this.dateOfBirth = other.dateOfBirth;
        this.about = other.about;
        this.followers = other.followers;
        this.following = other.following;
    }

    @Override
    public void setId(Long id) {
        this.userId = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (nickname != null ? !nickname.equals(user.nickname) : user.nickname != null) return false;
        if (!dateRegistered.equals(user.dateRegistered)) return false;
        return dateOfBirth != null ? dateOfBirth.equals(user.dateOfBirth) : user.dateOfBirth == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (nickname != null ? nickname.hashCode() : 0);
        result = 31 * result + dateRegistered.hashCode();
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        return result;
    }
}
