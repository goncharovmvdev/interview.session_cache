package interview.session_cache;

import interview.session_cache.auth.UserStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class InMemoryUserStoreTests {
    private final UserStore userStore = new UserStore.InMemory();

    @Test
    void save() {
        Assertions.assertThrows(NullPointerException.class, () -> userStore.save(null, null));
        Assertions.assertThrows(NullPointerException.class, () -> userStore.save("", null));
        Assertions.assertThrows(NullPointerException.class, () -> userStore.save(null, ""));

        Assertions.assertTrue(userStore.save("username", "password"));
        Assertions.assertFalse(userStore.save("username", "password"));
    }

    @Test
    void containsUsername() {
        String username = UUID.randomUUID().toString();
        String passwd = UUID.randomUUID().toString();

        userStore.save(username, passwd);
        Assertions.assertTrue(userStore.containsUsername(username));
    }

    @Test
    void containsUser() {
        String username = UUID.randomUUID().toString();
        String passwd = UUID.randomUUID().toString();

        userStore.save(username, passwd);
        Assertions.assertTrue(userStore.containsUser(username, passwd));
    }
}
