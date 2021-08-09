# project_exercise
java的一些基础项目练习
# 点菜系统开发

## 系统分析

点菜系统，首先需要实现菜单的显示、对菜品进行点单以及最后的结账操作。

其中主要包含菜品类，以及功能实现类。

(当然，其中也可以包含顾客类等等，但是本系统开发设置仅包含菜品类以及功能实现类，最后实现的功能为在控制台操作点单)

## 菜品类创建

`点菜系统菜品类Dish`

```java
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
```

菜品类中仅包含菜品编号，菜品名称，以及菜品价格3个变量，然后生成有参的构造方法。

注：因为使用软件为IDEA，因此可以使用快捷键Alt+insert更快的生成构造方法。

## 功能实现（重点）

### 第一步 创建main方法

首先创建main方法，然后根据需要实现的功能进行扩充。

在该方法中，需要对所有的菜品进行展示，然后让用户根据提示进行点单，然后能够显示点好后的菜品，最后，得出所有菜品的总价。

### 第二步 菜品初始化

因为需要对饭店中的所有菜品进行展示，所以需要对菜品进行初始化。在此过程中，需要先创建一个集合对所有的菜品进行存放。

`定义集合`static List<Dish> dishList = new ArrayList<>();

在菜品初始化过程中，创建新的菜品对象，然后将对象添加到集合中。有如下的两种方法：

`菜品初始化`

```java
Dish dish2 = new Dish(2,"香菇炒肉",29.0);
dishList.add(dish2);

dishList.add(new Dish(3,"凉拌肉丝",20.0));
```

### 第三步 主菜单展示

对主菜单进行展示，让顾客能够根据主菜单选择相关的操作，实现结果如下所示:

----主菜单----
菜单			 1
已点菜品		 2
买单			 3
--请根据编号选择服务--

### 第四步 菜单方法实现

根据主菜单，分别创建菜单、已点菜单、买单三个方法来对功能进行实现。

菜单：主要通过遍历初始化菜品后得到的集合，进行输出显示后实现。

已点菜单：通过遍历已点菜品后得到的集合，进行输出显示后实现。

买单：通过遍历已点菜品后得到的集合，然后将菜品价格相加后输出显示实现。

### 第五步 点单操作（main方法中实现）

在main方法中，通过创建扫描器对象，获取控制台输入的内容，然后根据输出内容创建while循环语句，展示菜单。使用switch…case…语句、if语句进行条件判断等操作来对用户的选择进行进一步的操作。

### 功能实现类总程序

`点菜主程序`

```java
package com.atweiranxi.demo;

import java.net.SocketOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 蔚然溪
 *
 * 点菜主程序
 */
public class DishApp {

    // 提前准备一些可以展示给用户点菜的菜品
    // 定义集合（表示饭店拥有的菜品） 泛型
    static List<Dish> dishList = new ArrayList<>();
    static List<Dish> personDish = new ArrayList<>();

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
```

## 总结

运行程序后得出的结果为

----主菜单----
菜单			 1
已点菜品		 2
买单			 3
--请根据编号选择服务--
1
----请点菜----
1	鱼香肉丝	29.0
2	香菇炒肉	29.0
3	凉拌肉丝	20.0
4	炒生菜	15.0
5	麻婆豆腐	20.0
----输入序号点菜，按0返回上一级----
2
亲，您点了香菇炒肉菜
----请点菜----
1	鱼香肉丝	29.0
2	香菇炒肉	29.0
3	凉拌肉丝	20.0
4	炒生菜	15.0
5	麻婆豆腐	20.0
----输入序号点菜，按0返回上一级----
3
亲，您点了凉拌肉丝菜
----请点菜----
1	鱼香肉丝	29.0
2	香菇炒肉	29.0
3	凉拌肉丝	20.0
4	炒生菜	15.0
5	麻婆豆腐	20.0
----输入序号点菜，按0返回上一级----
4
亲，您点了炒生菜菜
----请点菜----
1	鱼香肉丝	29.0
2	香菇炒肉	29.0
3	凉拌肉丝	20.0
4	炒生菜	15.0
5	麻婆豆腐	20.0
----输入序号点菜，按0返回上一级----
0
----您已点菜品----
2	香菇炒肉	29.0
3	凉拌肉丝	20.0
4	炒生菜	15.0
----主菜单----
菜单			 1
已点菜品		 2
买单			 3
--请根据编号选择服务--
2
----您已点菜品----
2	香菇炒肉	29.0
3	凉拌肉丝	20.0
4	炒生菜	15.0
----主菜单----
菜单			 1
已点菜品		 2
买单			 3
--请根据编号选择服务--
1
----请点菜----
1	鱼香肉丝	29.0
2	香菇炒肉	29.0
3	凉拌肉丝	20.0
4	炒生菜	15.0
5	麻婆豆腐	20.0
----输入序号点菜，按0返回上一级----
1
亲，您点了鱼香肉丝菜
----请点菜----
1	鱼香肉丝	29.0
2	香菇炒肉	29.0
3	凉拌肉丝	20.0
4	炒生菜	15.0
5	麻婆豆腐	20.0
----输入序号点菜，按0返回上一级----
0
----您已点菜品----
2	香菇炒肉	29.0
3	凉拌肉丝	20.0
4	炒生菜	15.0
1	鱼香肉丝	29.0
----主菜单----
菜单			 1
已点菜品		 2
买单			 3
--请根据编号选择服务--
2
----您已点菜品----
2	香菇炒肉	29.0
3	凉拌肉丝	20.0
4	炒生菜	15.0
1	鱼香肉丝	29.0
----主菜单----
菜单			 1
已点菜品		 2
买单			 3
--请根据编号选择服务--
3
----请稍等，正在结算中----
亲，您本次共消费了：93.0 元

Process finished with exit code 0

该点菜系统的开发实现的功能为在控制台进行菜品的点单，结算等操作。主要对Java的while循环、switch语句、for循环、集合等知识进行了巩固。

该系统也可以进行进一步的优化

1.对菜品种类以及主菜单进行优化（丰富菜品与主菜单内容，增加更多的方法）

2.增加用户类，对使用者进行区分，可以据此再增设会员等级别
