package alkfejl1.chess.dao.db;

import java.io.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import alkfejl1.chess.dao.PartyDAO;
import alkfejl1.chess.model.Party;

public class PartyDaoDB implements PartyDAO {

    private static String CONNECTION;
    private static final String CHESS_INSERT = "INSERT INTO CHESS (player1, player2, winner, boardstates, partytime) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_ALL_UNFINISHED_PARTY = "SELECT * FROM CHESS WHERE winner = 'w' OR winner = 'b';";

    public PartyDaoDB(){
        try {
            var props = new Properties();
            props.load(PartyDaoDB.class.getResourceAsStream("/application.properties"));

            CONNECTION = props.getProperty("db.url");

            Class.forName("org.sqlite.JDBC");
        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public boolean addParty(Party party) {
        try(var connection = DriverManager.getConnection(CONNECTION);
            var statement = connection.prepareStatement(CHESS_INSERT)){

            statement.setString(1, party.getPlayer1name());
            statement.setString(2, party.getPlayer2name());
            statement.setString(3, party.getWinner());
            statement.setString(4, party.getBoardStates());
            statement.setString(5, party.getPartyTime());

            return statement.executeUpdate() == 1;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Party> listFinished(String field, String value) {
        return list("SELECT * FROM CHESS WHERE NOT winner = 'w' AND NOT winner = 'b' AND " + field + " = " + value + ";");
    }

    @Override
    public List<Party> listUnfinished() {
    	return list(SELECT_ALL_UNFINISHED_PARTY);
    }

	private List<Party> list(String query) {
		var result = new ArrayList<Party>();

        try(var connection = DriverManager.getConnection(CONNECTION);
            var statement = connection.createStatement();
            var rs = statement.executeQuery(query)){

            while (rs.next()){
                Party party = new Party();

                party.setParty_id(rs.getInt("id"));
                party.setPlayer1name(rs.getString("player1"));
                party.setPlayer2name(rs.getString("player2"));
                party.setWinner(rs.getString("winner"));
                party.setBoardStates(rs.getString("boardstates"));
                party.setPartyTime(rs.getString("partytime"));

                result.add(party);
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return result;
	}
}
