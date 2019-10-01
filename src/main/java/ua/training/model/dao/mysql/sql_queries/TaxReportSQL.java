package ua.training.model.dao.mysql.sql_queries;

public enum  TaxReportSQL {

    SELECT_ALL_FROM_TAXREPORT("SELECT * FROM taxreport "),
    INSERT_TAXREPORT("INSERT INTO taxreport(taxpayer_id, taxpayer_inspector_id, form_id, type_id, status_id, message, date) " +
                            " VALUES (?,?,?,?,?,?,?)"),
    UPDATE_TAXREPORT("UPDATE taxreport SET taxreport.taxpayer_id = ?, taxreport.taxpayer_inspector_id = ?, taxreport.form_id = ?, " +
            "taxreport.type_id = ?, taxreport.status_id = ?, taxreport.message = ?, taxreport.date = ? " +
                            "WHERE taxreport.id = ?"),
    DELETE_TAXREPORT("DELETE FROM taxreport WHERE taxreport.id = ?"),

    SELECT_COUNT_ROWS("SELECT COUNT(*) FROM taxreport");

    String QUERY;

    TaxReportSQL(String QUERY){
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}
