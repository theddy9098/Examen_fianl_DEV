package Proyecto.Aplication.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AplicacionDto {
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @NotBlank(message = "El proveedor es obligatorio")
    @Size(max = 100, message = "El proveedor no puede exceder 100 caracteres")
    private String proveedor;

    @NotBlank(message = "La categoría es obligatoria")
    @Size(max = 50, message = "La categoría no puede exceder 50 caracteres")
    private String categoria;

    @NotBlank(message = "El lenguaje principal es obligatorio")
    @JsonProperty("lenguaje_principal")
    @Size(max = 50, message = "El lenguaje principal no puede exceder 50 caracteres")
    private String lenguajePrincipal;

    @JsonProperty("lenguaje_secundario")
    @Size(max = 50, message = "El lenguaje secundario no puede exceder 50 caracteres")
    private String lenguajeSecundario;

    @NotNull(message = "Debe especificar si usa BD")
    @JsonProperty("usa_bd")
    private Boolean usaBd;

    @NotNull(message = "Debe especificar si requiere conexión a red")
    @JsonProperty("requiere_conexion_red")
    private Boolean requiereConexionRed;

    @NotNull(message = "El número de bits es obligatorio")
    @JsonProperty("num_bits")
    @Min(value = 32, message = "Los bits deben ser 32 o 64")
    @Max(value = 64, message = "Los bits deben ser 32 o 64")
    private Integer numBits;

    @NotBlank(message = "El sistema operativo es obligatorio")
    @JsonProperty("sistema_operativo")
    @Size(max = 50, message = "El sistema operativo no puede exceder 50 caracteres")
    private String sistemaOperativo;

    @JsonProperty("requisitos_hardware")
    private String requisitosHardware;

    @NotBlank(message = "La licencia es obligatoria")
    @Size(max = 50, message = "La licencia no puede exceder 50 caracteres")
    private String licencia;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El precio no puede ser negativo")
    private BigDecimal precio;

    private String descripcion;

    @Size(max = 255, message = "La web no puede exceder 255 caracteres")
    private String web;

    @Email(message = "El correo debe ser válido")
    @Size(max = 100, message = "El correo no puede exceder 100 caracteres")
    private String correo;

    @NotBlank(message = "El tamaño del instalador es obligatorio")
    @JsonProperty("tamano_instalador")
    @Size(max = 50, message = "El tamaño del instalador no puede exceder 50 caracteres")
    private String tamanoInstalador;

    @NotNull(message = "El ID del usuario es obligatorio")
    @JsonProperty("usuario_id")
    private Long usuarioId;

    // Campo adicional para mostrar el nombre del usuario en las respuestas
    @JsonProperty("usuario_nombre")
    private String usuarioNombre;
}