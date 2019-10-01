package ua.training.model.dao.mysql.sql_queries;

public enum  TaxReportTypeSQL {

    SELECT_ALL_FROM_TAXREPORT_TYPE("SELECT * FROM taxreport_type "),
    INSERT_TAXREPORT_TYPE("INSERT INTO taxreport_type(type_number, type_name) " +
            " VALUES (?, ?"),
    UPDATE_TAXREPORT_TYPE("UPDATE taxreport_type SET taxreport_type.type_number = ?, taxreport_type.type_name = ?" +
            "WHERE taxreport_type.id = ?"),
    DELETE_TAXREPORT_TYPE("DELETE FROM taxreport_type WHERE taxreport_type.id = ?");


    String QUERY;

    TaxReportTypeSQL(String QUERY){
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}
