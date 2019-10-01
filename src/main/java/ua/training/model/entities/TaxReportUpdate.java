package ua.training.model.entities;

import java.util.ArrayList;
import java.util.Objects;

public class TaxReportUpdate {

    private int id;
    private ArrayList<TaxReport> reports;

    public TaxReportUpdate(int id, ArrayList<TaxReport> reports) {
        this.id = id;
        this.reports = reports;
    }

    public TaxReportUpdate(ArrayList<TaxReport> reports) {
        this.reports = reports;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<TaxReport> getReports() {
        return reports;
    }

    public void setReports(ArrayList<TaxReport> reports) {
        this.reports = reports;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxReportUpdate that = (TaxReportUpdate) o;
        return id == that.id &&
                Objects.equals(reports, that.reports);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reports);
    }

    @Override
    public String toString() {
        return "TaxReportUpdate{" +
                "id=" + id +
                ", reports=" + reports +
                '}';
    }
}
