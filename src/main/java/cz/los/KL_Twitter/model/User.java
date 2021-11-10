package cz.los.KL_Twitter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(exclude = {"followers", "following", "about", "nickname"})
@AllArgsConstructor
public class User implements PersistenceEntity {

    private Long userId;
    private String nickname;
    private final String login;
    private final LocalDateTime dateRegistered;
    private LocalDate dateOfBirth;
    private String about;
    private List<User> followers;
    private List<User> following;

    public User(String login) {
        this(login, login, null, null);
    }

    public User(String login, LocalDate dateOfBirth, String about) {
        this(login, login, dateOfBirth, about);
    }

    public User(String login, String nickname, LocalDate dateOfBirth, String about) {
        this.login = login;
        this.nickname = nickname;
        this.dateRegistered = LocalDateTime.now();
        this.dateOfBirth = dateOfBirth;
        this.about = about;
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public User(User other) {
        this.userId = other.userId;
        this.nickname = other.nickname;
        this.login = other.login;
        this.dateRegistered = other.dateRegistered;
        this.dateOfBirth = other.dateOfBirth;
        this.about = other.about;
        this.followers = other.followers;
        this.following = other.following;
    }

    public void addToFollowing(User userToFollow) {
        this.following.add(userToFollow);
    }

    @Override
    public void setId(Long id) {
        this.userId = id;
    }
}
