package dev.cat.modular.monolith.globalexceptions;

import lombok.Getter;

public class ErrorResponse {

    @Getter
    String exception;
    int code;

    public ErrorResponse(String exception) {
        this.exception = exception;
    }

    public ErrorResponse(int code, String exception){
        this.code = code;
        this.exception = exception;
    }

}
