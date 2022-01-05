package alkfejl1.chess.controller;

import java.util.List;

import alkfejl1.chess.dao.PartyDAO;
import alkfejl1.chess.dao.db.PartyDaoDB;
import alkfejl1.chess.model.Party;

public class PartyController {
    private PartyDAO dao = new PartyDaoDB();

    public PartyController() { }

    public boolean add(Party party) {
        return dao.addParty(party);
    }

    public List<Party> listFinished(String field, String value) {
        return dao.listFinished(field, value);
    }
    
    public List<Party> listUnfinished() {
    	return dao.listUnfinished();
    }
}
