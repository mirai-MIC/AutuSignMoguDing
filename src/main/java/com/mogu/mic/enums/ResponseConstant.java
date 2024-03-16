package com.mogu.mic.enums;

//public class ResponseConstant {
//    public static final Integer SUCCESS = 200;
//}

import lombok.Getter;

@Getter
public enum ResponseConstant {
    SUCCESS(200);

    private final Integer value;

    ResponseConstant(Integer value) {
        this.value = value;
    }

}
