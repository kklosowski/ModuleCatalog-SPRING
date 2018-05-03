package com.kklosowski.module_catalog;

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
@EnableAutoConfiguration
public class ModuleController {


    private Gson gson = new Gson();
    ModuleDaoImpl moduleDao = new ModuleDaoImpl();


    @GetMapping("/all")
    @ResponseBody
    List<Module> all(@RequestParam(value = "discontinued", required = false, defaultValue = "true") String discontinued) {
        return moduleDao.getAllModules(Boolean.valueOf(discontinued));
    }

    //TODO: Make unique subject, level and name listings for select boxes

    @GetMapping("/{subject}")
    @ResponseBody
    List<Module> levels(@PathVariable("subject") String subject,
                  @RequestParam(value = "discontinued", required = false, defaultValue = "false") String discontinued) {
        return moduleDao.getModulesBySubject(subject, Boolean.valueOf(discontinued));
    }

    @GetMapping("/{subject}/{level}")
    @ResponseBody
    List<Module> modules(@PathVariable("subject") String subject,
                   @PathVariable("level") int level,
                   @RequestParam(value = "discontinued", required = false, defaultValue = "false") String discontinued) {
        return moduleDao.getModulesBySubjectAndLevel(subject, level, Boolean.valueOf(discontinued));
    }

    @GetMapping("/{subject}/{level}/{name}")
    @ResponseBody
    Module module(@PathVariable("subject") String subject,
                  @PathVariable("level") int level,
                  @PathVariable("name") String name) {
        return moduleDao.getModule(name);
    }

    @PostMapping("/{subject}/{level}/{name}")
    @ResponseBody
    boolean createModule(@PathVariable("subject") String subject,
                         @PathVariable("level") int level,
                         @PathVariable("name") String name,
                         @RequestParam(value = "discontinued", defaultValue = "false", required = false) String discontinued) {

        return moduleDao.addModule(new Module(subject, level, name, Boolean.valueOf(discontinued)));
    }

    @DeleteMapping("/{name}")
    @ResponseBody
    boolean deleteModule(@PathVariable("name") String name) {
        return moduleDao.deleteModule(name);
    }

    @PatchMapping("/{name}")
    @ResponseBody
    boolean setActiveStatus(@PathVariable("name") String name,
                            @RequestParam(value = "discontinued") String discontinued) {
        return moduleDao.updateModule(name, Boolean.valueOf(discontinued));

    }
}
