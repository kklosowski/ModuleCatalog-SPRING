package com.kklosowski.module_catalog;

import java.util.List;

public interface ModuleDao {
    public Module getModule(String name);
    public List<Module> getAllModules(Boolean discontinued);
    public List<Module> getModulesBySubject(String subject, Boolean discontinued);
    public List<Module> getModulesBySubjectAndLevel(String subject, int level, Boolean discontinued);
    public boolean addModule(Module module);
    public boolean deleteModule(String name);
    public boolean updateModule(String name, Boolean discontinued);
    public List<String> getSubjects();
    public List<Integer> getLevels();
}
