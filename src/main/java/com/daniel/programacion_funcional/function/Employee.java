package com.daniel.programacion_funcional.function;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString//(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {

    //@ToString.Include
    @EqualsAndHashCode.Include //PARA QUE SE HAGAN LAS COMPARACIONES TENIENDO EN CUENTA SOLO EL ID EMPLEADO
    private Integer idEmployee;

    private String name;
    private String job;
    private LocalDate birthDate;
    private double salary;
    private String department;
}
