package pe.isil.dae1.ei.config;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Singleton
@Startup
@DataSourceDefinition(
		name="java:app/jdbc/EI_DAE1DS", // mismo nombre declarado en la linea 10 de persistence.xml
		className="com.microsoft.sqlserver.jdbc.SQLServerDriver",
		url="jdbc:sqlserver://localhost:1433;databaseName=DAE1_EI;encrypt=true;trustServerCertificate=true",
		user="",
		//Colocar el usuario de login para conectarte a sql server
		password=""
		//colocar la password que usas para conectarte a sql server
)



public class AppResources {

}
