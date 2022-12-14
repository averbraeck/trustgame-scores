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
import org.jooq.Row10;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.types.UInteger;
import org.transsonic.trustgame.data.trustgame.Indexes;
import org.transsonic.trustgame.data.trustgame.Keys;
import org.transsonic.trustgame.data.trustgame.Trustgame;
import org.transsonic.trustgame.data.trustgame.tables.records.FbreportRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Fbreport extends TableImpl<FbreportRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>trustgame.fbreport</code>
     */
    public static final Fbreport FBREPORT = new Fbreport();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<FbreportRecord> getRecordType() {
        return FbreportRecord.class;
    }

    /**
     * The column <code>trustgame.fbreport.ID</code>.
     */
    public final TableField<FbreportRecord, Integer> ID = createField(DSL.name("ID"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>trustgame.fbreport.Carrier_ID</code>.
     */
    public final TableField<FbreportRecord, Integer> CARRIER_ID = createField(DSL.name("Carrier_ID"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>trustgame.fbreport.FBRegistration</code>.
     */
    public final TableField<FbreportRecord, String> FBREGISTRATION = createField(DSL.name("FBRegistration"), SQLDataType.VARCHAR(8).nullable(false), this, "");

    /**
     * The column <code>trustgame.fbreport.CountryCode</code>.
     */
    public final TableField<FbreportRecord, String> COUNTRYCODE = createField(DSL.name("CountryCode"), SQLDataType.VARCHAR(2).nullable(false), this, "");

    /**
     * The column <code>trustgame.fbreport.Address</code>.
     */
    public final TableField<FbreportRecord, String> ADDRESS = createField(DSL.name("Address"), SQLDataType.VARCHAR(45).nullable(false), this, "");

    /**
     * The column <code>trustgame.fbreport.FBMemberSince</code>.
     */
    public final TableField<FbreportRecord, UInteger> FBMEMBERSINCE = createField(DSL.name("FBMemberSince"), SQLDataType.INTEGERUNSIGNED.nullable(false), this, "");

    /**
     * The column <code>trustgame.fbreport.ServiceOntime</code>.
     */
    public final TableField<FbreportRecord, byte[]> SERVICEONTIME = createField(DSL.name("ServiceOntime"), SQLDataType.BLOB.defaultValue(DSL.field("NULL", SQLDataType.BLOB)), this, "");

    /**
     * The column <code>trustgame.fbreport.ServiceSatisfaction</code>.
     */
    public final TableField<FbreportRecord, byte[]> SERVICESATISFACTION = createField(DSL.name("ServiceSatisfaction"), SQLDataType.BLOB.defaultValue(DSL.field("NULL", SQLDataType.BLOB)), this, "");

    /**
     * The column <code>trustgame.fbreport.TechnicalFleet</code>.
     */
    public final TableField<FbreportRecord, byte[]> TECHNICALFLEET = createField(DSL.name("TechnicalFleet"), SQLDataType.BLOB.defaultValue(DSL.field("NULL", SQLDataType.BLOB)), this, "");

    /**
     * The column <code>trustgame.fbreport.TechnicalGreen</code>.
     */
    public final TableField<FbreportRecord, byte[]> TECHNICALGREEN = createField(DSL.name("TechnicalGreen"), SQLDataType.BLOB.defaultValue(DSL.field("NULL", SQLDataType.BLOB)), this, "");

    private Fbreport(Name alias, Table<FbreportRecord> aliased) {
        this(alias, aliased, null);
    }

    private Fbreport(Name alias, Table<FbreportRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>trustgame.fbreport</code> table reference
     */
    public Fbreport(String alias) {
        this(DSL.name(alias), FBREPORT);
    }

    /**
     * Create an aliased <code>trustgame.fbreport</code> table reference
     */
    public Fbreport(Name alias) {
        this(alias, FBREPORT);
    }

    /**
     * Create a <code>trustgame.fbreport</code> table reference
     */
    public Fbreport() {
        this(DSL.name("fbreport"), null);
    }

    public <O extends Record> Fbreport(Table<O> child, ForeignKey<O, FbreportRecord> key) {
        super(child, key, FBREPORT);
    }

    @Override
    public Schema getSchema() {
        return Trustgame.TRUSTGAME;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.FBREPORT_FK_FBREPORT_CARRIER1_IDX);
    }

    @Override
    public Identity<FbreportRecord, Integer> getIdentity() {
        return (Identity<FbreportRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<FbreportRecord> getPrimaryKey() {
        return Keys.KEY_FBREPORT_PRIMARY;
    }

    @Override
    public List<UniqueKey<FbreportRecord>> getKeys() {
        return Arrays.<UniqueKey<FbreportRecord>>asList(Keys.KEY_FBREPORT_PRIMARY, Keys.KEY_FBREPORT_ID_UNIQUE);
    }

    @Override
    public List<ForeignKey<FbreportRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<FbreportRecord, ?>>asList(Keys.FK_FBREPORT_CARRIER1);
    }

    private transient Carrier _carrier;

    public Carrier carrier() {
        if (_carrier == null)
            _carrier = new Carrier(this, Keys.FK_FBREPORT_CARRIER1);

        return _carrier;
    }

    @Override
    public Fbreport as(String alias) {
        return new Fbreport(DSL.name(alias), this);
    }

    @Override
    public Fbreport as(Name alias) {
        return new Fbreport(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Fbreport rename(String name) {
        return new Fbreport(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Fbreport rename(Name name) {
        return new Fbreport(name, null);
    }

    // -------------------------------------------------------------------------
    // Row10 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row10<Integer, Integer, String, String, String, UInteger, byte[], byte[], byte[], byte[]> fieldsRow() {
        return (Row10) super.fieldsRow();
    }
}
