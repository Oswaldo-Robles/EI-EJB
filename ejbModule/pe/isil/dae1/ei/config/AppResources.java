package pe.isil.dae1.ei.config;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Singleton
@Startup
@DataSourceDefinition(
		name="java:app/jdbc/EI_DAE1DS", // mismo nombre declarado en la linea 10 de persistence.xml
		className="com.microsoft.sqlserver.jdbc.SQLServerDriver",
		url="jdbc:sqlserver://DESKTOP-9E0ONEI\\AAROM:1433;databaseName=DAE1;encrypt=true;trustServerCertificate=true",
		user="aarom_villanueva",
		//Colocar el usuario de login para conectarte a sql server
		password="12345"
		//colocar la password que usas para conectarte a sql server
)



public class AppResources {

}
