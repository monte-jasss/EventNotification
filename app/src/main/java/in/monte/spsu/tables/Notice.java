package in.monte.spsu.tables;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Monte on 2/26/2018.
 */
@Table(name = "notice")
public class Notice extends Model {
    @Column(name = "subject")
    private String title;

    @Column(name = "no", unique = true, onUniqueConflict = Column.ConflictAction.ABORT)
    private int num;

    @Column(name = "seen")
    private int check=1;

    @Column(name = "content")
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

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }
}

