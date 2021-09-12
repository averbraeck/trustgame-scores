package org.transsonic.trustgame.scores;

import javax.sql.DataSource;

import org.transsonic.trustgame.data.trustgame.tables.records.UserRecord;

public class ScoreData {

    /**
     * the SQL datasource representing the database's connection pool.<br>
     * the datasource is shared among the servlets and stored as a ServletContext attribute.
     */
    private DataSource dataSource;

    /**
     * the name of the admin user logged in to this session. <br>
     * if null, no user is logged in.<br>
     * filled by the UserLoginServlet.<br>
     * used by: server and in servlet.
     */
    private String username;

    /**
     * the id of the admin user logged in to this session.<br>
     * if null, no user is logged in.<br>
     * filled by the UserLoginServlet.<br>
     * used by: server.
     */
    private Integer userId;

    /**
     * the admin User record for the logged in user.<br>
     * this record has the USERNAME to display on the screen.<br>
     * filled by the UserLoginServlet.<br>
     * used by: server and in servlet.<br>
     */
    private UserRecord user;

    private String contentHtml = "";

    /* ******************* */
    /* GETTERS AND SETTERS */
    /* ******************* */

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserRecord getUser() {
        return user;
    }

    public void setUser(UserRecord user) {
        this.user = user;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

}
