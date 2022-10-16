package org.taehyeon.welcome_pet_khackathon.Auth;

public class UserAccount {
    private String pw,name,email,phone;
    private int check,job;
    private String Idtoken;
    public UserAccount(){ }


    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public int getCheck() {
        return check;
    }
    public void setCheck(int check) {
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
        Idtoken = idtoken;
    }
}
