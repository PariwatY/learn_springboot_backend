package com.example.allianz.exception.token;

import com.example.allianz.exception.BaseException;

public class TokenException extends BaseException {

    public TokenException(String code) {
        super("token." + code);
    }


    public static TokenException tokenInvalid() {
        return new TokenException("token invalid");
    }

}