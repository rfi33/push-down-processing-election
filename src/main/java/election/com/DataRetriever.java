package election.com;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
