package com.daniel.programacion_funcional.interfaces;

import java.util.function.Predicate;

//boolean test(T t);
public class PredicateApp {

    private void method1(){
        Predicate<Integer> checkAgeNumber = x -> x >= 18;
        boolean rpta = checkAgeNumber.test(32);
        System.out.println(rpta);
    }

    private void method2(){
        Predicate<Integer> greaterThan = x -> x > 10;
        Predicate<Integer> lowerThan = x -> x < 20;

        boolean rpta = greaterThan.and(lowerThan).negate().test(32);
        System.out.println(rpta);
    }

    public void method3(int num, Predicate<Integer> fx){
        boolean rpta = fx.test(num);
        System.out.println(rpta);
    }

    public static void main(String[] args) {
        PredicateApp app = new PredicateApp();
        //app.method2();
        Predicate<Integer> fx1 = x -> x > 5;
        //app.method3(50, fx1);
        app.method3(50, x -> x > 5);
    }
}
