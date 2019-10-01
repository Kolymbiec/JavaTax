package ua.training.model.dao.mysql.sql_queries;

public enum  TaxpayerTypeSQL {

    SELECT_ALL_FROM_TAXPAYER_TYPE("SELECT * FROM taxpayer_type "),
    INSERT_TAXPAYER_TYPE("INSERT INTO taxpayer_type(type) " +
                            " VALUES (?"),
    UPDATE_TAXPAYER_TYPE("UPDATE taxpayer_type SET taxpayer_type.type = ?" +
                            "WHERE taxpayer_type.id = ?"),
    DELETE_TAXPAYER_TYPE("DELETE FROM taxpayer_type WHERE taxpayer_type.id = ?");

    String QUERY;

    TaxpayerTypeSQL(String QUERY){
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}
