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

    /* ================================= */
    /* FULLY DYNAMIC INFO IN THE SESSION */
    /* ================================= */

    /**
     * The content of the screen.
     */
    private String contentHtml = "";

    /**
     * The state of the screen: 0 = no game selected; 1 = game selected but no gameplay selected; 2 = gameplay selected
     * and users are being displayed; user can select a user record number for details.
     */
    private int state = 0;
    
    /** the selected game id. */
    private int selectedGameId = 0;
    
    /** the selected gamePlay. */
    private int selectedGamePlayId = 0;
    
    /** the selected gameUser. */
    private int selectedGameUserId = 0;
    
    /**
     * when 0, do not show popup; when 1: show popup. <br>
     * filled and updated by RoundServlet.
     */
    private int showModalWindow = 0;

    /**
     * client info (dynamic) for popup.
     */
    private String modalWindowHtml = "";

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

    public String getModalWindowHtml() {
        return modalWindowHtml;
    }

    public void setModalWindowHtml(String modalClientWindowHtml) {
        this.modalWindowHtml = modalClientWindowHtml;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public int getShowModalWindow() {
        return showModalWindow;
    }

    public void setShowModalWindow(int showModalWindow) {
        this.showModalWindow = showModalWindow;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getSelectedGameId() {
        return selectedGameId;
    }

    public void setSelectedGameId(int selectedGameId) {
        this.selectedGameId = selectedGameId;
    }

    public int getSelectedGamePlayId() {
        return selectedGamePlayId;
    }

    public void setSelectedGamePlayId(int selectedGamePlay) {
        this.selectedGamePlayId = selectedGamePlay;
    }

    public int getSelectedGameUserId() {
        return selectedGameUserId;
    }

    public void setSelectedGameUserId(int selectedGameUser) {
        this.selectedGameUserId = selectedGameUser;
    }

}
