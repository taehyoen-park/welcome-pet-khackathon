package org.taehyeon.welcome_pet_khackathon.Auth;

public class UserAccount {
    private String pw,name,email,phone,check,Idtoken,job;

    public UserAccount(){ }


    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


    public String getCheck() {
        return check;
    }
    public void setCheck(String check) {
        this.check = check;
    }

    public String getPw() {
        return pw;
    }
    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdtoken() {
        return Idtoken;
    }
    public void setIdtoken(String idtoken) {
        this.Idtoken = Idtoken;
    }


}
