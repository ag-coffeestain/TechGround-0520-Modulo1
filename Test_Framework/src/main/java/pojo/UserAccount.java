package pojo;

public class UserAccount {

    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private boolean shouldLogin; //bandera que indica si el usuario deberia o no loguearse

    // si solo se usa para parsear de json a pojo no requiere constructor
    public UserAccount(String _email, String _password, boolean _shouldLogin){
        this.setEmail(_email);
        this.setPassword(_password);
        this.setShouldLogin(_shouldLogin);
    }

    // si solo se usa para parsear de json a pojo no requiere constructor
    public UserAccount(String _email, String _password, boolean _shouldLogin, String firstName, String lastName){
        this.setEmail(_email);
        this.setPassword(_password);
        this.setShouldLogin(_shouldLogin);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isShouldLogin() {
        return shouldLogin;
    }

    public void setShouldLogin(boolean shouldLogin) {
        this.shouldLogin = shouldLogin;
    }

    @Override
    public String toString(){
        return (String.format("Email: %s and password: %s", email,password));
    }
}
