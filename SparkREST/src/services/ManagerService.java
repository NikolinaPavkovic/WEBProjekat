package services;

import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import beans.Manager;
import dao.Managers;

public class ManagerService {
	private Managers managers = new Managers();
	
	public Collection<Manager> getManagers() throws JsonGenerationException, JsonMappingException, IOException {
		return managers.load();
	}
}
