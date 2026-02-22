package election.com;

import java.util.Objects;

public class VoteSummary {
    private long validCount;
    private long blankCount;
    private long nullCount;

    public void setValidCount(long validCount) {
        this.validCount = validCount;
    }

    public void setBlankCount(long blankCount) {
        this.blankCount = blankCount;
    }

    public void setNullCount(long nullCount) {
        this.nullCount = nullCount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        VoteSummary that = (VoteSummary) o;
        return validCount == that.validCount && blankCount == that.blankCount && nullCount == that.nullCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(validCount, blankCount, nullCount);
    }

    public VoteSummary(long validCount, long blankCount, long nullCount) {
        this.validCount = validCount;
        this.blankCount = blankCount;
        this.nullCount = nullCount;
    }

    public long getValidCount() { return validCount; }
    public long getBlankCount() { return blankCount; }
    public long getNullCount() { return nullCount; }

    @Override
    public String toString() {
        return "VoteSummary(validCount=" + validCount +
                ", blankCount=" + blankCount +
                ", nullCount=" + nullCount + ")";
    }
}