import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wengzhiyu
 * @since 2021/12/29 15:45
 */
public class Test01 {

    public static void main(String[] args) {


        LocalDateTime localDateTime = LocalDateTime.now();
        String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


        System.out.println(format);


    }
}
