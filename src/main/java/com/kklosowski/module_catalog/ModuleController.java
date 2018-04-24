package com.kklosowski.module_catalog;

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
@EnableAutoConfiguration
public class ModuleController {

    public List<Module> moduleLibrary = new ArrayList<>();
    //            Arrays.asList(
//            new Module("Science", 2, "Compsci", false),
//            new Module("Science", 1, "Math", false),
//            new Module("Science", 3, "Physics", true)
//    );
    private Gson gson = new Gson();

    @GetMapping("/all")
    @ResponseBody
    String all(@RequestParam(value = "discontinued", required = false, defaultValue = "true") String discontinued) {
        return gson.toJson(moduleLibrary.stream()
                .filter(x -> Boolean.valueOf(discontinued) || !x.isDiscontinued())
                .collect(Collectors.toList()));
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
    String levels(@PathVariable("subject") String subject,
                  @RequestParam(value = "discontinued", required = false, defaultValue = "true") String discontinued) {
        return gson.toJson(
                moduleLibrary.stream()
                        .filter(x -> x.getSubject().equals(subject))
                        .filter(x -> Boolean.valueOf(discontinued) || !x.isDiscontinued())
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{subject}/{level}")
    @ResponseBody
    String modules(@PathVariable("subject") String subject,
                   @PathVariable("level") int level,
                   @RequestParam(value = "discontinued", required = false, defaultValue = "true") String discontinued) {
        return gson.toJson(
                moduleLibrary.stream()
                        .filter(x -> x.getSubject().equals(subject))
                        .filter(x -> x.getLevel() == level)
                        .filter(x -> Boolean.valueOf(discontinued) || !x.isDiscontinued())
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{subject}/{level}/{name}")
    @ResponseBody
    String module(@PathVariable("subject") String subject,
                  @PathVariable("level") int level,
                  @PathVariable("name") String name) {
        return gson.toJson(
                moduleLibrary.stream()
                        .filter(x -> x.getName().equals(name))
                        .collect(Collectors.toList())
        );
    }

    @PostMapping("/{subject}/{level}/{name}")
    @ResponseBody
    boolean createModule(@PathVariable("subject") String subject,
                         @PathVariable("level") int level,
                         @PathVariable("name") String name,
                         @RequestParam(value = "discontinued", defaultValue = "false", required = false) String discontinued) {

        if (moduleLibrary.stream().anyMatch(x -> Objects.equals(x.getName(), name))) {
            return false;
        } else {
            try {
                moduleLibrary.add(new Module(subject, level, name, Boolean.valueOf(discontinued)));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return true;
        }
    }

    @DeleteMapping("/{name}")
    @ResponseBody
    boolean deleteModule(@PathVariable("name") String name) {
        return moduleLibrary.removeIf(x -> x.getName().equals(name));
    }

    @PatchMapping("/{name}")
    @ResponseBody
    boolean setActiveStatus(@PathVariable("name") String name,
                            @RequestParam(value = "discontinued") String discontinued) {
        if (!moduleLibrary.stream().anyMatch(x -> Objects.equals(x.getName(), name))) {
            return false;
        } else {
            moduleLibrary.stream()
                    .filter(x -> x.getName().equals(name))
                    .findFirst()
                    .get()
                    .setDiscontinued(Boolean.valueOf(discontinued));
            return true;
        }
    }
}
