/*
 * This file is generated by jOOQ.
 */
package db.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


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
public class Nomenclature implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer nomenclatureId;
    private final String  name;
    private final String  serialNumber;

    public Nomenclature(Nomenclature value) {
        this.nomenclatureId = value.nomenclatureId;
        this.name = value.name;
        this.serialNumber = value.serialNumber;
    }

    public Nomenclature(
        Integer nomenclatureId,
        String  name,
        String  serialNumber
    ) {
        this.nomenclatureId = nomenclatureId;
        this.name = name;
        this.serialNumber = serialNumber;
    }

    /**
     * Getter for <code>public.Nomenclature.nomenclature_id</code>.
     */
    public Integer getNomenclatureId() {
        return this.nomenclatureId;
    }

    /**
     * Getter for <code>public.Nomenclature.name</code>.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for <code>public.Nomenclature.serial_number</code>.
     */
    public String getSerialNumber() {
        return this.serialNumber;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Nomenclature (");

        sb.append(nomenclatureId);
        sb.append(", ").append(name);
        sb.append(", ").append(serialNumber);

        sb.append(")");
        return sb.toString();
    }
}
