package com.daniel.programacion_funcional.interfaces;

import java.util.List;
import java.util.function.Consumer;

//void accept(T t);
public class ConsumerApp {

    private void method1(){
        Consumer<String> print = x -> System.out.println(x);
        Consumer<Integer> fx = x -> {
            x = x + 5;
            System.out.println(x);
        };
        Consumer<Integer> fx2 = x -> System.out.println(x + 5);
        print.accept("Hola Coders");
        fx2.accept(10);
    }

    private void method2(){
        List<Integer> list = List.of(1,2,3,4,5,6,7,8,9,10);

        Consumer<Integer> consumer = x -> {
            x++;
            System.out.println(x);
        };
        listAll(list, consumer);
    }

    //Funciones de Alto de Orden - High Order Functions
    private void listAll(List<Integer> list, Consumer<Integer> fx){
        for(Integer i: list){
            fx.accept(i);
        }
        //list.forEach(fx);
    }

    private void metodoPrueba(){
        List<Integer> list = List.of(1,2,3,4,5,6,7,8,9,10);
        Consumer<Integer> consumer = e -> {
            e++;
            System.out.println(e);
        };
        list.forEach(consumer);
    }

    public static void main(String[] args) {
        ConsumerApp app = new ConsumerApp();
        app.metodoPrueba();
    }
}
