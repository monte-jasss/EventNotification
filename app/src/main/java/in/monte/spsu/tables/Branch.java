package in.monte.spsu.tables;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "branch")
public class Branch extends Model {
    @Column(name = "branch", unique = true, onUniqueConflict = Column.ConflictAction.ABORT)
    private String branch;

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
