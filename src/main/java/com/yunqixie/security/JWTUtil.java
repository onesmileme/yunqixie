package com.yunqixie.security;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import  com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Date;

public class JWTUtil {

    private  static  final  long EXPIRE_TIME = 12*30*24*60*60*1000;

    private static Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    public static  boolean verify(String token , String uid , String openid){

        try {
            //Algorithm
            Algorithm algorithm = Algorithm.HMAC256(openid);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("uid",uid).build();

            DecodedJWT jwt = verifier.verify(token);
            return true;

        }catch (UnsupportedEncodingException une){
            logger.error(une.getLocalizedMessage(),openid);
            return false;
        }
    }

    public static String getUid(String token){

        try{
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("uid").asString();
        }catch (JWTDecodeException exception){
            return null;
        }
    }

    public static  String sign(String uid , String openId){
        try{
            Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(openId);
            return JWT.create().withClaim("uid",uid)
                    .withExpiresAt(date)
                    .sign(algorithm);
        }catch (UnsupportedEncodingException e){
            logger.error(e.getLocalizedMessage(),openId);
            return null;
        }
    }

}
