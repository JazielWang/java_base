package com.ithiema_05;

import java.util.ArrayList;
import java.util.stream.Stream;

/*
    static <T> Stream<T> concat​(Stream a, Stream b)：合并a和b两个流为一个流
    Stream<T> distinct​()：返回由该流的不同元素（根据Object.equals(Object) ）组成的流
 */
public class StreamDemo04 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("林青霞");
        list.add("张曼玉");
        list.add("王祖贤");
        list.add("柳岩");
        list.add("张敏");
        list.add("张无忌");

        Stream<String> s1 = list.stream().limit(4);
        Stream<String> s2 = list.stream().skip(2);

        //需求3：合并需求1和需求2得到的流，并把结果在控制台输出
//        Stream.concat(s1,s2).forEach(System.out::println);
        //需求4：合并需求1和需求2得到的流，并把结果在控制台输出，要求字符串元素不能重复
        Stream.concat(s1, s2).distinct().forEach(System.out::println);
    }
}