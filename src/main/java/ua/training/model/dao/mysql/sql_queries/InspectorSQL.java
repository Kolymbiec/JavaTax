package ua.training.model.dao.mysql.sql_queries;

public enum InspectorSQL {

    SELECT_ALL_FROM_INSPECTOR("SELECT * FROM inspector "),
    SELECT_BY_USER("SELECT * FROM inspector WHERE inspector.user_inspector_id IN (SELECT user.id FROM user WHERE user.login = "),
    INSERT_INSPECTOR("INSERT INTO inspector( user_inspector_id, name, surname, email) " +
            " VALUES (?,?,?,?)"),
    UPDATE_INSPECTOR("UPDATE inspector SET inspector.user_inspector_id = ?, inspector.name = ?, inspector.surname = ?, inspector.email = ? " +
            "WHERE inspector.id = ?"),
    DELETE_INSPECTOR("DELETE FROM inspector WHERE inspector.id = ?");

    String QUERY;

    InspectorSQL(String QUERY){
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}
