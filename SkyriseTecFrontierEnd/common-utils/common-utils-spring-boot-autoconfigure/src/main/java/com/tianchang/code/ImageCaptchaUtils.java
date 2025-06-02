package com.tianchang.code;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;

//图片验证码，
public class ImageCaptchaUtils {

    //生成图片验证码
    public static String generateImageCaptcha(int width, int height, int charLength, int lineCount) {
        // 生成验证码
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(width, height, charLength, lineCount);
        // 将验证码文本保存到session中
        String captchaText = lineCaptcha.getCode();
        // 将验证码图片输出到响应流
        return captchaText;
    }

}
