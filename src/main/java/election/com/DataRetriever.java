package election.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {

    private final DBConnection dbConnection;
    public DataRetriever(){
        this.dbConnection = new DBConnection();
    }

    public long countAllVote(){
        String sql = """
                SELECT COUNT(voter.id) as total_de_vote from voter;
                """;
        Connection connection = dbConnection.getDBConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return resultSet.getLong("total_de_vote");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    return 0;
    }

    public List<VoteTypeCount> countVotesByType(){
        String sql = """
                SELECT vote_type::TEXT,count (vote.id) as nb_de_vote from vote group by vote_type;
                """;
        List<VoteTypeCount> result = new ArrayList<>();
        Connection connection = dbConnection.getDBConnection();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                result.add(new VoteTypeCount(
                        resultSet.getString("vote_type"),
                        resultSet.getLong("nb_de_vote")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
    public List<CandidateVoteCount> countValidVotesByCandidate(){
        String sql = """
                SELECT candidate.name as candidate_name,count(vote.id) FILTER (WHERE vote.vote_type ='VALID') as valid_vote from vote inner join candidate
                ON vote.candidate_id = candidate.id
                GROUP BY candidate.name;
                """;
        List<CandidateVoteCount> result = new ArrayList<>();
        Connection connection = dbConnection.getDBConnection();

        try {
            PreparedStatement  preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    result.add(new CandidateVoteCount(
                            resultSet.getString("candidate_name"),
                            resultSet.getLong("valid_vote")
                    ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public VoteSummary computeVoteSummary(){
        String sql = """
                SELECT count (vote.id) FILTER (WHERE vote.vote_type = 'VALID') as valid_count,
                        count (vote.id) FILTER (WHERE vote.vote_type = 'BLANK') as blank_count,
                        count (vote.id) FILTER (WHERE vote.vote_type = 'NULL') as null_count
                from vote;
                """;
        Connection connection = dbConnection.getDBConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                return new VoteSummary(
                        resultSet.getLong("valid_count"),
                        resultSet.getLong("blank_count"),
                        resultSet.getLong("null_count")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new VoteSummary(0, 0, 0);
    }

}
