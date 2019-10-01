package ua.training.model.dao.mysql.sql_queries;

public enum  TaxpayerSQL {

    SELECT_ALL_FROM_TAXPAYER("SELECT * FROM taxpayer "),
    SELECT_BY_USER("SELECT * FROM taxpayer WHERE taxpayer.user_id IN (SELECT user.id FROM user WHERE user.login = "),
    INSERT_TAXPAYER("INSERT INTO taxpayer(user_id, taxpayer_type_id, inspector_id, name, surname, email) " +
                             " VALUES (?,?,?,?,?,?)"),
    UPDATE_TAXPAYER("UPDATE taxpayer SET taxpayer.user_id = ?, taxpayer.taxpayer_type_id = ?, taxpayer.inspector_id = ?, taxpayer.name = ?, taxpayer.surname = ?, taxpayer.email = ? " +
                             "WHERE taxpayer.id = ?"),
    DELETE_TAXPAYER("DELETE FROM taxpayer WHERE taxpayer.user_id = ?");

    String QUERY;

    TaxpayerSQL(String QUERY){
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}
