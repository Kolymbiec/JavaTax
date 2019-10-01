package ua.training.model.entities;

import java.util.Objects;

public class TaxReportForm {

    private int id;
    private String form;

    public TaxReportForm(int id, String form) {
        this.id = id;
        this.form = form;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxReportForm that = (TaxReportForm) o;
        return id == that.id &&
                form.equals(that.form);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, form);
    }

    @Override
    public String toString() {
        return "TaxReportForm{" +
                "id=" + id +
                ", form='" + form + '\'' +
                '}';
    }
}
