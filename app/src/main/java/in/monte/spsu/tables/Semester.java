package in.monte.spsu.tables;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "semester")
public class Semester extends Model {
    @Column(name = "semester", unique = true, onUniqueConflict = Column.ConflictAction.ABORT)
    private String sem;

    public String getSem() {
        return sem;
    }

    public void setSem(String sem) {
        this.sem = sem;
    }
}
