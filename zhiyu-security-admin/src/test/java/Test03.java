import com.zhiyu.core.utils.RedisUtils;
import com.zhiyu.security.SecurityApplication;
import com.zhiyu.security.config.properties.SecurityProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = SecurityApplication.class)
@RunWith(value = SpringRunner.class)
public class Test03 {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private SecurityProperties properties;

    @Test
    public void func() {

//        Object obj = redisUtils.get("user-login-cache:jason001");
//        JwtUserDto dto = (JwtUserDto) obj;

//        Object obj = redisUtils.get("user-login-cache:jason001");
//        JwtUserDto dto = JSON.parseObject(JSON.toJSONString(obj), JwtUserDto.class);
//        System.out.println(dto.toString());

//        OnlineUserDto dto = new OnlineUserDto();
//        dto.setIp("127.0.0.1");
//        dto.setAddress("深圳");
//        redisUtils.set("test1", dto);
//        Object o = redisUtils.get("test1");
//        if (o instanceof OnlineUserDto) {
//            OnlineUserDto vo = (OnlineUserDto) o;
//            System.out.println(vo.toString());
//        }

        String loginKey = properties.getOnlineKey() +"";
        System.out.println("loginKey: -> "+loginKey);
        List<String> scan = redisUtils.scan(loginKey+"*");

        System.out.println("scan: -> "+scan);

    }

}
