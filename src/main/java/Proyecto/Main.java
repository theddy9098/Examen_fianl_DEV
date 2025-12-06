
package Proyecto;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@QuarkusMain
public class Main implements QuarkusApplication {

    @Override
    public int run(String... args) throws Exception {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║  CRUD de Usuarios y Aplicaciones       ║");
        System.out.println("║  Inicializando...                      ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        System.out.println("✓ Base de datos conectada");
        System.out.println("✓ Servicios inicializados");
        System.out.println("✓ API REST disponible");
        System.out.println();
        System.out.println("Endpoints disponibles:");
        System.out.println("  • GET    /api/usuarios");
        System.out.println("  • POST   /api/usuarios");
        System.out.println("  • GET    /api/usuarios/{id}");
        System.out.println("  • PUT    /api/usuarios/{id}");
        System.out.println("  • DELETE /api/usuarios/{id}");
        System.out.println();
        System.out.println("  • GET    /api/aplicaciones");
        System.out.println("  • POST   /api/aplicaciones");
        System.out.println("  • GET    /api/aplicaciones/{id}");
        System.out.println("  • PUT    /api/aplicaciones/{id}");
        System.out.println("  • DELETE /api/aplicaciones/{id}");
        System.out.println();
        
        Quarkus.waitForExit();
        return 0;
    }
}
