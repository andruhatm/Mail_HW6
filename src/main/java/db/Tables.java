/*
 * This file is generated by jOOQ.
 */
package db;


import db.tables.FlywaySchemaHistory;
import db.tables.Nomenclature;
import db.tables.Organization;
import db.tables.Waybill;
import db.tables.WaybillItems;

import javax.annotation.Generated;


/**
 * Convenience access to all tables in public.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.14.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>public.flyway_schema_history</code>.
     */
    public static final FlywaySchemaHistory FLYWAY_SCHEMA_HISTORY = FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY;

    /**
     * The table <code>public.Nomenclature</code>.
     */
    public static final Nomenclature NOMENCLATURE = Nomenclature.NOMENCLATURE;

    /**
     * The table <code>public.Organization</code>.
     */
    public static final Organization ORGANIZATION = Organization.ORGANIZATION;

    /**
     * The table <code>public.Waybill</code>.
     */
    public static final Waybill WAYBILL = Waybill.WAYBILL;

    /**
     * The table <code>public.Waybill_items</code>.
     */
    public static final WaybillItems WAYBILL_ITEMS = WaybillItems.WAYBILL_ITEMS;
}
