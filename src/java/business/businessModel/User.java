package business.businessModel;

import java.io.Serializable;

/**
 *
 * @author david
 */
public class User implements Serializable {

    private SecurityGroup securityGroup;
    private String userID;
    private String username;
    private String password;

    public User() {
        userID = "";
        username = "";
        password = "";
        securityGroup = SecurityGroup.None; 
    }

    public SecurityGroup getSecurityGroup() {
        return securityGroup;
    }

    public void setSecurityGroup(SecurityGroup securityGroup) {
        this.securityGroup = securityGroup;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
