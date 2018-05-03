package com.kklosowski.module_catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * RESPONSE TO FEEDBACK
 * Previous mark: 100%
 * I improved the submission by adding the Hibernate framework with SQLite database
 * instead of simply storing the data in a collection.
 * I also created a front-end Single Page Application instead of simple java client.
 */

@SpringBootApplication
public class ModuleCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleController.class, args);
    }
}
