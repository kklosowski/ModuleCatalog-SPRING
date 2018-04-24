package com.kklosowski.module_catalog;

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class ModuleController {

    public List<Module> moduleLibrary = Arrays.asList(
            new Module("Science", 2, "Compsci", false),
            new Module("Science", 1, "Math", false)
    );
    private Gson gson = new Gson();

    @GetMapping("/all")
    @ResponseBody
    String all() {
        return gson.toJson(moduleLibrary);
    }

    @GetMapping("/")
    @ResponseBody
    String subjects() {
        return gson.toJson(
                moduleLibrary.stream()
                        .map(Module::getSubject)
                        .distinct()
                        .toArray(String[]::new)
        );
    }

    @GetMapping("/{subject}")
    @ResponseBody
    String levels(@PathVariable("subject") String subject) {
        return gson.toJson(
                moduleLibrary.stream()
                        .filter(x -> x.getSubject().equals(subject))
                        .map(x -> x.getName() + " Level: " + x.getLevel())
                        .toArray(Module[]::new)
        );
    }

    @GetMapping("/{subject}/{level}")
    @ResponseBody
    String modules(@PathVariable("subject") String subject,
                   @PathVariable("level") int level) {
        return gson.toJson(
                moduleLibrary.stream()
                        .filter(x -> x.getSubject().equals(subject))
                        .filter(x -> x.getLevel() == level)
                        .map(Module::getName)
                        .toArray(String[]::new)
        );
    }

    @GetMapping("/{subject}/{level}/{name}")
    @ResponseBody
    String module(@PathVariable("subject") String subject,
                  @PathVariable("level") int level,
                  @PathVariable("name") String name) {
        return moduleLibrary.stream().map(Module::toString).reduce(String::concat).orElse("");
    }
}
