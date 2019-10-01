package ua.training.model.entities;

import java.sql.Timestamp;
import java.util.Objects;

public class TaxReport {

    private int id;
    private Taxpayer taxpayer;
    private Inspector inspector;
    private TaxReportForm form;
    private TaxReportType type;
    private TaxReportStatus status;
    private String message;
    private Timestamp date;

    public TaxReport(){}

    public TaxReport(int id, Taxpayer taxpayer, Inspector inspector, TaxReportForm form, TaxReportType type, TaxReportStatus status, String message, Timestamp date) {
        this.id = id;
        this.taxpayer = taxpayer;
        this.inspector = inspector;
        this.form = form;
        this.type = type;
        this.status = status;
        this.message = message;
        this.date = date;
    }

    public TaxReport(Taxpayer taxpayer, Inspector inspector, TaxReportForm form, TaxReportType type, TaxReportStatus status, String message, Timestamp date) {
        this.taxpayer = taxpayer;
        this.inspector = inspector;
        this.form = form;
        this.type = type;
        this.status = status;
        this.message = message;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Taxpayer getTaxpayer() {
        return taxpayer;
    }

    public void setTaxpayer(Taxpayer taxpayer) {
        this.taxpayer = taxpayer;
    }

    public Inspector getInspector() {
        return inspector;
    }

    public void setInspector(Inspector inspector) {
        this.inspector = inspector;
    }

    public TaxReportForm getForm() {
        return form;
    }

    public void setForm(TaxReportForm form) {
        this.form = form;
    }

    public TaxReportType getType() {
        return type;
    }

    public void setType(TaxReportType type) {
        this.type = type;
    }

    public TaxReportStatus getStatus() {
        return status;
    }

    public void setStatus(TaxReportStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxReport taxReport = (TaxReport) o;
        return id == taxReport.id &&
                taxpayer.equals(taxReport.taxpayer) &&
                inspector.equals(taxReport.inspector) &&
                form.equals(taxReport.form) &&
                type.equals(taxReport.type) &&
                status.equals(taxReport.status) &&
                Objects.equals(message, taxReport.message) &&
                date.equals(taxReport.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taxpayer, inspector, form, type, status, message, date);
    }

    @Override
    public String toString() {
        return "TaxReport{" +
                "id=" + id +
                ", taxpayer=" + taxpayer +
                ", inspector=" + inspector +
                ", form=" + form +
                ", type=" + type +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }
}
