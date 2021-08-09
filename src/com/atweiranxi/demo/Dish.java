package com.atweiranxi.demo;

/**
 * 蔚然溪
 *
 * 菜品类
 * 提供有参数的构造方法
 */

public class Dish {

    //编号
    int id;
    //菜品名称
    String name;
    //价格
    double price;

    //生成构造方法(快捷键Alt+insert)
    public Dish(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

}
