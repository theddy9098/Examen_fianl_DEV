package Proyecto.Domain.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "aplicaciones")
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

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Aplicacion> aplicaciones = new ArrayList<>();
    

    public Usuario(String clave, String nombre, String rol) {
        this.clave = clave;
        this.nombre = nombre;
        this.rol = rol;
        this.aplicaciones = new ArrayList<>();


    }


    public boolean esAdministrador() {
        return "ADMIN".equalsIgnoreCase(this.rol) || "ADMINISTRADOR".equalsIgnoreCase(this.rol);
    }

    public boolean esUsuarioRegular() {
        return "USUARIO".equalsIgnoreCase(this.rol) || "USER".equalsIgnoreCase(this.rol);
    }

    // Métodos helper para manejar la relación bidireccional
    public void agregarAplicacion(Aplicacion aplicacion) {
        if (!this.aplicaciones.contains(aplicacion)) {
            this.aplicaciones.add(aplicacion);
            aplicacion.setUsuario(this);
        }
    }

    public void removerAplicacion(Aplicacion aplicacion) {
        if (this.aplicaciones.contains(aplicacion)) {
            this.aplicaciones.remove(aplicacion);
            aplicacion.setUsuario(null);
        }
    }
}
