package com.kklosowski.module_catalog;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The Module controller.
 */
@Controller
@RequestMapping("/api")
@EnableAutoConfiguration
public class ModuleController {


    /**
     * The Module data access object.
     */
    ModuleDao moduleDao = new ModuleDaoImpl();


    /**
     * All list.
     *
     * @param discontinued Status
     * @return Module list
     */
    @GetMapping("/all")
    @ResponseBody
    List<Module> all(@RequestParam(value = "discontinued", required = false, defaultValue = "false") String discontinued) {
        return moduleDao.getAllModules(Boolean.valueOf(discontinued));
    }


    /**
     * Subjects list.
     *
     * @return the list
     */
    @GetMapping("/subjects")
    @ResponseBody
    List<String> subjects() {
        return moduleDao.getSubjects();
    }

    /**
     * Levels list.
     *
     * @return the list
     */
    @GetMapping("/levels")
    @ResponseBody
    List<Integer> levels() {
        return moduleDao.getLevels();
    }

    /**
     * Get module list by subject.
     *
     * @param subject      the subject
     * @param discontinued the status
     * @return the module list
     */
    @GetMapping("/{subject}")
    @ResponseBody
    List<Module> modulesBySubject(@PathVariable("subject") String subject,
                                  @RequestParam(value = "discontinued", required = false, defaultValue = "false") String discontinued) {
        return moduleDao.getModulesBySubject(subject, Boolean.valueOf(discontinued));
    }

    /**
     * Get modules list by subject and level.
     *
     * @param subject      the subject
     * @param level        the level
     * @param discontinued the statys
     * @return the module list
     */
    @GetMapping("/{subject}/{level}")
    @ResponseBody
    List<Module> modulesBySubjectAndLevel(@PathVariable("subject") String subject,
                                          @PathVariable("level") int level,
                                          @RequestParam(value = "discontinued", required = false, defaultValue = "false") String discontinued) {
        return moduleDao.getModulesBySubjectAndLevel(subject, level, Boolean.valueOf(discontinued));
    }

    /**
     * Get single module.
     *
     * @param subject the subject
     * @param level   the level
     * @param name    the name
     * @return the module list
     */
    @GetMapping("/{subject}/{level}/{name}")
    @ResponseBody
    Module module(@PathVariable("subject") String subject,
                  @PathVariable("level") int level,
                  @PathVariable("name") String name) {
        return moduleDao.getModule(name);
    }

    /**
     * Create module.
     *
     * @param subject      the subject
     * @param level        the level
     * @param name         the name
     * @param discontinued the status
     * @return success boolean
     */
    @PostMapping("/{subject}/{level}/{name}")
    @ResponseBody
    boolean createModule(@PathVariable("subject") String subject,
                         @PathVariable("level") int level,
                         @PathVariable("name") String name,
                         @RequestParam(value = "discontinued", defaultValue = "false", required = false) String discontinued) {

        return moduleDao.addModule(new Module(subject, level, name, Boolean.valueOf(discontinued)));
    }

    /**
     * Delete module.
     *
     * @param name the name
     * @return success boolean
     */
    @DeleteMapping("/{name}")
    @ResponseBody
    boolean deleteModule(@PathVariable("name") String name) {
        return moduleDao.deleteModule(name);
    }

    /**
     * Sets active status.
     *
     * @param name         the name
     * @param discontinued the status
     * @return success boolean
     */
    @PatchMapping("/{name}")
    @ResponseBody
    boolean setActiveStatus(@PathVariable("name") String name,
                            @RequestParam(value = "discontinued") String discontinued) {
        return moduleDao.updateModule(name, Boolean.valueOf(discontinued));

    }
}
