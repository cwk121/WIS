package com.example.wis.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter @Setter
@IdClass(Inventory.InventoryPK.class)
public class Inventory implements Serializable {
    @Id
    private String location;
    @Id
    private String productCode;
    private Integer quantity;

    public Inventory() {}
    public Inventory(String location, String productCode, Integer quantity) {
        this.location = location;
        this.productCode = productCode;
        this.quantity = quantity;
    }

    public static class InventoryPK implements Serializable {
        protected String location;
        protected String productCode;

        public InventoryPK() {}
        public InventoryPK(String location, String productCode) {
            this.location = location;
            this.productCode = productCode;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Inventory inventory = (Inventory) o;
        return Objects.equals(location, inventory.location) && Objects.equals(productCode, inventory.productCode) && Objects.equals(quantity, inventory.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, productCode, quantity);
    }
}
