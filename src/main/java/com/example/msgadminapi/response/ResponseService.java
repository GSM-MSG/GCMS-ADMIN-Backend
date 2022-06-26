package com.example.msgadminapi.response;

import com.example.msgadminapi.response.result.CommonResultResponse;
import com.example.msgadminapi.response.result.SingleResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {
    @Getter
    @AllArgsConstructor
    public enum CommonResponse {
        SUCCESS(true, "성공하였습니다.", 200);
        private boolean success;
        private String msg;
        private int status;
    }

    public <T>SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>(getCommonResultResponse(), data);
        return result;
    }

    public CommonResultResponse getSuccessResult() {
        return getCommonResultResponse();
    }

    private CommonResultResponse getCommonResultResponse() {
        return new CommonResultResponse(CommonResponse.SUCCESS.isSuccess(), CommonResponse.SUCCESS.getMsg(), CommonResponse.SUCCESS.getStatus());
    }
}
