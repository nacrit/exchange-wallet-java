package com.nacrt.exchange.wallet.demo.model;

public record ApiResponse<T>(String code, T data, String message) {
    public static <T> ApiResponse<T> error(String code, String message) {
        return new ApiResponse<>(code, null, message);
    }
    public static <T> ApiResponse<T> success(T t) {
        return new ApiResponse<>("SUCCESS", t, "success");
    }
}
