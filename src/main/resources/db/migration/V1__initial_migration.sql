CREATE TABLE "Waybill" (
    "id" serial NOT NULL,
    "date" DATE NOT NULL,
    "organization_id" int NOT NULL,
    CONSTRAINT "Waybill_pk" PRIMARY KEY ("id")
) WITH (
    OIDS=FALSE
);

CREATE TABLE "Waybill_items" (
    "item_id" serial NOT NULL,
    "waybill_id" int NOT NULL,
    "price" money NOT NULL,
    "quantity" int NOT NULL,
    "nomenclature" int NOT NULL,
    CONSTRAINT "Waybill_items_pk" PRIMARY KEY ("item_id")
) WITH (
    OIDS=FALSE
);

CREATE TABLE "Organization" (
    "id" serial NOT NULL,
    "name" varchar(255) NOT NULL,
    "inn" varchar(20) NOT NULL,
    "checking_acc" varchar(20) NOT NULL,
    CONSTRAINT "Organization_pk" PRIMARY KEY ("id")
) WITH (
    OIDS=FALSE
);

CREATE TABLE "Nomenclature" (
    "nomenclature_id" int NOT NULL,
    "name" varchar(55) NOT NULL,
    "serial_number" varchar(255) NOT NULL,
    CONSTRAINT "Nomenclature_pk" PRIMARY KEY ("nomenclature_id")
) WITH (
    OIDS=FALSE
);

ALTER TABLE "Waybill" ADD CONSTRAINT "Waybill_fk0" FOREIGN KEY ("organization_id") REFERENCES "Organization"("id");

ALTER TABLE "Waybill_items" ADD CONSTRAINT "Waybill_items_fk0" FOREIGN KEY ("waybill_id") REFERENCES "Waybill"("id");

ALTER TABLE "Waybill_items" ADD CONSTRAINT "Waybill_items_fk1" FOREIGN KEY ("nomenclature") REFERENCES "Nomenclature"("nomenclature_id");
