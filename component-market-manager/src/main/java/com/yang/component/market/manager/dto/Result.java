package com.yang.component.market.manager.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Result<T> implements Serializable {

    private boolean success;
    private String message;
    private T data;

    public static <S> Result<S> ok(S data) {
        return new Result<>(true, "success", data);
    }

    public static Result<String> error(String message) {
        return new Result<>(false, message, null);
    }

    public static Result<String> error(Map<String, Object> messageMap) {

        String message = messageMap.entrySet().stream()
                .map(entry -> String.format("字段[%s]不符合要求:%s", entry.getKey(), entry.getValue())).collect(
                        Collectors.joining(";"));

        return new Result<>(false, message, null);
    }
}
