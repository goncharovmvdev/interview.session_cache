package interview.session_cache.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author gonch
 * @since 11/5/2023, 9:26 PM
 */
public interface UserStore {
    boolean save(CharSequence username, CharSequence passwd);

    boolean containsUsername(CharSequence username);

    boolean containsUser(CharSequence username, CharSequence password);

    class InMemory implements UserStore {
        private static final Logger log = LoggerFactory.getLogger(InMemory.class);
        //concurrent чтоб не было коллизий проблем при одновременной аутентификации 2х+ пользователей
        private final Map<CharSequence, CharSequence> usernames2passwds;

        public InMemory(Map<CharSequence, CharSequence> initialUsernames2passds) {
            this.usernames2passwds = initialUsernames2passds;
        }

        public InMemory() {
            this(new ConcurrentHashMap<>());
        }

        @Override
        public boolean save(CharSequence username, CharSequence passwd) {
            if (username == null) throw new NullPointerException("юзернейм не может быть null");
            if (passwd == null) throw new NullPointerException("пароль не может быть null");
            if (usernames2passwds.containsKey(username)) {
                log.warn("Пользователь с юзернеймом {} уже сохранен", username);
                return false;
            }
            usernames2passwds.put(username, passwd);
            log.debug("Пользователь с юзернеймом {} успешно сохранен", username);
            return true;
        }

        @Override
        public boolean containsUsername(CharSequence username) {
            return usernames2passwds.containsKey(username);
        }

        @Override
        public boolean containsUser(CharSequence username, CharSequence password) {
            CharSequence foundPasswd = usernames2passwds.get(username);
            return Objects.equals(foundPasswd, password);
        }
    }
}
