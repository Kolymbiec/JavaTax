package ua.training.model.entities;

import java.util.Objects;

public class TaxReportType {

    private int id;
    private String typeNumber;
    private String typeName;

    public TaxReportType(int id, String typeNumber, String typeName) {
        this.id = id;
        this.typeNumber = typeNumber;
        this.typeName = typeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeNumber() {
        return typeNumber;
    }

    public void setTypeNumber(String typeNumber) {
        this.typeNumber = typeNumber;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxReportType type = (TaxReportType) o;
        return id == type.id &&
                typeNumber.equals(type.typeNumber) &&
                typeName.equals(type.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeNumber, typeName);
    }

    @Override
    public String toString() {
        return "TaxReportType{" +
                "id=" + id +
                ", typeNumber=" + typeNumber +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
