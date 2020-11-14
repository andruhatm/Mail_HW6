/*
 * This file is generated by jOOQ.
 */
package db.tables;


import db.Keys;
import db.Public;
import db.tables.records.WaybillItemsRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.14.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class WaybillItems extends TableImpl<WaybillItemsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.Waybill_items</code>
     */
    public static final WaybillItems WAYBILL_ITEMS = new WaybillItems();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<WaybillItemsRecord> getRecordType() {
        return WaybillItemsRecord.class;
    }

    /**
     * The column <code>public.Waybill_items.item_id</code>.
     */
    public final TableField<WaybillItemsRecord, Integer> ITEM_ID = createField(DSL.name("item_id"), SQLDataType.INTEGER.nullable(false).defaultValue(DSL.field("nextval('\"Waybill_items_item_id_seq\"'::regclass)", SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.Waybill_items.waybill_id</code>.
     */
    public final TableField<WaybillItemsRecord, Integer> WAYBILL_ID = createField(DSL.name("waybill_id"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.Waybill_items.quantity</code>.
     */
    public final TableField<WaybillItemsRecord, Integer> QUANTITY = createField(DSL.name("quantity"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.Waybill_items.nomenclature</code>.
     */
    public final TableField<WaybillItemsRecord, Integer> NOMENCLATURE = createField(DSL.name("nomenclature"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.Waybill_items.price</code>.
     */
    public final TableField<WaybillItemsRecord, Double> PRICE = createField(DSL.name("price"), SQLDataType.DOUBLE, this, "");

    private WaybillItems(Name alias, Table<WaybillItemsRecord> aliased) {
        this(alias, aliased, null);
    }

    private WaybillItems(Name alias, Table<WaybillItemsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.Waybill_items</code> table reference
     */
    public WaybillItems(String alias) {
        this(DSL.name(alias), WAYBILL_ITEMS);
    }

    /**
     * Create an aliased <code>public.Waybill_items</code> table reference
     */
    public WaybillItems(Name alias) {
        this(alias, WAYBILL_ITEMS);
    }

    /**
     * Create a <code>public.Waybill_items</code> table reference
     */
    public WaybillItems() {
        this(DSL.name("Waybill_items"), null);
    }

    public <O extends Record> WaybillItems(Table<O> child, ForeignKey<O, WaybillItemsRecord> key) {
        super(child, key, WAYBILL_ITEMS);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public UniqueKey<WaybillItemsRecord> getPrimaryKey() {
        return Keys.WAYBILL_ITEMS_PK;
    }

    @Override
    public List<UniqueKey<WaybillItemsRecord>> getKeys() {
        return Arrays.<UniqueKey<WaybillItemsRecord>>asList(Keys.WAYBILL_ITEMS_PK);
    }

    @Override
    public List<ForeignKey<WaybillItemsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<WaybillItemsRecord, ?>>asList(Keys.WAYBILL_ITEMS__WAYBILL_ITEMS_FK0, Keys.WAYBILL_ITEMS__WAYBILL_ITEMS_FK1);
    }

    public Waybill waybill() {
        return new Waybill(this, Keys.WAYBILL_ITEMS__WAYBILL_ITEMS_FK0);
    }

    public Nomenclature nomenclature() {
        return new Nomenclature(this, Keys.WAYBILL_ITEMS__WAYBILL_ITEMS_FK1);
    }

    @Override
    public WaybillItems as(String alias) {
        return new WaybillItems(DSL.name(alias), this);
    }

    @Override
    public WaybillItems as(Name alias) {
        return new WaybillItems(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public WaybillItems rename(String name) {
        return new WaybillItems(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public WaybillItems rename(Name name) {
        return new WaybillItems(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, Integer, Integer, Integer, Double> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
