package com.kklosowski.module_catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        ModuleDaoImpl moduleDao = new ModuleDaoImpl();
//        moduleDao.getAllModules().forEach(System.out::println);
        SpringApplication.run(ModuleController.class, args);
    }
}
