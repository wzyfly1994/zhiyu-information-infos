import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Test01 {

    public static void main(String[] args) {
        // 你的原始数据
        String originalData = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRST";
        byte[] originalBytes = originalData.getBytes();

        // 使用Base64编码
        String base64Encoded = Base64.getEncoder().encodeToString(originalBytes);

        System.out.println("原始数据: " + originalData);
        System.out.println("Base64编码后: " + base64Encoded);
    }
}
