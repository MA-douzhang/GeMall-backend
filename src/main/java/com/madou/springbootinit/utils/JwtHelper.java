package com.madou.springbootinit.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class JwtHelper {
    // 秘钥
    static final String SECRET = "X-Gemall-Token";
    // 签名是有谁生成
    // 签名的主题


    public String createToken(Integer userId) {
        try {
            String algorithm = SecureUtil.md5(SECRET);
            Map<String, Object> map = new HashMap<String, Object>();
            Date nowDate = new Date();
            // 过期时间：100年
            Date expireDate = getAfterDate(nowDate, 100, 0, 0, 0, 0, 0);
            map.put("alg", "HS256");
            map.put("typ", "JWT");
            Map<String, Object> payload = new HashMap<String, Object>();
            //签发时间
            payload.put(JWTPayload.ISSUED_AT, nowDate);
            //过期时间
            payload.put(JWTPayload.EXPIRES_AT, expireDate);
            //生效时间
            payload.put(JWTPayload.NOT_BEFORE, nowDate);
            //载荷
            payload.put("userId", userId);
            String token = JWT.create()
                    // 设置头部信息 Header
                    .addHeaders(map)
                    .addPayloads(payload)
                    .setKey(algorithm.getBytes())
                    .sign();
            return token;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * 校验token获取userId
     * @param token
     * @return
     */
    public Integer verifyTokenAndGetUserId(String token) {
        try {
            if(!verify(token)) return null;
            JWT jwt = JWT.of(token);
            return Integer.valueOf(jwt.getPayload("userId").toString());
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    /**
     * token 校验
     * @param token token
     * @return 是否通过校验
     */
    public static boolean verify (String token) {
        String algorithm = SecureUtil.md5(SECRET);
        if (StringUtils.isBlank(token)) return false;
        return JWT.of(token).setKey(algorithm.getBytes()).verify();
    }
    public Date getAfterDate(Date date, int year, int month, int day, int hour, int minute, int second) {
        if (date == null) {
            date = new Date();
        }

        Calendar cal = new GregorianCalendar();

        cal.setTime(date);
        if (year != 0) {
            cal.add(Calendar.YEAR, year);
        }
        if (month != 0) {
            cal.add(Calendar.MONTH, month);
        }
        if (day != 0) {
            cal.add(Calendar.DATE, day);
        }
        if (hour != 0) {
            cal.add(Calendar.HOUR_OF_DAY, hour);
        }
        if (minute != 0) {
            cal.add(Calendar.MINUTE, minute);
        }
        if (second != 0) {
            cal.add(Calendar.SECOND, second);
        }
        return cal.getTime();
    }

}
