package election.com;

import java.util.List;

public class Main {
    public static void main(String[] args) {
    DataRetriever dataRetriever = new DataRetriever();

    long totalDeVote = dataRetriever.countAllVote();
        System.out.println("total_de_vote: "+totalDeVote);

        System.out.println();
        List<VoteTypeCount> byType = dataRetriever.countVotesByType();
        System.out.println(byType);

        System.out.println();
        List<CandidateVoteCount> byCandidate = dataRetriever.countValidVotesByCandidate();
        System.out.println(byCandidate);
    }
}
