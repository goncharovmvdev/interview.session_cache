package interview.session_cache.auth;


import java.nio.charset.StandardCharsets;
import java.util.Objects;

//аналог PaswwordEncoder из spring security
public interface Encoder {
    CharSequence encode(CharSequence in);

    //проверяет что пароли совпадают по хешу
    default boolean matchesWithFoundPasswd(CharSequence inPasswd, CharSequence foundPasswd) {
        return Objects.equals(foundPasswd, encode(inPasswd));
    }

    final class NoOp implements Encoder {

        @Override
        public CharSequence encode(CharSequence in) {
            return in;
        }
    }

    final class Base64 implements Encoder {

        @Override
        public CharSequence encode(CharSequence in) {
            return java.util.Base64
                    .getEncoder()
                    .encodeToString(
                            String
                                    .valueOf(in)
                                    .getBytes(StandardCharsets.UTF_8));
        }
    }
}
