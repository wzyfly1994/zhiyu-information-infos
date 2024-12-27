import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class Test01 {

    public static void main(String[] args) {
        // 你的原始数据
        String originalData = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRST";
        byte[] originalBytes = originalData.getBytes();

        // 使用Base64编码
        String base64Encoded = Base64.getEncoder().encodeToString(originalBytes);

        System.out.println("原始数据: " + originalData);
        System.out.println("Base64编码后: " + base64Encoded);

        Cookie cookie = new Cookie("myCookie", "cookieValue");

        List<String> list1 = new ArrayList<>();
        list1.add("A");


        List<String> list2 = new ArrayList<>(list1);
        list2.add("B");
        System.out.println(list1);

    }
}
