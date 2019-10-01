package ua.training.model.dao.mysql.sql_queries;

public enum  TaxReportFormSQL {

    SELECT_ALL_FROM_TAXREPORT_FORM("SELECT * FROM taxreport_form "),
    INSERT_TAXREPORT_FORM("INSERT INTO taxreport_form(form) " +
                                 " VALUES (?"),
    UPDATE_TAXREPORT_FORM("UPDATE taxreport_form SET taxreport_form.form = ?" +
                                 "WHERE taxreport_form.id = ?"),
    DELETE_TAXREPORT_FORM("DELETE FROM taxreport_form WHERE taxreport_form.id = ?");


    String QUERY;

    TaxReportFormSQL(String QUERY){
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}
