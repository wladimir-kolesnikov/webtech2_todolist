package de.wt2.todo.conf;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

// Wird benutzt um das root verzeichnis der API zu bestimmen
@ApplicationPath("web/api/")
public class JAXRSActivator extends Application {

}
