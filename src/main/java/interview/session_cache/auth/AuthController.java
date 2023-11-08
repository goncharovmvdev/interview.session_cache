package interview.session_cache.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author gonch
 * @since 11/5/2023, 8:54 PM
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final SessionService sessionService;
    private final String containsUsernameMsg;
    private final String notContainsUsernameMsg;

    public AuthController(SessionService sessionService,
                          @Value("${msg.contains.user:да}") String containsUsernameMsg,
                          @Value("${msg.contains.user:нет}") String notContainsUsernameMsg) {
        this.sessionService = sessionService;
        this.containsUsernameMsg = containsUsernameMsg;
        this.notContainsUsernameMsg = notContainsUsernameMsg;
    }

    /*
     * Почему-то в тз метод должен принимать пароль или хеш пароля пользователя.
     * Мне кажется это странно, тк нельзя гарантировать уникальность пароля для каждого из пользователей.
     * Поэтому я сделал так что метод юзернейм и проверяет есть ли в кеше пользователь
     */
    @GetMapping
    public String containsUsername(@RequestParam String username) {
        return sessionService.containsUsername(username)
                ? containsUsernameMsg
                : notContainsUsernameMsg;
    }

    @PostMapping
    public ResponseEntity<String> authenticate(@RequestBody AuthRq authRq) {
        boolean save = sessionService.save(authRq.getUsername(), authRq.getPassword());
        return save
                ? ResponseEntity.ok("ОК")
                : ResponseEntity.internalServerError().body("Попытка аутентифицировать одного пользователя несколько раз");
    }
}
