package entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Sendor implements Serializable {
        private int type;//0等录验证1加好友验证2查询聊天记录3删除聊天记录4备份聊天记录5注册6下线信息7查找好友8聊天
        private UserInfo userInfo;
        private String friendNum;
        private String keyword;
        private ChattingRecord chatting;

    public int getChattingType() {
        return chattingType;
    }

    public void setChattingType(int chattingType) {
        this.chattingType = chattingType;
    }

    private int chattingType; //0 sender->accepter;1 accepter->sender
        private int result;
        private ArrayList arrayList;

    public String getFriendNum() {
        return friendNum;
    }

    public void setFriendNum(String friendNum) {
        this.friendNum = friendNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public ArrayList getArrayList() {
        return arrayList;
    }

    public void setArrayList(ArrayList arrayList) {
        this.arrayList = arrayList;
    }

    public ChattingRecord getChatting() {
        return chatting;
    }

    public void setChatting(ChattingRecord chatting) {
        this.chatting = chatting;
    }
}
