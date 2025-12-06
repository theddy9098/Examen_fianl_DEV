package Proyecto.Aplication.Dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDto{

    private Long id;

        @NotBlank(message = "La clave es obligatoria")
        @Size(min = 3, max = 50, message = "La clave debe tener entre 3 y 50 caracteres")
        private String clave;

        @NotBlank(message = "El nombre es obligatorio")
        @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
        private String nombre;

        @NotBlank(message = "El rol es obligatorio")
        @Size(max = 50, message = "El rol no puede exceder 50 caracteres")
        private String rol;
}
