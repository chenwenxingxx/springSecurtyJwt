package com.chenwenxing.springsecurityjwt.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public  class JwtUtil {
    private static String secret="chenwenxing";

    //生成token
    public String generateToken(String username){

        Date now=new Date();
        Date expire=new Date(now.getTime()+72000000);   //单位毫秒
        return Jwts.builder()
                .setHeaderParam("typ","jwt")
                .setSubject(username)
                .claim("username",username)
                .setIssuedAt(now)
                .setExpiration(expire)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();

    }

    //解析token
    public  Claims parseToken(String token){
        Claims body = Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        System.out.println(body);
        String subject = body.getSubject();
        System.out.println(subject);
        String username = body.get("username").toString();
        System.out.println(username);
        return body;

    }

    //判断token是否过期
    public  boolean isTokenExpire(Claims claims){
        return claims.getExpiration().before(new Date());
    }

    public static void main(String[] args) {
//        String token = generateToken(secret);
//        System.out.println(token);
//
//        parseToken(secret,token);
    }


}
