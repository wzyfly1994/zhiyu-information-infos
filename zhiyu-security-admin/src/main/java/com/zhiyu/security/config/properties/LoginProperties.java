/*
 * Copyright 2019-2020 the original author or authors.
 *
 * Licensed under the Apache License, Version loginCode.length.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-loginCode.length.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhiyu.security.config.properties;

import com.wf.captcha.*;
import com.wf.captcha.base.Captcha;
import com.zhiyu.core.exception.BusinessException;
import com.zhiyu.security.entity.dto.LoginCodeDto;
import com.zhiyu.security.enums.LoginCodeEnum;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.util.Objects;

/**
 * 配置文件读取
 */
@Data
public class LoginProperties {

    /**
     * 账号单用户 登录
     */
    @Getter
    private boolean singleLogin = false;

    private LoginCodeDto loginCodeDto;

    public static final String cacheKey = "user-login-cache:";

    /**
     * 获取验证码生产类
     *
     * @return /
     */
    public Captcha getCaptcha() {
        if (Objects.isNull(loginCodeDto)) {
            loginCodeDto = new LoginCodeDto();
            if (Objects.isNull(loginCodeDto.getCodeType())) {
                loginCodeDto.setCodeType(LoginCodeEnum.ARITHMETIC);
            }
        }
        return switchCaptcha(loginCodeDto);
    }

    /**
     * 依据配置信息生产验证码
     *
     * @param loginCodeDto 验证码配置信息
     * @return /
     */
    private Captcha switchCaptcha(LoginCodeDto loginCodeDto) {
        Captcha captcha;
        switch (loginCodeDto.getCodeType()) {
            case ARITHMETIC:
                // 算术类型 https://gitee.com/whvse/EasyCaptcha
                captcha = new FixedArithmeticCaptcha(loginCodeDto.getWidth(), loginCodeDto.getHeight());
                // 几位数运算，默认是两位
                captcha.setLen(loginCodeDto.getLength());
                break;
            case CHINESE:
                captcha = new ChineseCaptcha(loginCodeDto.getWidth(), loginCodeDto.getHeight());
                captcha.setLen(loginCodeDto.getLength());
                break;
            case CHINESE_GIF:
                captcha = new ChineseGifCaptcha(loginCodeDto.getWidth(), loginCodeDto.getHeight());
                captcha.setLen(loginCodeDto.getLength());
                break;
            case GIF:
                captcha = new GifCaptcha(loginCodeDto.getWidth(), loginCodeDto.getHeight());
                captcha.setLen(loginCodeDto.getLength());
                break;
            case SPEC:
                captcha = new SpecCaptcha(loginCodeDto.getWidth(), loginCodeDto.getHeight());
                captcha.setLen(loginCodeDto.getLength());
                break;
            default:
                throw new BusinessException("验证码配置信息错误！正确配置查看 LoginCodeEnum ");
        }
        if (StringUtils.isNotBlank(loginCodeDto.getFontName())) {
            captcha.setFont(new Font(loginCodeDto.getFontName(), Font.PLAIN, loginCodeDto.getFontSize()));
        }
        return captcha;
    }

    static class FixedArithmeticCaptcha extends ArithmeticCaptcha {
        public FixedArithmeticCaptcha(int width, int height) {
            super(width, height);
        }

        @Override
        protected char[] alphas() {
            // 生成随机数字和运算符
            int n1 = num(1, 10), n2 = num(1, 10);
            int opt = num(3);

            // 计算结果
            int res = new int[]{n1 + n2, n1 - n2, n1 * n2}[opt];
            // 转换为字符运算符
            char optChar = "+-x".charAt(opt);

            this.setArithmeticString(String.format("%s%c%s=?", n1, optChar, n2));
            this.chars = String.valueOf(res);

            return chars.toCharArray();
        }
    }
}
