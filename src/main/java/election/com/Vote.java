package election.com;

import java.util.Objects;

public class Vote {
    private int id;
    private Candidate candidate;
    private Voter voter;
    private String voteType;

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", candidate=" + candidate +
                ", voter=" + voter +
                ", voteType='" + voteType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Vote vote = (Vote) o;
        return id == vote.id && Objects.equals(candidate, vote.candidate) && Objects.equals(voter, vote.voter) && Objects.equals(voteType, vote.voteType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, candidate, voter, voteType);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public void setVoteType(String voteType) {
        this.voteType = voteType;
    }

    public Vote(int id, Candidate candidate, Voter voter, String voteType) {
        this.id = id;
        this.candidate = candidate;
        this.voter = voter;
        this.voteType = voteType;
    }

    public int getId() { return id; }
    public Candidate getCandidate() { return candidate; }
    public Voter getVoter() { return voter; }
    public String getVoteType() { return voteType; }

}