import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import alkfejl1.dao.PartyDAO;
import alkfejl1.dao.db.PartyDaoDB;
import alkfejl1.model.Party;

@WebServlet("/chess")
public class StartServlet extends HttpServlet {

	private final PartyDAO dao = new PartyDaoDB();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var session = req.getSession();

		var player1Name = (String) session.getAttribute("player1Name");
		var player2Name = (String) session.getAttribute("player2Name");
		var winner = (String) session.getAttribute("winner");
		var time = (String) session.getAttribute("time");
		var date = (String) session.getAttribute("date");
		var searchedWinner = winner == null ? "winner" :
				winner.equals("1") ? "player1" :
						winner.equals("2") ? "player2" : "'X'";

		var result = dao.list(player1Name.isEmpty() ? "player1" : "'" + player1Name + "'",
				player2Name.isEmpty() ? "player2" : "'" + player2Name + "'",
				searchedWinner,
				time.isEmpty() || date.isEmpty() ? "partytime" : ("'" + date.replace("-", ". ") + ". " + time + "'"));

		req.setAttribute("tableData", createTableData(result));
	}

	private static String createTableData(List<Party> parties) {
		var result = "";

		for(var i = 0; i < parties.size(); ++i) {
			var party = parties.get(i);

			result += "<tr>" +
					"<th scope=\"row\">" + (i + 1) + "</th>" +
					"<td>" + party.getPlayer1name() + "</td>" +
					"<td>" + party.getPlayer2name() + "</td>" +
					"<td>" + party.getWinner() + "</td>" +
					"<td>" + party.getPartyTime() + "</td>" +
					"</tr>";
		}
		return result;
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var session = req.getSession();

		session.setAttribute("player1Name", req.getParameter("player1Name"));
		session.setAttribute("player2Name", req.getParameter("player2Name"));
		session.setAttribute("winner", req.getParameter("winner"));
		session.setAttribute("date", req.getParameter("date"));
		session.setAttribute("time", req.getParameter("time"));

		resp.sendRedirect("list-parties.jsp");
	}
}