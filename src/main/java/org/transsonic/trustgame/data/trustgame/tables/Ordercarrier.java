/*
 * This file is generated by jOOQ.
 */
package org.transsonic.trustgame.data.trustgame.tables;


import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row9;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.transsonic.trustgame.data.trustgame.Indexes;
import org.transsonic.trustgame.data.trustgame.Keys;
import org.transsonic.trustgame.data.trustgame.Trustgame;
import org.transsonic.trustgame.data.trustgame.tables.records.OrdercarrierRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Ordercarrier extends TableImpl<OrdercarrierRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>trustgame.ordercarrier</code>
     */
    public static final Ordercarrier ORDERCARRIER = new Ordercarrier();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<OrdercarrierRecord> getRecordType() {
        return OrdercarrierRecord.class;
    }

    /**
     * The column <code>trustgame.ordercarrier.ID</code>.
     */
    public final TableField<OrdercarrierRecord, Integer> ID = createField(DSL.name("ID"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>trustgame.ordercarrier.Order_ID</code>.
     */
    public final TableField<OrdercarrierRecord, Integer> ORDER_ID = createField(DSL.name("Order_ID"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>trustgame.ordercarrier.Carrier_ID</code>.
     */
    public final TableField<OrdercarrierRecord, Integer> CARRIER_ID = createField(DSL.name("Carrier_ID"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>trustgame.ordercarrier.QuoteOffer</code>. Number between 1 and 10
     */
    public final TableField<OrdercarrierRecord, Integer> QUOTEOFFER = createField(DSL.name("QuoteOffer"), SQLDataType.INTEGER.nullable(false), this, "Number between 1 and 10");

    /**
     * The column <code>trustgame.ordercarrier.ExtraProfit</code>.
     */
    public final TableField<OrdercarrierRecord, Integer> EXTRAPROFIT = createField(DSL.name("ExtraProfit"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("0", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>trustgame.ordercarrier.OutcomeSustainability</code>.
     */
    public final TableField<OrdercarrierRecord, Integer> OUTCOMESUSTAINABILITY = createField(DSL.name("OutcomeSustainability"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>trustgame.ordercarrier.OutcomeSatisfaction</code>.
     */
    public final TableField<OrdercarrierRecord, Integer> OUTCOMESATISFACTION = createField(DSL.name("OutcomeSatisfaction"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>trustgame.ordercarrier.OutcomeMessage</code>.
     */
    public final TableField<OrdercarrierRecord, String> OUTCOMEMESSAGE = createField(DSL.name("OutcomeMessage"), SQLDataType.CLOB.defaultValue(DSL.field("NULL", SQLDataType.CLOB)), this, "");

    /**
     * The column <code>trustgame.ordercarrier.TransportMessage</code>.
     */
    public final TableField<OrdercarrierRecord, String> TRANSPORTMESSAGE = createField(DSL.name("TransportMessage"), SQLDataType.CLOB.defaultValue(DSL.field("NULL", SQLDataType.CLOB)), this, "");

    private Ordercarrier(Name alias, Table<OrdercarrierRecord> aliased) {
        this(alias, aliased, null);
    }

    private Ordercarrier(Name alias, Table<OrdercarrierRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>trustgame.ordercarrier</code> table reference
     */
    public Ordercarrier(String alias) {
        this(DSL.name(alias), ORDERCARRIER);
    }

    /**
     * Create an aliased <code>trustgame.ordercarrier</code> table reference
     */
    public Ordercarrier(Name alias) {
        this(alias, ORDERCARRIER);
    }

    /**
     * Create a <code>trustgame.ordercarrier</code> table reference
     */
    public Ordercarrier() {
        this(DSL.name("ordercarrier"), null);
    }

    public <O extends Record> Ordercarrier(Table<O> child, ForeignKey<O, OrdercarrierRecord> key) {
        super(child, key, ORDERCARRIER);
    }

    @Override
    public Schema getSchema() {
        return Trustgame.TRUSTGAME;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.ORDERCARRIER_FK_ORDERCARRIER_CARRIER1_IDX, Indexes.ORDERCARRIER_FK_ORDERCARRIER_ORDER1_IDX);
    }

    @Override
    public Identity<OrdercarrierRecord, Integer> getIdentity() {
        return (Identity<OrdercarrierRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<OrdercarrierRecord> getPrimaryKey() {
        return Keys.KEY_ORDERCARRIER_PRIMARY;
    }

    @Override
    public List<UniqueKey<OrdercarrierRecord>> getKeys() {
        return Arrays.<UniqueKey<OrdercarrierRecord>>asList(Keys.KEY_ORDERCARRIER_PRIMARY, Keys.KEY_ORDERCARRIER_ID_UNIQUE);
    }

    @Override
    public List<ForeignKey<OrdercarrierRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<OrdercarrierRecord, ?>>asList(Keys.FK_ORDERCARRIER_ORDER1, Keys.FK_ORDERCARRIER_CARRIER1);
    }

    private transient Order _order;
    private transient Carrier _carrier;

    public Order order() {
        if (_order == null)
            _order = new Order(this, Keys.FK_ORDERCARRIER_ORDER1);

        return _order;
    }

    public Carrier carrier() {
        if (_carrier == null)
            _carrier = new Carrier(this, Keys.FK_ORDERCARRIER_CARRIER1);

        return _carrier;
    }

    @Override
    public Ordercarrier as(String alias) {
        return new Ordercarrier(DSL.name(alias), this);
    }

    @Override
    public Ordercarrier as(Name alias) {
        return new Ordercarrier(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Ordercarrier rename(String name) {
        return new Ordercarrier(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Ordercarrier rename(Name name) {
        return new Ordercarrier(name, null);
    }

    // -------------------------------------------------------------------------
    // Row9 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row9<Integer, Integer, Integer, Integer, Integer, Integer, Integer, String, String> fieldsRow() {
        return (Row9) super.fieldsRow();
    }
}
