package com.example.msgadminapi.response.result;

import lombok.Getter;

@Getter
public class SingleResult<T> extends CommonResultResponse{
    private T data;

    public SingleResult(CommonResultResponse resultResponse, T data) {
        super(resultResponse.isSuccess(), resultResponse.getMsg(), resultResponse.getStatus());
        this.data = data;
    }
}
