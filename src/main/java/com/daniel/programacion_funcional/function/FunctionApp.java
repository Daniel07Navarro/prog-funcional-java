package com.daniel.programacion_funcional.function;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FunctionApp {

    private void m1Apply(){
        Function<String, Integer> fx = x -> x.length();
        Integer rpta = fx.apply("mitocode");
        System.out.println(rpta);
    }

    private void m2AndThen(){
        Function<String, Integer> fx1 = x -> x.length();
        Function<Integer, Integer> fx2 = x -> x + 10;

        Integer rpta = fx1.andThen(fx2).apply("mitocode");
        System.out.println(rpta);
    }

    private void m2AndThenV2(){
        Function<Integer, Integer> fx1 = x -> x * 2;
        Function<Integer, Integer> fx2 = x -> x + 10;

        Integer rpta = fx1.andThen(fx2).apply(5);
        System.out.println(rpta);
    }

    private void m3Compose(){
        Function<Integer, Integer> fx1 = x -> x * 2;
        Function<Integer, Integer> fx2 = x -> x + 10;

        Integer rpta = fx1.compose(fx2).apply(5);
        System.out.println(rpta);
    }

    private void m4Identity(){
        //f(x) = x;
        Function<Employee, Employee> fx = Function.identity();
        System.out.println(fx.apply(new Employee()));
    }

    private void m5Identity(){
        List<Person> list = new ArrayList<>();
        list.add(new Person(1, "Mito", 31));
        list.add(new Person(2, "Code", 32));
        list.add(new Person(3, "Jaime", 33));

        Map<Integer, Person> map = list
                .stream()
                .collect(Collectors.toMap(Person::getId, Function.identity())); //e -> e.getId()
        System.out.println(map);
    }

    private void m6Identity(){
        Function<Integer, Integer> f1 = Function.identity();
        Function<Integer, Integer> f2 = Function.identity();
        Function<Integer, Integer> f3 = Function.identity();

        Function<Integer, Integer> f4 = t -> t;
        Function<Integer, Integer> f5 = t -> t;
        Function<Integer, Integer> f6 = t -> t;

        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f3);
        System.out.println("====================================");
        System.out.println(f4);
        System.out.println(f5);
        System.out.println(f6);
    }

    public static void main(String[] args) {
        FunctionApp app = new FunctionApp();
        app.m6Identity();
    }
}
