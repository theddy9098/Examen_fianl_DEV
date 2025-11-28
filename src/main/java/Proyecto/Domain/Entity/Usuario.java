package Proyecto.Domain.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String clave;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 50)
    private String rol;


    public Usuario(String clave, String nombre, String rol) {
        this.clave = clave;
        this.nombre = nombre;
        this.rol = rol;

    }


    public boolean esAdministrador() {
        return "ADMIN".equalsIgnoreCase(this.rol) || "ADMINISTRADOR".equalsIgnoreCase(this.rol);
    }

    public boolean esUsuarioRegular() {
        return "USUARIO".equalsIgnoreCase(this.rol) || "USER".equalsIgnoreCase(this.rol);
    }


}
