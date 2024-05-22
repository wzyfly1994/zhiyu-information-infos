import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Test02 {

    public static void main(String[] args) {
        String rawPassword = "123456";

//        PasswordEncoder passwordEncoder =
//                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密密码
        String encodedPassword = passwordEncoder.encode(rawPassword);
        // 输出加密后的密码
        System.out.println("Encoded password: " + encodedPassword);

        boolean matches = passwordEncoder.matches(rawPassword, "$2a$10$lK/NLjAwcoSYYTo47FO97ulorRvAV5y2ga3jet9lD2sHKH.4jG0UO");

        if (matches) {
            System.out.println("Password matches!");
        } else {
            System.out.println("Password does not match!");
        }
    }

}
