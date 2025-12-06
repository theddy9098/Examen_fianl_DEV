package Proyecto.Domain.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "aplicacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "usuario") // Evita loops infinitos
@EqualsAndHashCode(of = "id")
public class Aplicacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String proveedor;

    @Column(nullable = false, length = 50)
    private String categoria;

    @Column(nullable = false, length = 50)
    private String lenguajePrincipal;

    @Column(length = 50)
    private String lenguajeSecundario;

    @Column(nullable = false)
    private Boolean usaBd;

    @Column(nullable = false)
    private Boolean requiereConexionRed;

    @Column(nullable = false)
    private Integer numBits;

    @Column(nullable = false, length = 50)
    private String sistemaOperativo;

    @Column(columnDefinition = "TEXT")
    private String requisitosHardware;

    @Column(nullable = false, length = 50)
    private String licencia;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(length = 255)
    private String web;

    @Column(length = 100)
    private String correo;

    @Column(nullable = false, length = 50)
    private String tamanoInstalador;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Aplicacion(String nombre, String proveedor, String categoria, String lenguajePrincipal,
                      String lenguajeSecundario, Boolean usaBd, Boolean requiereConexionRed,
                      Integer numBits, String sistemaOperativo, String requisitosHardware,
                      String licencia, BigDecimal precio, String descripcion, String web,
                      String correo, String tamanoInstalador) {
        this.nombre = nombre;
        this.proveedor = proveedor;
        this.categoria = categoria;
        this.lenguajePrincipal = lenguajePrincipal;
        this.lenguajeSecundario = lenguajeSecundario;
        this.usaBd = usaBd;
        this.requiereConexionRed = requiereConexionRed;
        this.numBits = numBits;
        this.sistemaOperativo = sistemaOperativo;
        this.requisitosHardware = requisitosHardware;
        this.licencia = licencia;
        this.precio = precio;
        this.descripcion = descripcion;
        this.web = web;
        this.correo = correo;
        this.tamanoInstalador = tamanoInstalador;
    }
}
