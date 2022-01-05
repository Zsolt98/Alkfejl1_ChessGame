package alkfejl1.dao.db;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import alkfejl1.dao.PartyDAO;
import alkfejl1.model.Party;

public class PartyDaoDB implements PartyDAO {

    private static String CONNECTION;

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
	public List<Party> list(String player1Name, String player2Name, String winnerName, String partyTime) {
    	var query = "SELECT * FROM CHESS WHERE NOT winner = 'w' AND NOT winner = 'b' " +
	    			"AND player1 = " + player1Name + " " + 
	    			"AND player2 = " + player2Name + " " +
	    			"AND winner = " + winnerName + " " +
	    			"AND partytime = " + partyTime + ";";
    	
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
