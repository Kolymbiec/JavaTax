package ua.training.model.dao.mysql.sql_queries;

public enum UserSQL {

    SELECT_ALL_FROM_USER("SELECT * FROM user "),
    INSERT_USER("INSERT INTO user(login, password) " +
                             " VALUES (?,?)"),
    UPDATE_USER("UPDATE user SET user.login = ?, user.password = ? " +
                             "WHERE user.id = ?"),
    DELETE_USER("DELETE FROM user WHERE user.id = ?");

    String QUERY;

    UserSQL(String QUERY){
        this.QUERY = QUERY;
    }

    public String getQUERY() {
        return QUERY;
    }
}
