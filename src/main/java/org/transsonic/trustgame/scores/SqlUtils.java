package org.transsonic.trustgame.scores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.transsonic.trustgame.data.trustgame.Tables;
import org.transsonic.trustgame.data.trustgame.tables.records.CarrierRecord;
import org.transsonic.trustgame.data.trustgame.tables.records.ClientRecord;
import org.transsonic.trustgame.data.trustgame.tables.records.GameRecord;
import org.transsonic.trustgame.data.trustgame.tables.records.GameplayRecord;
import org.transsonic.trustgame.data.trustgame.tables.records.OrderRecord;
import org.transsonic.trustgame.data.trustgame.tables.records.OrdercarrierRecord;
import org.transsonic.trustgame.data.trustgame.tables.records.RoundRecord;
import org.transsonic.trustgame.data.trustgame.tables.records.UserRecord;

public final class SqlUtils {

    private SqlUtils() {
        // utility class
    }

    public static Connection dbConnection() throws SQLException, ClassNotFoundException {
        String jdbcURL = "jdbc:mysql://localhost:3306/trustgame";
        String dbUser = "trustgame";
        String dbPassword = "TG%s1naval%2105";

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
    }

    public static GameRecord readGameFromGameId(final ScoreData data, final Integer gameId) {
        DSLContext dslContext = DSL.using(data.getDataSource(), SQLDialect.MYSQL);
        return dslContext.selectFrom(Tables.GAME).where(Tables.GAME.ID.eq(gameId)).fetchAny();
    }

    public static RoundRecord readRoundFromRoundId(final ScoreData data, final Integer roundId) {
        DSLContext dslContext = DSL.using(data.getDataSource(), SQLDialect.MYSQL);
        return dslContext.selectFrom(Tables.ROUND).where(Tables.ROUND.ID.eq(roundId)).fetchAny();
    }

    public static OrderRecord readOrderFromOrderId(final ScoreData data, final Integer orderId) {
        DSLContext dslContext = DSL.using(data.getDataSource(), SQLDialect.MYSQL);
        return dslContext.selectFrom(Tables.ORDER).where(Tables.ORDER.ID.eq(orderId)).fetchAny();
    }

    public static OrdercarrierRecord readOrderCarrierFromOrderCarrierId(final ScoreData data,
            final Integer orderCarrierId) {
        DSLContext dslContext = DSL.using(data.getDataSource(), SQLDialect.MYSQL);
        return dslContext.selectFrom(Tables.ORDERCARRIER).where(Tables.ORDERCARRIER.ID.eq(orderCarrierId)).fetchAny();
    }

    public static GameplayRecord readGamePlayFromGamePlayId(final ScoreData data, final Integer gamePlayId) {
        DSLContext dslContext = DSL.using(data.getDataSource(), SQLDialect.MYSQL);
        return dslContext.selectFrom(Tables.GAMEPLAY).where(Tables.GAMEPLAY.ID.eq(gamePlayId)).fetchAny();
    }

    public static UserRecord readUserFromUserId(final ScoreData data, final Integer userId) {
        DSLContext dslContext = DSL.using(data.getDataSource(), SQLDialect.MYSQL);
        return dslContext.selectFrom(Tables.USER).where(Tables.USER.ID.eq(userId)).fetchAny();
    }

    public static UserRecord readUserFromUsername(final ScoreData data, final String username) {
        DSLContext dslContext = DSL.using(data.getDataSource(), SQLDialect.MYSQL);
        return dslContext.selectFrom(Tables.USER).where(Tables.USER.USERNAME.eq(username)).fetchAny();
    }

    public static ClientRecord readClientFromClientId(final ScoreData data, final Integer clientId) {
        DSLContext dslContext = DSL.using(data.getDataSource(), SQLDialect.MYSQL);
        return dslContext.selectFrom(Tables.CLIENT).where(Tables.CLIENT.ID.eq(clientId)).fetchAny();
    }

    public static CarrierRecord readCarrierFromCarrierId(final ScoreData data, final Integer carrierId) {
        DSLContext dslContext = DSL.using(data.getDataSource(), SQLDialect.MYSQL);
        return dslContext.selectFrom(Tables.CARRIER).where(Tables.CARRIER.ID.eq(carrierId)).fetchAny();
    }

}
