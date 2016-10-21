package com.mantambakberas.iamantaras.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mantambakberas.iamantaras.model.MyInfo;

import java.util.List;

/**
 * Created by winnerawan on 10/19/16.
 */

public class MyInfoResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("users")
    @Expose
    private List<MyInfo> myInfo;

    /**
     *
     * @return
     * The error
     */
    public Boolean getError() {
        return error;
    }

    /**
     *
     * @param error
     * The error
     */
    public void setError(Boolean error) {
        this.error = error;
    }

    public List<MyInfo> getMyInfo() {
        return myInfo;
    }

    public void setMyInfo(List<MyInfo> myInfo) {
        this.myInfo = myInfo;
    }
}
