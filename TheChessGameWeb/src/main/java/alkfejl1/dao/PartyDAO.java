package alkfejl1.dao;

import java.util.List;

import alkfejl1.model.Party;

public interface PartyDAO {
    List<Party> list(String player1Name, String player2Name, String winnerName, String partyTime);
}
