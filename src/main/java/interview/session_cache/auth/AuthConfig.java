package interview.session_cache.auth;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gonch
 * @since 11/5/2023, 9:20 PM
 */
@Configuration
public class AuthConfig {
    @Bean
    //если желать из этого стартер то явно надо давать возможность настраивать кодирование паролей
    @ConditionalOnMissingBean
    public Encoder encoder() {
        return new Encoder.Base64();
    }

    @Bean
    @ConditionalOnMissingBean
    //@ConditionalOnProperty
    public UserStore userStore() {
        return new UserStore.InMemory();
    }
}
