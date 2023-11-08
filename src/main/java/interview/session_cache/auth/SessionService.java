package interview.session_cache.auth;

//подобие SecurityContextHolder + UserDetailsService
public interface SessionService {
    boolean save(CharSequence username, CharSequence password);

    //метод по тз
    boolean containsUsername(CharSequence username);

    // как это примерно сделано в spring security
    boolean containsUsernameAndMatchesPassword(CharSequence username, CharSequence password);
}
