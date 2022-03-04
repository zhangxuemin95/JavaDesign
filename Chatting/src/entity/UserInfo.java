package entity;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private String accountnum = "";
    private String pwd = "";
    private String name = "";
    private int age = 0;
    private String address = "";

    public void setAccountnum(String accountnum) {
        this.accountnum = accountnum;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private int type = 0;//普通用户是0, 管理员是1
    private int isonline = 0;//0表示下线,1表示在线

    public void setIsonline(int isonline) {
        this.isonline = isonline;
    }

    public int getIsonline() {
        return isonline;
    }

    public UserInfo(String accountnum, String pwd) {
        this.accountnum = accountnum;
        this.pwd = pwd;
    }

    public UserInfo(String accountnum, String pwd, String name, int age, String address) {
        this.accountnum = accountnum;
        this.pwd = pwd;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getAccountnum() {
        return accountnum;
    }

    public String getPwd() {
        return pwd;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
