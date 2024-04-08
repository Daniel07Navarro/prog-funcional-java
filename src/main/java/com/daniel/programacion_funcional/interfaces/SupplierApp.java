package com.daniel.programacion_funcional.interfaces;

import java.time.LocalDateTime;
import java.util.function.Supplier;

// T get();
public class SupplierApp{

    private void method1(){
        Supplier<LocalDateTime> fx = () -> LocalDateTime.now().minusDays(2);
        System.out.println(fx.get());
    }

    private void metodo2(){
        Supplier<String> f = () -> "alicate";
    }
    public static void main(String[] args) {
        SupplierApp app = new SupplierApp();
        app.method1();
    }


}
