package interview.session_cache.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author gonch
 * @since 11/5/2023, 9:10 PM
 */
@Component
public class SessionServiceImpl implements SessionService {
    private static final Logger log = LoggerFactory.getLogger(SessionServiceImpl.class);
    private final Encoder encoder;
    private final UserStore userStore;

    public SessionServiceImpl(Encoder encoder, UserStore userStore) {
        this.encoder = encoder;
        this.userStore = userStore;
    }

    @Override
    public boolean save(CharSequence username, CharSequence password) {
        if (userStore.containsUsername(username)) {
            log.warn("Данный пользователь уже аутентифицирован");
            //не уудалось повторно аутентифицировать пользователя повторно
            return false;
        }
        userStore.save(username, encoder.encode(password));
        return true;
    }

    // по тз
    @Override
    public boolean containsUsername(CharSequence username) {
        return userStore.containsUsername(username);
    }

    //как это примерно реализовано в spring security. Там есть метод loadUserByUsername,
    // он принимает только юзернейм и под капотом проверят совпадение хешей паролей
    @Override
    public boolean containsUsernameAndMatchesPassword(CharSequence username, CharSequence password) {
        CharSequence encodedPasswd = encoder.encode(password);
        return userStore.containsUser(username, encodedPasswd);
    }
}
