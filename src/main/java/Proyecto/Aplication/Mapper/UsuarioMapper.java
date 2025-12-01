package Proyecto.Aplication.Mapper;

import Proyecto.Aplication.Dto.UsuarioDto;
import Proyecto.Domain.Entity.Usuario;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioMapper {

    // Convierte de Entidad a DTO (para devolver en la API)
    public UsuarioDto toDto(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioDto dto = new UsuarioDto();
        dto.setId(usuario.getId());
        dto.setClave(usuario.getClave());
        dto.setNombre(usuario.getNombre());
        dto.setRol(usuario.getRol());

        return dto;
    }

    // Convierte de DTO a Entidad (para crear/actualizar)
    public Usuario toEntity(UsuarioDto dto) {
        if (dto == null) {
            return null;
        }

        Usuario usuario = new Usuario();
        usuario.setId(dto.getId());
        usuario.setClave(dto.getClave());
        usuario.setNombre(dto.getNombre());
        usuario.setRol(dto.getRol());

        return usuario;
    }
}
