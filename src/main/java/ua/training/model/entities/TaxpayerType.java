package ua.training.model.entities;

import java.util.Objects;

public class TaxpayerType {

    private int id;
    private String type;

    public TaxpayerType(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxpayerType that = (TaxpayerType) o;
        return id == that.id &&
                type.equals(that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "TaxpayerType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
