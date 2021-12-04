package org.transsonic.trustgame.scores;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.transsonic.trustgame.data.trustgame.Tables;
import org.transsonic.trustgame.data.trustgame.tables.records.CarrierRecord;
import org.transsonic.trustgame.data.trustgame.tables.records.GameRecord;
import org.transsonic.trustgame.data.trustgame.tables.records.GameplayRecord;
import org.transsonic.trustgame.data.trustgame.tables.records.GameuserRecord;
import org.transsonic.trustgame.data.trustgame.tables.records.MissionRecord;
import org.transsonic.trustgame.data.trustgame.tables.records.OrderRecord;
import org.transsonic.trustgame.data.trustgame.tables.records.OrdercarrierRecord;
import org.transsonic.trustgame.data.trustgame.tables.records.RoundRecord;
import org.transsonic.trustgame.data.trustgame.tables.records.SelectedcarrierRecord;
import org.transsonic.trustgame.data.trustgame.tables.records.UserRecord;
import org.transsonic.trustgame.data.trustgame.tables.records.UsercarrierRecord;
import org.transsonic.trustgame.data.trustgame.tables.records.UserclickRecord;
import org.transsonic.trustgame.data.trustgame.tables.records.UserorderRecord;
import org.transsonic.trustgame.data.trustgame.tables.records.UserroundRecord;

@WebServlet("/scores")
public class ScoreServlet extends HttpServlet {

    /** */
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        ScoreData data = SessionUtils.getData(session);
        if (data == null) {
            response.sendRedirect("/trustgame-scores/login");
            return;
        }

        String click = "";
        if (request.getParameter("clickType") != null)
            click = request.getParameter("clickType").toString();
        int recordNr = 0;
        if (request.getParameter("recordNr") != null)
            recordNr = Integer.parseInt(request.getParameter("recordNr"));

        switch (click) {

        case "game": {
            data.setSelectedGameId(recordNr);
            data.setSelectedGamePlayId(0);
            data.setSelectedGameUserId(0);
            StringBuilder s = new StringBuilder();
            s.append(startForm());
            s.append(startPickTable());
            s.append(makeGamePicklist(session, data));
            if (recordNr != 0)
                s.append(makeGamePlayPicklist(session, data));
            s.append(endPickTable());
            s.append(endForm());
            data.setContentHtml(s.toString());
            break;
        }

        case "gamePlay": {
            data.setSort("Date");
            data.setSortDown(true);
            data.setSelectedGamePlayId(recordNr);
            data.setSelectedGameUserId(0);
            StringBuilder s = new StringBuilder();
            s.append(startForm());
            s.append(startPickTable());
            s.append(makeGamePicklist(session, data));
            s.append(makeGamePlayPicklist(session, data));
            s.append(endPickTable());
            if (recordNr != 0)
                s.append(makeScoreTable(session, data));
            s.append(endForm());
            data.setContentHtml(s.toString());
            break;
        }

        case "gameUser": {
            data.setSelectedGameUserId(recordNr);
            StringBuilder s = new StringBuilder();
            s.append(startForm());
            s.append(startPickTable());
            s.append(makeGamePicklist(session, data));
            s.append(makeGamePlayPicklist(session, data));
            s.append(endPickTable());
            s.append(makeScoreTable(session, data));
            s.append(endForm());
            data.setContentHtml(s.toString());
            break;
        }

        case "refresh": {
            StringBuilder s = new StringBuilder();
            s.append(startForm());
            s.append(startPickTable());
            s.append(makeGamePicklist(session, data));
            s.append(makeGamePlayPicklist(session, data));
            s.append(endPickTable());
            s.append(makeScoreTable(session, data));
            s.append(endForm());
            data.setContentHtml(s.toString());
            break;
        }

        case "sort": {
            String sort = "";
            if (request.getParameter("sort") != null)
                sort = request.getParameter("sort");
            toggleSort(data, sort);
            StringBuilder s = new StringBuilder();
            s.append(startForm());
            s.append(startPickTable());
            s.append(makeGamePicklist(session, data));
            s.append(makeGamePlayPicklist(session, data));
            s.append(endPickTable());
            s.append(makeScoreTable(session, data));
            s.append(endForm());
            data.setContentHtml(s.toString());
            break;
        }

        default:
            break;
        }

        response.sendRedirect("jsp/scores/scores.jsp");
    }

    private static void toggleSort(ScoreData data, String sort) {
        if (sort.equals(data.getSort())) {
            data.setSortDown(!data.isSortDown());
            return;
        }
        data.setSort(sort);
        data.setSortDown(true);
    }

    public static String startForm() {
        StringBuilder s = new StringBuilder();
        s.append("<div class=\"tg-form\">\n");
        s.append("  <form id=\"scoreForm\" action=\"/trustgame-scores/scores\" method=\"POST\" >\n");
        s.append("    <input id=\"clickType\" type=\"hidden\" name=\"clickType\" value=\"tobefilled\" />\n");
        s.append("    <input id=\"sort\" type=\"hidden\" name=\"sort\" value=\"Date\" />\n");
        s.append("    <input id=\"recordNr\" type=\"hidden\" name=\"recordNr\" value=\"0\" />\n");
        s.append("    <fieldset>\n");
        return s.toString();
    }

    public static String endForm() {
        StringBuilder s = new StringBuilder();
        s.append("    </fieldset>\n");
        s.append("  </form>\n");
        s.append("</div>\n");
        return s.toString();
    }

    public static String startPickTable() {
        StringBuilder s = new StringBuilder();
        s.append("<div class=\"tg-select-table\">\n");
        s.append("  <table>\n");
        return s.toString();
    }

    public static String endPickTable() {
        StringBuilder s = new StringBuilder();
        s.append("  </table>\n");
        s.append("</div>\n");
        return s.toString();
    }

    public static String makeGamePicklist(HttpSession session, ScoreData data) {
        int selectedGameId = data.getSelectedGameId();
        StringBuilder s = new StringBuilder();
        DSLContext dslContext = DSL.using(data.getDataSource(), SQLDialect.MYSQL);
        List<GameRecord> games = dslContext.selectFrom(Tables.GAME).fetch();
        s.append("    <tr>\n");
        s.append("      <td width=\"25%\">Select game</td>\n");
        s.append("      <td width=\"75%\">\n");
        s.append("        <select name=\"game\" id=\"game\" onchange=\"submitSelectGame(); \">");
        s.append("          <option value=\"0\">&nbsp;</option>\n");
        for (GameRecord game : games) {
            int id = game.getId();
            s.append("        <option value=\"");
            s.append(id);
            s.append("\"");
            if (id == selectedGameId) {
                s.append(" selected");
            }
            s.append(">");
            s.append(game.getCode() + " : " + game.getName());
            s.append("</option>\n");
        }
        s.append("        </select>\n");
        s.append("      </td>\n");
        s.append("    </tr>\n");
        return s.toString();
    }

    public static String makeGamePlayPicklist(HttpSession session, ScoreData data) {
        int selectedGamePlayId = data.getSelectedGamePlayId();
        GameRecord game = SqlUtils.readGameFromGameId(data, data.getSelectedGameId());
        StringBuilder s = new StringBuilder();
        DSLContext dslContext = DSL.using(data.getDataSource(), SQLDialect.MYSQL);
        List<GameplayRecord> gamePlays = dslContext.selectFrom(Tables.GAMEPLAY)
                .where(Tables.GAMEPLAY.GAME_ID.eq(game.getId())).fetch();
        s.append("    <tr>\n");
        s.append("      <td width=\"25%\">Select gameplay</td>\n");
        s.append("      <td width=\"75%\">\n");
        s.append("        <select name=\"gamePlay\" id=\"gamePlay\" onchange=\"submitSelectGamePlay(); \">");
        s.append("          <option value=\"0\">&nbsp;</option>\n");
        for (GameplayRecord gamePlay : gamePlays) {
            int id = gamePlay.getId();
            s.append("        <option value=\"");
            s.append(id);
            s.append("\"");
            if (id == selectedGamePlayId) {
                s.append(" selected");
            }
            s.append(">");
            s.append(gamePlay.getGroupdescription());
            s.append("</option>\n");
        }
        s.append("        </select>\n");
        s.append("      </td>\n");
        s.append("    </tr>\n");
        return s.toString();
    }

    public static String makeScoreTable(HttpSession session, ScoreData data) {
        int selectedGamePlayId = data.getSelectedGamePlayId();
        int selectedGameUserId = data.getSelectedGameUserId();
        StringBuilder s = new StringBuilder();
        DSLContext dslContext = DSL.using(data.getDataSource(), SQLDialect.MYSQL);
        List<GameuserRecord> gameUserRecords = dslContext.selectFrom(Tables.GAMEUSER)
                .where(Tables.GAMEUSER.GAMEPLAY_ID.eq(selectedGamePlayId)).fetch();

        s.append("\n<br>&nbsp;<br><div class=\"tg-users-score\">\n");
        s.append("  <table width=\"100%\" style=\"border:none; padding:0px; margin:0px;\">\n");
        s.append("   <tr><td width=\"50%\" style=\"border:none; padding:0px; margin:0px;\">\n");
        s.append("      <a href=\"#\" onClick=\"submitRefresh()\"; return false;\">Refresh</a>\n");
        s.append("   </td><td style=\"border:none; padding:0px; margin:0px;\">&nbsp;</td></tr>\n");
        s.append("   <tr><td width=\"50%\" style=\"border:none; padding:0px; margin:0px;\">\n");
        s.append("    <div class=\"tg-scores-line-table\">");
        s.append("       <table width=\"90%\">\n");
        s.append("          <thead>\n");
        s.append("            <tr>\n");
        appendSortHeader(data, s, "left", "Name");
        appendSortHeader(data, s, "left", "Date");
        appendSortHeader(data, s, "center", "Played");
        appendSortHeader(data, s, "center", "Round");
        appendSortHeader(data, s, "center", "Profit");
        appendSortHeader(data, s, "center", "Satisf");
        appendSortHeader(data, s, "center", "Sustain");
        s.append("            </tr>\n");
        s.append("          </thead>\n");
        s.append("          <tbody>\n");

        // sort the data
        SortedMap<String, GameuserRecord> sortedRecords = data.isSortDown() ? new TreeMap<>()
                : new TreeMap<>(Collections.reverseOrder());
        int unique = 0;
        for (GameuserRecord gameUser : gameUserRecords) {
            UserRecord user = SqlUtils.readUserFromUserId(data, gameUser.getUserId());
            String key = "";
            switch (data.getSort()) {
            case "Name":
                key = user.getUsername();
                break;
            case "Date":
                if (user.getCreatetime() == null)
                    key = "-";
                else
                    key = user.getCreatetime().format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm"));
                break;
            case "Played" :
                key = userHasPlayed(data, gameUser) ? "Y" : "N";
                break;
            case "Round" :
                key = String.format("%05d", gameUser.getRoundnumber().intValue());
                break;
            case "Profit" :
                key = String.format("%05d", gameUser.getScoreprofit());
                break;
            case "Satisf" :
                key = String.format("%05d", gameUser.getScoresatisfaction());
                break;
            case "Sustain" :
                key = String.format("%05d", gameUser.getScoresustainability());
                break;
            default:
                break;
            }
            sortedRecords.put(key + String.format("%08d", unique++), gameUser);
        }

        // display the table
        for (GameuserRecord gameUser : sortedRecords.values()) {
            s.append("            <tr>\n");
            s.append("              <td align=\"left\">");
            UserRecord user = SqlUtils.readUserFromUserId(data, gameUser.getUserId());
            if (gameUser.getId() == selectedGameUserId)
                s.append("              <span class=\"tg-scores-highlight\">\n");
            else
                s.append("              <span>\n");
            s.append("<a href=\"#\" onClick=\"submitSelectGameUser(");
            s.append(gameUser.getId());
            s.append("); return false;\">");
            s.append(user.getUsername());
            s.append("</a></span>\n"); // tg-scores-line-field
            s.append("                </span>\n"); // tg-scores-line
            s.append("</td>\n");
            s.append("              <td align=\"left\">");
            if (user.getCreatetime() == null)
                s.append("-");
            else
                s.append(user.getCreatetime().format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm")));
            s.append("</td>\n");
            s.append("              <td align=\"center\">");
            s.append(userHasPlayed(data, gameUser) ? "Y" : "N");
            s.append("</td>\n");
            s.append("              <td align=\"center\">");
            s.append(gameUser.getRoundnumber());
            s.append("</td>\n");
            s.append("              <td align=\"center\">");
            s.append(gameUser.getScoreprofit());
            s.append("</td>\n");
            s.append("              <td align=\"center\">");
            s.append(gameUser.getScoresatisfaction());
            s.append("</td>\n");
            s.append("              <td align=\"center\">");
            s.append(gameUser.getScoresustainability());
            s.append("</td>\n");
            s.append("            </tr>\n");
        }
        s.append("          </tbody>\n");
        s.append("        </table>\n");
        s.append("      </div>");
        s.append("    </div>\n"); // tg-scores-line-table
        s.append("   </td>\n");

        s.append(makeDetailedScoreTable(session, data));

        return s.toString();
    }

    private static void appendSortHeader(ScoreData data, StringBuilder s, String pos, String name) {
        s.append("              <td align=\"");
        s.append(pos);
        s.append("\">");
        s.append("<a href=\"#\" onClick=\"submitSort('");
        s.append(name);
        s.append("'); return false;\">");
        s.append(name);
        s.append("</a>");
        if (data.getSort().equals(name)) {
            if (data.isSortDown())
                s.append("&nbsp;&darr;");
            else
                s.append("&nbsp;&uarr;");
        }
        s.append("</td>\n");
    }

    public static String makeDetailedScoreTable(HttpSession session, ScoreData data) {

        StringBuilder s = new StringBuilder();

        if (data.getSelectedGameUserId() == 0) {
            s.append("   <td style=\"border:none; padding:0px; margin:0px;\">No selected user</td></tr>\n");
            s.append("  </table>\n"); // outer 2-column table
            s.append("</div>\n"); // tg-users-score
            return s.toString();
        }

        DSLContext dslContext = DSL.using(data.getDataSource(), SQLDialect.MYSQL);
        GameuserRecord gameUser = dslContext.selectFrom(Tables.GAMEUSER)
                .where(Tables.GAMEUSER.ID.eq(data.getSelectedGameUserId())).fetchAny();
        GameplayRecord gamePlay = dslContext.selectFrom(Tables.GAMEPLAY)
                .where(Tables.GAMEPLAY.ID.eq(gameUser.getGameplayId())).fetchAny();
        GameRecord game = SqlUtils.readGameFromGameId(data, gamePlay.getGameId());
        MissionRecord mission = dslContext.selectFrom(Tables.MISSION).where(Tables.MISSION.GAME_ID.eq(game.getId()))
                .fetchAny();
        List<UsercarrierRecord> userCarriers = dslContext.selectFrom(Tables.USERCARRIER)
                .where(Tables.USERCARRIER.GAMEUSER_ID.eq(gameUser.getId())).fetch(); // bought reports

        // rounds
        List<RoundRecord> roundRecords = dslContext.selectFrom(Tables.ROUND)
                .where(Tables.ROUND.GAME_ID.eq(game.getId())).fetch().sortAsc(Tables.ROUND.ROUNDNUMBER);
        SortedMap<Integer, RoundRecord> roundMap = new TreeMap<>();
        for (RoundRecord roundRecord : roundRecords) {
            roundMap.put(roundRecord.getRoundnumber(), roundRecord);
        }

        // orders
        SortedMap<Integer, List<OrderRecord>> orderMap = new TreeMap<>();
        for (RoundRecord roundRecord : roundRecords) {
            List<OrderRecord> orderList = dslContext.selectFrom(Tables.ORDER)
                    .where(Tables.ORDER.ROUND_ID.eq(roundRecord.getId())).fetch().sortAsc(Tables.ORDER.ORDERNUMBER);
            orderMap.put(roundRecord.getRoundnumber(), orderList);
        }

        // highest rounds number
        int highestRoundNumber = gameUser.getRoundnumber().intValue();

        s.append("   <td style=\"border:none; padding:0px; margin:0px;\">\n");
        s.append("\n<div class=\"tg-detail-score\">\n");
        s.append("  <table width=\"100%\">\n");
        s.append("    <thead><tr><td align=\"center\">Round</td><td align=\"center\">Order</td><td>Chosen Carrier</td>"
                + "<td align=\"center\">Profit</td><td align=\"center\">Satisfaction</td>"
                + "<td align=\"center\">Sustainability</td></tr></thead>\n");
        s.append("    <tbody>\n");

        s.append("           <tr><td align=\"center\">Start</td><td>&nbsp;</td><td>&nbsp;</td><td align=\"center\">");
        s.append(mission.getStartprofit());
        s.append("</td><td align=\"center\">");
        s.append(mission.getStartsatisfaction());
        s.append("</td><td align=\"center\">");
        s.append(mission.getStartsustainability());
        s.append("</td></tr>\n");

        for (int round = 1; round < highestRoundNumber; round++) {
            UserroundRecord userRound = dslContext.selectFrom(Tables.USERROUND)
                    .where(Tables.USERROUND.ROUND_ID.eq(roundMap.get(round).getId())
                            .and(Tables.USERROUND.GAMEPLAY_ID.eq(gamePlay.getId()))
                            .and(Tables.USERROUND.GAMEUSER_ID.eq(gameUser.getId())))
                    .fetchAny();

            // bought reports
            for (UsercarrierRecord userCarrier : userCarriers) {
                if (userCarrier.getRoundnumber() == round) {
                    CarrierRecord carrier = dslContext.selectFrom(Tables.CARRIER)
                            .where(Tables.CARRIER.ID.eq(userCarrier.getCarrierId())).fetchAny();
                    s.append("           <tr><td align=\"center\">");
                    s.append(round);
                    s.append("</td><td align=\"center\">");
                    s.append("Report");
                    s.append("</td><td>");
                    s.append(carrier == null ? "-" : carrier.getName());
                    s.append("</td><td align=\"center\">");
                    s.append("-5");
                    s.append("</td><td align=\"center\">");
                    s.append("-");
                    s.append("</td><td align=\"center\">");
                    s.append("-");
                    s.append("</td></tr>\n");
                }
            }

            // orders and their scores
            for (OrderRecord order : orderMap.get(round)) {
                UserorderRecord userOrder = userRound == null ? null
                        : dslContext.selectFrom(Tables.USERORDER).where(Tables.USERORDER.ORDER_ID.eq(order.getId())
                                .and(Tables.USERORDER.USERROUND_ID.eq(userRound.getId()))).fetchAny();
                SelectedcarrierRecord selectedCarrier = userOrder == null ? null
                        : dslContext.selectFrom(Tables.SELECTEDCARRIER)
                                .where(Tables.SELECTEDCARRIER.USERORDER_ID.eq(userOrder.getId())).fetchAny();
                OrdercarrierRecord orderCarrier = selectedCarrier == null ? null
                        : dslContext.selectFrom(Tables.ORDERCARRIER)
                                .where(Tables.ORDERCARRIER.ID.eq(selectedCarrier.getOrdercarrierId())).fetchAny();
                CarrierRecord carrier = selectedCarrier == null ? null
                        : dslContext.selectFrom(Tables.CARRIER).where(Tables.CARRIER.ID.eq(orderCarrier.getCarrierId()))
                                .fetchAny();
                boolean practice = roundMap.get(round).getTestround() != 0;
                s.append("           <tr><td align=\"center\">");
                s.append(round);
                if (practice)
                    s.append(" (Practice)");
                s.append("</td><td align=\"center\">");
                s.append(order.getOrdernumber());
                s.append("</td><td>");
                s.append(carrier == null ? "-" : carrier.getName());
                s.append("</td><td align=\"center\">");
                if (order == null || orderCarrier == null)
                    s.append("-");
                else {
                    int profit = order.getTransportearnings() - orderCarrier.getQuoteoffer()
                            + orderCarrier.getExtraprofit();
                    if (practice)
                        s.append("(" + profit + ")");
                    else
                        s.append(profit);
                }
                s.append("</td><td align=\"center\">");
                String satis = orderCarrier == null ? "-" : Integer.toString(orderCarrier.getOutcomesatisfaction());
                if (practice)
                    s.append("(" + satis + ")");
                else
                    s.append(satis);
                s.append("</td><td align=\"center\">");
                String sust = orderCarrier == null ? "-" : Integer.toString(orderCarrier.getOutcomesustainability());
                if (practice)
                    s.append("(" + sust + ")");
                else
                    s.append(sust);
                s.append("</td></tr>\n");
            }
        }

        s.append("           <tr><td align=\"center\">Total</td><td>&nbsp;</td><td>&nbsp;</td><td align=\"center\">");
        s.append(gameUser.getScoreprofit());
        s.append("</td><td align=\"center\">");
        s.append(gameUser.getScoresatisfaction());
        s.append("</td><td align=\"center\">");
        s.append(gameUser.getScoresustainability());
        s.append("</td></tr>\n");

        s.append("    </tbody>\n");
        s.append("  </table>\n");
        s.append("</div>\n"); // tg-detail-score

        s.append("   </td></tr>\n");
        s.append("  </table>\n"); // outer 2-column table
        s.append("</div>\n"); // tg-users-score
        return s.toString();
    }

    public static void fillInitialScreen(HttpSession session, ScoreData data) {
        StringBuilder s = new StringBuilder();
        s.append(startForm());
        s.append(startPickTable());
        s.append(makeGamePicklist(session, data));
        s.append(endPickTable());
        s.append(endForm());
        data.setContentHtml(s.toString());
    }

    private static boolean userHasPlayed(ScoreData data, GameuserRecord gameUser) {
        if (gameUser.getRoundnumber().intValue() > 1 || gameUser.getRoundstatus() != 0)
            return true;
        DSLContext dslContext = DSL.using(data.getDataSource(), SQLDialect.MYSQL);
        UserclickRecord userClick = dslContext.selectFrom(Tables.USERCLICK)
                .where(Tables.USERCLICK.GAMEUSER_ID.eq(gameUser.getId())).fetchAny(); // clicks
        if (userClick != null)
            return true;
        UserroundRecord userRound = dslContext.selectFrom(Tables.USERROUND)
                .where(Tables.USERROUND.GAMEUSER_ID.eq(gameUser.getId())).fetchAny(); // rounds
        if (userRound != null)
            return true;
        UsercarrierRecord userCarrier = dslContext.selectFrom(Tables.USERCARRIER)
                .where(Tables.USERCARRIER.GAMEUSER_ID.eq(gameUser.getId())).fetchAny(); // bought reports
        if (userCarrier != null)
            return true;
        return false;
    }

}
