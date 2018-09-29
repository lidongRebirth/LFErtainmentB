package com.zlz.app.lfertainmentb.activitys.interestlable.bean;

public class Bean {
    int type;           //100标题200内容
    String name;        //
    int id;             //
    int mid;            //所属标题id    无为0
    Boolean isSelected; //被选中        标题默认false

    public Bean() {
    }

    public Bean(int type, String name, int id, int mid, Boolean isSelected) {
        this.type = type;
        this.name = name;
        this.id = id;
        this.mid = mid;
        this.isSelected = isSelected;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }



}
