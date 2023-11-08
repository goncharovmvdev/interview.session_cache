package interview.session_cache.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author gonch
 * @since 11/5/2023, 8:55 PM
 */
public class AuthRq {
    @JsonProperty(required = true)
    private final String username;
    @JsonProperty(required = true)
    private final String password;

    @JsonCreator
    public AuthRq(@JsonProperty("username") String username,
                  @JsonProperty("password") String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
