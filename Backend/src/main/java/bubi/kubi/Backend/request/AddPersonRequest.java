package bubi.kubi.Backend.request;

public class AddPersonRequest {

    private String username;
    private String password;

    public AddPersonRequest() {
    }

    public AddPersonRequest(String username, String password) {
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
