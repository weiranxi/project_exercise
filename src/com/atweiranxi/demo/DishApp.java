package com.atweiranxi.demo;

import java.net.SocketOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 蔚然
 *
 * 点菜主程序
 */
public class DishApp {

    // 提前准备一些可以展示给用户点菜的菜品
    // 定义集合（表示饭店拥有的菜品） 泛型
    static List<Dish> dishList = new ArrayList<>();
    static List<Dish> personDish = new ArrayList<>();1

    public static void main(String[] args) {
        // 先初始化菜品
        initDish();
        // 假设饭店就会这几个菜

        // 创建扫描器对象，获取控制台输入的内容
        Scanner s = new Scanner(System.in);

        //写循环，让主菜单能够反复显示
        while (true){
            //给用户展示主菜单
            showMenu();
            //获取内容
            int num = s.nextInt();
            // 判断用户输入的数字，并做出对应的操作
            switch (num){
                case 1:
                    while (true){
                        // 展示饭店的菜单
                        showDishMenu();
                        // 获取用户输入的内容
                        int id = s.nextInt();
                        //判断
                        if (id == 0){
                            break;
                        }

                        //从饭店的集合中获取菜品的对象 1 2 3 4 5（集合索引：0 1 2 3 4）
                        Dish dish = dishList.get(id-1);
                        System.out.println("亲，您点了"+dish.name+"菜");
                        // 用户点一个菜，存储一个
                        personDish.add(dish);
                    }
                case 2:
                    // 展示用户已经点的菜品
                    showPersonDish();
                    break;
                case 3:
                    //买单
                    buy();
                    return;
            }
        }

    }

    /**
     * 展示菜单
     */
    public static void showDishMenu(){
        System.out.println("----请点菜----");
        //遍历集合
        for (int i = 0; i < dishList.size(); i++){
            //从集合中使用 i 获取每一个菜品对象
            Dish dish = dishList.get(i);
            System.out.println(dish.id+"\t"+dish.name+"\t"+dish.price);
        }
        System.out.println("----输入序号点菜，按0返回上一级----");
    }

    /**
     * 展示已点菜品
     */
    public static void showPersonDish(){
        System.out.println("----您已点菜品----");
        for (Dish dish : personDish){
            System.out.println(dish.id+"\t"+dish.name+"\t"+dish.price);
        }
    }

    /**
     * 买单
     */
    public static void buy(){
        System.out.println("----请稍等，正在结算中----");
        //定义总金额
        double total = 0f;
        // 遍历已经点的菜品
        for (Dish dish : personDish){
            // 单价累加
            total += dish.price;
        }
        System.out.println("亲，您本次共消费了：" +total +" 元");
    }
    /**
     * 展示主菜单
     */
    public static void showMenu(){
        System.out.println("----主菜单----");
        System.out.println("菜单\t\t\t 1");
        System.out.println("已点菜品\t\t 2");
        System.out.println("买单\t\t\t 3");
        System.out.println("--请根据编号选择服务--");

    }
    /**
     * 初始化菜品
     */
    public static void initDish() {
        Dish dish1 = new Dish(1,"鱼香肉丝",29.0);
        dishList.add(dish1);
        Dish dish2 = new Dish(2,"香菇炒肉",29.0);
        dishList.add(dish2);

        dishList.add(new Dish(3,"凉拌肉丝",20.0));
        dishList.add(new Dish(4,"炒生菜",15.0));
        dishList.add(new Dish(5,"麻婆豆腐",20.0));


    }
}
