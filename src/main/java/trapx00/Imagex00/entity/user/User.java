package trapx00.Imagex00.entity.user;


import trapx00.Imagex00.entity.annotation.Column;
import trapx00.Imagex00.entity.Entity;
import trapx00.Imagex00.entity.annotation.Table;

@Table(name = "user")
public class User extends Entity {
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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
}
