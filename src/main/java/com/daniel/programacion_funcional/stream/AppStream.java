package com.daniel.programacion_funcional.stream;

import com.daniel.programacion_funcional.function.Employee;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

public class AppStream {

    public void m1getDevelopers(List<Employee> lista, String search){
        lista.stream()
                .filter(e -> e.getJob().toLowerCase().contains(search))
                .forEach(System.out::println);
    }

    public void m2getInverse(List<Employee> lista){
        //Comparator<Employee> comparator = (e1,e2) -> e2.getIdEmployee()-e1.getIdEmployee();
        Comparator<Employee> comparator = (e1,e2) -> e2.getIdEmployee().compareTo(e1.getIdEmployee());
        lista.stream()
                .sorted(comparator) //TRAE DE MAYOR A MENOR
                .forEach(System.out::println);
    }

    public void m3getAdults(List<Employee> lista ){
        //SOLO TRABAJA CON TIPO DE DATO LocalDate
        Predicate<Employee> isAdult = e-> Period.between(e.getBirthDate(),LocalDate.now()).getYears() >= 18;
        lista.stream()
                .filter(isAdult)
                .forEach(System.out::println);
    }

    public void m4getOldestAdult(List<Employee> lista){
        lista.stream()
                //.filter(e-> Period.between(e.getBirthDate(),LocalDate.now()).getYears() >= 18)
                .sorted(Comparator.comparing(Employee::getBirthDate)) //ESTO TRAE DE MENOR A MAYOR
                .limit(1)
                .forEach(System.out::println);
    }

    public void m5getMaxMinSalary(List<Employee> lista ){
        /*
        Optional<Employee> e= lista.stream()
                .min(Comparator.comparing(Employee::getSalary));
        System.out.println(e.get().getSalary());
        */
        double max  = lista.stream()
                            .mapToDouble(Employee::getSalary)
                            .max()
                            .orElse(0);

        double min  = lista.stream()
                .mapToDouble(Employee::getSalary)
                .min()
                .orElse(0);

        Employee emp = lista
                .stream().max(Comparator.comparing(Employee::getSalary))
                .orElse(new Employee());

        System.out.println("Empleado:" + emp);
        System.out.println("Max salary: "+max);
        System.out.println("Min salary: "+ min);
    }

    //PROMEDIO
    public void m6getAverage(List<Employee> lista){
        double salario = lista.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0);
        System.out.println(salario);
    }

    //REPORTE DE ESTADISTICAS
    public void m7getSummary(List<Employee> lista){
        DoubleSummaryStatistics doubleSummaryStatistics =
                lista.stream()
                .mapToDouble(Employee::getSalary)
                .summaryStatistics();
        System.out.println(doubleSummaryStatistics);
    }

    //va de la mano con el Equals and hash code
    public void m8getDistinct(List<Employee> lista){
        lista.stream()
                .distinct()
                .forEach(System.out::println);
    }

    public void m9getCount(List<Employee> lista){
        long contador = lista.stream()
                .filter(e -> Period.between(e.getBirthDate(), LocalDate.now()).getYears()>=18)
                .count();
        System.out.println(contador);
    }

    public void m10skipLimit(List<Employee> lista){
        lista.stream()
                .skip(1)
                .forEach(System.out::println);
    }

    public void m11getAnyYounger(List<Employee> lista){
        Predicate<Employee> isYounger = e -> Period.between(e.getBirthDate(),LocalDate.now()).getYears() <= 18;
        boolean respuesta = lista.stream()
                            .anyMatch(isYounger);
        String mensaje = respuesta ? "Si" :" No";
        System.out.println("¿Existe algún joven? " + mensaje);
    }

    //PARA TRANSFORMACIONES
    public void m12map(List<Employee> lista){
        lista.stream()
                .map(e -> {
                    e.setSalary(e.getSalary()*1.10);
                    return e;
                })
                .forEach(System.out::println);
    }

    //UNA REGLA ES QUE EL RETORNO NO SEA DIRECTAMENTE OTRO OBJETO SINO QUE SEA UNO ENCAPSULADO EN UN STREAM
    //ASIMISMO PERMITE DEVOLVER MAS VALORES DE LOS ORIGINALES
    public void m13flatMap(List<Employee> lista){
      lista.stream()
              .flatMap(e -> {
                  e.setSalary(e.getSalary()*1.10);
                  return Stream.of(e.getSalary(), "a", "b");
              })
              .forEach(System.out::println);
    }

    //AYUDA PARA VER QUE PASA DURANTE EL PROCESO
    public void m14peek(List<Employee> lista){
        lista.stream()
                .filter(e -> e.getSalary()>3000)
                .peek(System.out::println)
                .collect(Collectors.toList());
    }

    public void m15groupBy(List<Employee> lista){
        Function<Employee,String> fx = Employee::getDepartment; //va a devolver todos los departamentos
        Map<String,List<Employee>> byDepartment = lista.stream()
                .collect(groupingBy(fx));
        /*
        Map<String,Map<String,List<Employee>>> byDepartment = lista.stream()
                .collect(Collectors.groupingBy(fx, Collectors.groupingBy(Employee::getJob)));
         */
        System.out.println(byDepartment);
    }

    public void m16ToMapToSet(List<Employee> lista){
        //LEGAR DE UNA LISTA A UN MAP
        Map<Integer,Employee> mapa= lista.stream()
                .collect(Collectors.toMap(Employee::getIdEmployee, Function.identity()));

        //IMPRIME EL OBJETO
        mapa.values().stream()
                        .filter(e -> Period.between(e.getBirthDate(),LocalDate.now()).getYears() >= 18)
                        .forEach(System.out::println);

        //SE SIGUE IMPRIMIENDO COMO CLAVE : VALOR
        mapa.entrySet().stream()
                .filter(e -> e.getValue().getName().contains("o"))
                .forEach(System.out::println);


        Set<Employee> set = lista.stream().collect(Collectors.toSet());
        Set<Employee> set1 = new HashSet<>(lista);


        System.out.println("\n");
        System.out.println(set);
        //System.out.println(mapa.keySet());
        //System.out.println(mapa.values());
        //System.out.println(mapa.entrySet());
    }

    public void m17Order(List<Employee> lista){
        lista.stream()
                .sorted(Comparator.comparingInt(Employee::getIdEmployee).reversed())
                .forEach(System.out::println);

        Stream.of(1,2,2,5).sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }

        //FUENTE DE DATOS PARA PODER HACER LAS CONSULTAS
    public static void main(String[] args) {
        AppStream appStream = new AppStream();
        Employee e1 = new Employee(1, "Ejemplo1", "Developer", LocalDate.of(1991, 1, 1), 1000.00, "TI");
        Employee e2 = new Employee(2, "Ejemplo2", "QA", LocalDate.of(1993, 2, 1), 2000.00, "TI");
        Employee e3 = new Employee(3, "Ejemplo3", "Arhictect", LocalDate.of(1995, 3, 1), 3000.00, "TI");
        Employee e4 = new Employee(4, "Ejemplo4", "Cloud Engenieer", LocalDate.of(1987, 4, 1), 4000.00, "TI");
        Employee e5 = new Employee(5, "Ejemplo5", "DevOps Engenieer", LocalDate.of(1956, 1, 1), 5000.00, "TI");
        Employee e6 = new Employee(6, "Ejemplo6", "Scrum Master", LocalDate.of(2002, 11, 1), 4500.00, "TI");
        Employee e7 = new Employee(7, "Ejemplo7", "Leader Project", LocalDate.of(1998, 12, 1), 10000.00, "TI");
        Employee e8 = new Employee(8, "Ejemplo8", "Senior Developer", LocalDate.of(1996, 7, 1), 700.00, "TI");
        Employee e9 = new Employee(9, "Ejemplo9", "Junior Developer", LocalDate.of(2010, 8, 1), 500.00, "TI");
        Employee e10 = new Employee(10, "Ejemplo10", "Mobile Developer", LocalDate.of(1975, 9, 1), 3000.00, "TI");
        Employee e11 = new Employee(11, "Ejemplo11", "Salesman", LocalDate.of(1993, 9, 1), 2000.00, "Comercial");
        //Employee e12 = new Employee(11, "Mito11", "Salesman", LocalDate.of(1993, 9, 1), 2000.00, "Comercial");

        List<Employee> lista= List.of(e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11);
        //appStream.m1getDevelopers(lista,"developer");
        //appStream.m2getInverse(lista);
        //appStream.m3getAdults(lista);
        //appStream.m4getOldestAdult(lista);
        //appStream.m5getMaxMinSalary(lista);
        //appStream.m6getAverage(lista);
        //appStream.m7getSummary(lista);
        appStream.m17Order(lista);
    }
}
