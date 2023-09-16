package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public final class PersistenceFactory {
    private static PersistenceFactory instance;

	PersistenceHandling service;
	
	private PersistenceFactory(String Persistencetype) {
		if(service == null) {
			if(Persistencetype.equals("MySQL")) 
				service = new mySQLDBHandler();
			
			else if(Persistencetype.equals("Oracle"))
				service = new OracleDBBHandler();
			
			service.setHandler();
			service.startConnection();
		}
    }
	
	public static PersistenceFactory getInstace(String Persistencetype) {
        if(instance == null) {
            instance = new PersistenceFactory(Persistencetype);
        }
        return instance;
    }
	
	public PersistenceHandling getDB() {
        return service;
    }
	
}