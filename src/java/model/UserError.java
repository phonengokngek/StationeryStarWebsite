package model;

public class UserError {

    private String fullNameError = "";
    private String emailError = "";
    private String passwordError = "";
    private String confirmError = "";
    private String phoneError = "";
    private String error = "";

    public UserError() {
    }

    public UserError(String fullNameError, String emailError, String passwordError, String confirmError, String phoneError, String error) {
        this.fullNameError = fullNameError;
        this.emailError = emailError;
        this.phoneError = phoneError;
        this.passwordError = passwordError;
        this.confirmError = confirmError;
        this.error = error;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getConfirmError() {
        return confirmError;
    }

    public void setConfirmError(String confirmError) {
        this.confirmError = confirmError;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    @Override
    public String toString() {
        return "UserError{" + "fullNameError=" + fullNameError + ", emailError=" + emailError + ", passwordError=" + passwordError + ", confirmError=" + confirmError + ", phoneError=" + phoneError + ", error=" + error + '}';
    }

    

}
