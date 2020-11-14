/*
 * This file is generated by jOOQ.
 */
package db.tables.records;


import db.tables.Nomenclature;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


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
public class NomenclatureRecord extends UpdatableRecordImpl<NomenclatureRecord> implements Record3<Integer, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.Nomenclature.nomenclature_id</code>.
     */
    public NomenclatureRecord setNomenclatureId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>public.Nomenclature.nomenclature_id</code>.
     */
    public Integer getNomenclatureId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.Nomenclature.name</code>.
     */
    public NomenclatureRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>public.Nomenclature.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.Nomenclature.serial_number</code>.
     */
    public NomenclatureRecord setSerialNumber(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>public.Nomenclature.serial_number</code>.
     */
    public String getSerialNumber() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Nomenclature.NOMENCLATURE.NOMENCLATURE_ID;
    }

    @Override
    public Field<String> field2() {
        return Nomenclature.NOMENCLATURE.NAME;
    }

    @Override
    public Field<String> field3() {
        return Nomenclature.NOMENCLATURE.SERIAL_NUMBER;
    }

    @Override
    public Integer component1() {
        return getNomenclatureId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getSerialNumber();
    }

    @Override
    public Integer value1() {
        return getNomenclatureId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getSerialNumber();
    }

    @Override
    public NomenclatureRecord value1(Integer value) {
        setNomenclatureId(value);
        return this;
    }

    @Override
    public NomenclatureRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public NomenclatureRecord value3(String value) {
        setSerialNumber(value);
        return this;
    }

    @Override
    public NomenclatureRecord values(Integer value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached NomenclatureRecord
     */
    public NomenclatureRecord() {
        super(Nomenclature.NOMENCLATURE);
    }

    /**
     * Create a detached, initialised NomenclatureRecord
     */
    public NomenclatureRecord(Integer nomenclatureId, String name, String serialNumber) {
        super(Nomenclature.NOMENCLATURE);

        setNomenclatureId(nomenclatureId);
        setName(name);
        setSerialNumber(serialNumber);
    }
}
