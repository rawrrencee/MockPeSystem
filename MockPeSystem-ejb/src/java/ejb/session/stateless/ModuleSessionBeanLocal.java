/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb.session.stateless;

import entity.Module;
import java.util.List;
import util.exception.GeneralException;
import util.exception.ModuleExistException;
import util.exception.ModuleNotFoundException;

public interface ModuleSessionBeanLocal {
    
    public List<Module> retrieveAllModules();
    
    public Module retrieveModuleByModuleId(Long moduleId) throws ModuleNotFoundException;
    
    public Module createNewModule(Module newModule) throws ModuleExistException, GeneralException;
    
    public Module retrieveModuleByModuleCode(String moduleCode) throws ModuleNotFoundException;
}
