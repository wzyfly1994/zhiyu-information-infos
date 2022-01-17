package com.zhiyu.common.map;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Objects;

/**
 * @author wengzhiyu
 * @since 2022/1/5 15:50
 */
//@Data
//@EqualsAndHashCode
public class People {

    private Integer age;

    private String name;

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "People{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        People people = (People) o;
//        return Objects.equals(age, people.age) && Objects.equals(name, people.name);
//    }
//
    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }
}
