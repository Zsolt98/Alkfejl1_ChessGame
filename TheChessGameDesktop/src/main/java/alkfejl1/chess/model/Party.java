package alkfejl1.chess.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Party {
	public static final DateTimeFormatter partyTimeFormat = DateTimeFormatter.ofPattern("yyyy. MM. dd. HH:mm");

    private int party_id;
    private String player1name;
    private String player2name;
    private String winner;
    private String boardStates;
    private String partyTime;

    public Party() {

    }

    public Party(String player1name, String player2name, String winner, String boardStates, LocalDateTime partyTime) {
        this.player1name = player1name;
        this.player2name = player2name;
        this.winner = winner;
        this.boardStates = boardStates;
        this.partyTime = partyTime.format(partyTimeFormat);
    }

    public String getPlayer1name() {
        return player1name;
    }

    public String getPlayer2name() {
        return player2name;
    }

    public String getWinner() {
        return winner;
    }

    public String getBoardStates() {
        return boardStates;
    }

    public String getPartyTime() {
        return partyTime;
    }

    public void setParty_id(int party_id) {
        this.party_id = party_id;
    }

    public void setPlayer1name(String player1name) {
        this.player1name = player1name;
    }

    public void setPlayer2name(String player2name) {
        this.player2name = player2name;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public void setBoardStates(String boardStates) {
        this.boardStates = boardStates;
    }

    public void setPartyTime(String partyTime) {
        this.partyTime = partyTime;
    }
}
