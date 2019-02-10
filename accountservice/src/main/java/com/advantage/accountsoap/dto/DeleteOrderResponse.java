package com.advantage.accountsoap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeleteOrderResponse {


    private boolean successfulOrderHeaderDelete;


    private boolean successfulOrderLineDelete;


    private boolean isSuccess;

    public DeleteOrderResponse() {

    }

    public DeleteOrderResponse(boolean successfulOrderHeaderDelete, boolean successfulOrderLineDelete,
                               boolean isSuccess){

        this.successfulOrderHeaderDelete = successfulOrderHeaderDelete;

        this.successfulOrderLineDelete = successfulOrderLineDelete;

        this.isSuccess = isSuccess;
    }


    public void setSuccessfulOrderHeaderDelete(boolean successfulOrderHeaderDelete) {
        this.successfulOrderHeaderDelete = successfulOrderHeaderDelete;
    }

    public void setSuccessfulOrderLineDelete(boolean successfulOrderLineDelete) {
        this.successfulOrderLineDelete = successfulOrderLineDelete;
    }

    public void setSuccess(boolean success) {
        this.isSuccess = success;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean isSuccessfulOrderHeaderDelete() {
        return successfulOrderHeaderDelete;
    }

    public boolean isSuccessfulOrderLineDelete() {
        return successfulOrderLineDelete;
    }

    @Override
    public String toString() {
        return "DeleteOrderResponse{" +
                "successfulOrderHeaderDelete=" + successfulOrderHeaderDelete +
                ", successfulOrderLineDelete=" + successfulOrderLineDelete +
                ", isSuccess=" + isSuccess +
                '}';
    }
}
