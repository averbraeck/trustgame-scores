/*
 * This file is generated by jOOQ.
 */
package org.transsonic.trustgame.data.trustgame.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;
import org.transsonic.trustgame.data.trustgame.tables.Round;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RoundRecord extends UpdatableRecordImpl<RoundRecord> implements Record4<Integer, Integer, Integer, Byte> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>trustgame.round.ID</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>trustgame.round.ID</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>trustgame.round.Game_ID</code>.
     */
    public void setGameId(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>trustgame.round.Game_ID</code>.
     */
    public Integer getGameId() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>trustgame.round.RoundNumber</code>.
     */
    public void setRoundnumber(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>trustgame.round.RoundNumber</code>.
     */
    public Integer getRoundnumber() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>trustgame.round.TestRound</code>. If true, do not count the points for ths round
     */
    public void setTestround(Byte value) {
        set(3, value);
    }

    /**
     * Getter for <code>trustgame.round.TestRound</code>. If true, do not count the points for ths round
     */
    public Byte getTestround() {
        return (Byte) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, Integer, Integer, Byte> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, Integer, Integer, Byte> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Round.ROUND.ID;
    }

    @Override
    public Field<Integer> field2() {
        return Round.ROUND.GAME_ID;
    }

    @Override
    public Field<Integer> field3() {
        return Round.ROUND.ROUNDNUMBER;
    }

    @Override
    public Field<Byte> field4() {
        return Round.ROUND.TESTROUND;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public Integer component2() {
        return getGameId();
    }

    @Override
    public Integer component3() {
        return getRoundnumber();
    }

    @Override
    public Byte component4() {
        return getTestround();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public Integer value2() {
        return getGameId();
    }

    @Override
    public Integer value3() {
        return getRoundnumber();
    }

    @Override
    public Byte value4() {
        return getTestround();
    }

    @Override
    public RoundRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public RoundRecord value2(Integer value) {
        setGameId(value);
        return this;
    }

    @Override
    public RoundRecord value3(Integer value) {
        setRoundnumber(value);
        return this;
    }

    @Override
    public RoundRecord value4(Byte value) {
        setTestround(value);
        return this;
    }

    @Override
    public RoundRecord values(Integer value1, Integer value2, Integer value3, Byte value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RoundRecord
     */
    public RoundRecord() {
        super(Round.ROUND);
    }

    /**
     * Create a detached, initialised RoundRecord
     */
    public RoundRecord(Integer id, Integer gameId, Integer roundnumber, Byte testround) {
        super(Round.ROUND);

        setId(id);
        setGameId(gameId);
        setRoundnumber(roundnumber);
        setTestround(testround);
    }
}
