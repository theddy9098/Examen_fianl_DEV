package Proyecto;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/")
public class ExampleResource {

    @GET
    @Path("/health")
    @Produces(MediaType.TEXT_PLAIN)
    public String health() {
        return "✓ Sistema operativo - CRUD activo";
    }

    @GET
    @Path("/info")
    @Produces(MediaType.APPLICATION_JSON)
    public String info() {
        return """
            {
              "aplicacion": "CRUD de Usuarios y Aplicaciones",
              "version": "1.0.0",
              "estado": "operativo",
              "endpoints": {
                "usuarios": "/api/usuarios",
                "aplicaciones": "/api/aplicaciones"
              }
            }
            """;
    }
}
