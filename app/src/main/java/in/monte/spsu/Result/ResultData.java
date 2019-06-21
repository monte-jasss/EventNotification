package in.monte.spsu.Result;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Monte on 1/27/2018.
 */

public class ResultData {
    @SerializedName("code")
    String code;

    @SerializedName("subject")
    String subject;

    @SerializedName("grade")
    String grade;

    public String getCode() {
        return code;
    }

    public String getSubject() {
        return subject;
    }

    public String getGrade() {
        return grade;
    }
}
