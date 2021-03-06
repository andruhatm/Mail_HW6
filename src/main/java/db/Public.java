/*
 * This file is generated by jOOQ.
 */
package db;


import db.tables.FlywaySchemaHistory;
import db.tables.Nomenclature;
import db.tables.Organization;
import db.tables.Waybill;
import db.tables.WaybillItems;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Sequence;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class Public extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public</code>
     */
    public static final Public PUBLIC = new Public();

    /**
     * The table <code>public.flyway_schema_history</code>.
     */
    public final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;

    /**
     * The table <code>public.Nomenclature</code>.
     */
    public final Nomenclature NOMENCLATURE = Nomenclature.NOMENCLATURE;

    /**
     * The table <code>public.Organization</code>.
     */
    public final Organization ORGANIZATION = Organization.ORGANIZATION;

    /**
     * The table <code>public.Waybill</code>.
     */
    public final Waybill WAYBILL = Waybill.WAYBILL;

    /**
     * The table <code>public.Waybill_items</code>.
     */
    public final WaybillItems WAYBILL_ITEMS = WaybillItems.WAYBILL_ITEMS;

    /**
     * No further instances allowed
     */
    private Public() {
        super("public", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Sequence<?>> getSequences() {
        return Arrays.<Sequence<?>>asList(
            Sequences.ORGANIZATION_ID_SEQ,
            Sequences.WAYBILL_ID_SEQ,
            Sequences.WAYBILL_ITEMS_ITEM_ID_SEQ);
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.<Table<?>>asList(
            FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY,
            Nomenclature.NOMENCLATURE,
            Organization.ORGANIZATION,
            Waybill.WAYBILL,
            WaybillItems.WAYBILL_ITEMS);
    }
}
