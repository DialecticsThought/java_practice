package com.atguigu.java1;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

/**
 * @author shkstart
 * @create 2019 上午 11:56
 */
@Inherited
/*
6.1 可重复注解：① 在MyAnnotation上声明@Repeatable，成员值为MyAnnotations.class
              ② MyAnnotation的Target和Retention等元注解与MyAnnotations相同。
 */
//表明该注解可重复 里面写myannotations 通过myannotations实现可重复，
// 但是这两个注解的生命周期要相同@Retention(RetentionPolicy.RUNTIME)
//所修饰的目标Target也要相同
@Repeatable(MyAnnotations.class)
@Retention(RetentionPolicy.RUNTIME)
//6.2 类型注解：
//        Target中多了两个
//       ElementType.TYPE_PARAMETER 表示该注解能写在类型变量的声明语句中（如：泛型声明）。
//        ElementType.TYPE_USE 表示该注解能写在使用类型的任何语句中。
//        Target:用于指定被修饰的 Annotation 能用于修饰哪些程序元素例如class,type,constructor,method
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE,TYPE_PARAMETER,TYPE_USE})
//注解声明为@inferface
public @interface MyAnnotation {
//Annotation的成员变量用无参方法来声明
    //其方法名和返回值定义了该成员的名字和类型，也叫做配置参数
    //类型只能是八种基本数据类型
    //只有一个参数成员，参数名就是value
    //注解有成员的话在使用注解的时，需要指明成员的值
    //default定义初始值,这样的话使用注解的时候括号里没有value=...也不会报错
    //有多个值的话可以定义成数组String[] value()
    //有一些特殊的Annotation比如Override没有成员参数，这些称之为标记
    String value() default "hello";
}
