package com.example.demo.test;

import java.util.ArrayList;
import java.util.Map;

/**
 * var 关键字，自动根据类型判断，编译后还是其他类型
 * 不同类型间不可以互相赋值。会报不兼容的类型，无法通过编译
 * 用在结合等中存放不同类型数据时，编译后为Object
 * 可以用来省略泛型
 * 但如果在new时定义了泛型类型，则不能通过编译
 * 如果是数组也不必有[]
 */
public class TestVar {

    public static void main(String[] args) {
        var a = 10;
        var b = 15;
//        a = "23";  //错误示范
        System.out.println(a+b);
        var list = new ArrayList<>();
        list.add(123);
        list.add("34234");
        var list2 = new ArrayList<Map<String,Integer>>();
//        list2.add(123); //错误
//        list2.add("34234"); //错误
    }


}
