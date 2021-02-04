package com.example.model;

import java.io.Serializable;

/**
 * Created by cuizhixiang on 2017/6/22.
 * fist
 **/
public class User implements Comparable<User>, Serializable {

    private String name;

    private String tel;

    private Integer age;

    public User() {
    }

    public User(String name, String tel, Integer age) {
        this.name = name;
        this.tel = tel;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (tel != null ? !tel.equals(user.tel) : user.tel != null) return false;
        return age != null ? age.equals(user.age) : user.age == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        return result;
    }


    @Override
    public int compareTo(User o) {
        if (this.age > o.age) {
            return this.age - o.age;
        }
        if (this.age > o.age) {
            return this.age - o.age;
        }
        if (this.name.compareTo(o.getName()) > 0) {
            return 1;
        }
        if (this.name.compareTo(o.getName()) < 0) {
            return -1;
        }
        if (this.tel.compareTo(o.getTel()) > 0) {
            return 1;
        }
        if (this.tel.compareTo(o.getTel()) < 0) {
            return -1;
        }
        return 0;
    }
}
