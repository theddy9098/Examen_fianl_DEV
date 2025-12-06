package Proyecto.Aplication.Mapper;

import Proyecto.Aplication.Dto.AplicacionDto;
import Proyecto.Domain.Entity.Aplicacion;
import Proyecto.Domain.Entity.Usuario;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AplicacionMapper {
    // Convierte de Entidad a DTO (para devolver en la API)
    public AplicacionDto toDto(Aplicacion aplicacion) {
        if (aplicacion == null) {
            return null;
        }

        AplicacionDto dto = new AplicacionDto();
        dto.setId(aplicacion.getId());
        dto.setNombre(aplicacion.getNombre());
        dto.setProveedor(aplicacion.getProveedor());
        dto.setCategoria(aplicacion.getCategoria());
        dto.setLenguajePrincipal(aplicacion.getLenguajePrincipal());
        dto.setLenguajeSecundario(aplicacion.getLenguajeSecundario());
        dto.setUsaBd(aplicacion.getUsaBd());
        dto.setRequiereConexionRed(aplicacion.getRequiereConexionRed());
        dto.setNumBits(aplicacion.getNumBits());
        dto.setSistemaOperativo(aplicacion.getSistemaOperativo());
        dto.setRequisitosHardware(aplicacion.getRequisitosHardware());
        dto.setLicencia(aplicacion.getLicencia());
        dto.setPrecio(aplicacion.getPrecio());
        dto.setDescripcion(aplicacion.getDescripcion());
        dto.setWeb(aplicacion.getWeb());
        dto.setCorreo(aplicacion.getCorreo());
        dto.setTamanoInstalador(aplicacion.getTamanoInstalador());

        // Mapear información del usuario
        if (aplicacion.getUsuario() != null) {
            dto.setUsuarioId(aplicacion.getUsuario().getId());
            dto.setUsuarioNombre(aplicacion.getUsuario().getNombre());
        }

        return dto;
    }

    // Convierte de DTO a Entidad (para crear/actualizar)
    public Aplicacion toEntity(AplicacionDto dto) {
        if (dto == null) {
            return null;
        }

        Aplicacion aplicacion = new Aplicacion();
        aplicacion.setId(dto.getId());
        aplicacion.setNombre(dto.getNombre());
        aplicacion.setProveedor(dto.getProveedor());
        aplicacion.setCategoria(dto.getCategoria());
        aplicacion.setLenguajePrincipal(dto.getLenguajePrincipal());
        aplicacion.setLenguajeSecundario(dto.getLenguajeSecundario());
        aplicacion.setUsaBd(dto.getUsaBd());
        aplicacion.setRequiereConexionRed(dto.getRequiereConexionRed());
        aplicacion.setNumBits(dto.getNumBits());
        aplicacion.setSistemaOperativo(dto.getSistemaOperativo());
        aplicacion.setRequisitosHardware(dto.getRequisitosHardware());
        aplicacion.setLicencia(dto.getLicencia());
        aplicacion.setPrecio(dto.getPrecio());
        aplicacion.setDescripcion(dto.getDescripcion());
        aplicacion.setWeb(dto.getWeb());
        aplicacion.setCorreo(dto.getCorreo());
        aplicacion.setTamanoInstalador(dto.getTamanoInstalador());

        // Solo establecemos el ID del usuario, la entidad completa se cargará en el servicio
        if (dto.getUsuarioId() != null) {
            Usuario usuario = new Usuario();
            usuario.setId(dto.getUsuarioId());
            aplicacion.setUsuario(usuario);
        }

        return aplicacion;
    }
}
