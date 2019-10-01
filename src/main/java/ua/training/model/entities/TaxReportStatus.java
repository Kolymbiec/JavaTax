package ua.training.model.entities;

import java.util.Objects;

public class TaxReportStatus {

    private int id;
    private String status;

    public TaxReportStatus(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxReportStatus status1 = (TaxReportStatus) o;
        return id == status1.id &&
                status.equals(status1.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }

    @Override
    public String toString() {
        return "TaxReportStatus{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }
}
