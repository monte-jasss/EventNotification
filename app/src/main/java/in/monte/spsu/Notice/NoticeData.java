package in.monte.spsu.Notice;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Monte on 1/27/2018.
 */

public class NoticeData {
    @SerializedName("subject")
    private String title;

    @SerializedName("id")
    private int num;

    @SerializedName("content")
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public int getNum() {
        return num;
    }
}
