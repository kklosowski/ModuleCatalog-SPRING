package com.kklosowski.module_catalog;

import java.util.List;

/**
 * The interface Module dao.
 */
public interface ModuleDao {
    /**
     * Gets module.
     *
     * @param name the name
     * @return the module
     */
    Module getModule(String name);

    /**
     * All list.
     *
     * @param discontinued Status
     * @return Module list
     */
    List<Module> getAllModules(Boolean discontinued);

    /**
     * Gets modules by subject.
     *
     * @param subject      the subject
     * @param discontinued the status
     * @return the module list
     */
    List<Module> getModulesBySubject(String subject, Boolean discontinued);

    /**
     * Gets modules by subject and level.
     *
     * @param subject      the subject
     * @param level        the level
     * @param discontinued the statys
     * @return the module list
     */
    List<Module> getModulesBySubjectAndLevel(String subject, int level, Boolean discontinued);

    /**
     * Add module boolean.
     *
     * @param module the module
     * @return success boolean
     */
    boolean addModule(Module module);

    /**
     * Delete module.
     *
     * @param name the name
     * @return success boolean
     */
    boolean deleteModule(String name);

    /**
     * Update module.
     *
     * @param name         the name
     * @param discontinued the discontinued
     * @return success boolean
     */
    boolean updateModule(String name, Boolean discontinued);

    /**
     * Subjects list.
     *
     * @return the list
     */
    List<String> getSubjects();

    /**
     * Levels list.
     *
     * @return the list
     */
    List<Integer> getLevels();
}
