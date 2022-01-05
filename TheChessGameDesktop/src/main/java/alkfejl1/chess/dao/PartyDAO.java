package alkfejl1.chess.dao;

import java.util.List;

import alkfejl1.chess.model.Party;

public interface PartyDAO {
    boolean addParty(Party party);
    List<Party> listFinished(String field, String value);
    List<Party> listUnfinished();
}
