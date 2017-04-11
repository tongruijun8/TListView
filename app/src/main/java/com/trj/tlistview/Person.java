package com.trj.tlistview;

import java.io.Serializable;
/**
 * 作者： 小童
 * 创建时间： 2017/4/11
 *
 */
public class Person implements Serializable {

    private String name;
    private int age;
    private String address;
    private boolean sex;

    public Person() {
    }

    public Person(String name, int age, String address, boolean sex) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "姓名：" + name + "\n"+
                "年龄：" + age + "\n"+
                "地址：" + address + "\n"+
                "性别：" + (sex==true?"男":"女");
    }
}
