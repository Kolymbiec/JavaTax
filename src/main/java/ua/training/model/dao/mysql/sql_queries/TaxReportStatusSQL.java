package ua.training.model.dao.mysql.sql_queries;

public enum  TaxReportStatusSQL {

    SELECT_ALL_FROM_TAXREPORT_STATUS("SELECT * FROM taxreport_status "),
    INSERT_TAXREPORT_STATUS("INSERT INTO taxreport_status(status) " +
                                  " VALUES (?"),
    UPDATE_TAXREPORT_STATUS("UPDATE taxreport_status SET taxreport_status.status = ?" +
                                  "WHERE taxreport_status.id = ?"),
    DELETE_TAXREPORT_STATUS("DELETE FROM taxreport_status WHERE taxreport_status.id = ?");

    String QUERY;

    TaxReportStatusSQL(String QUERY){
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}
