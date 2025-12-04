package Proyecto.infrastructure.Rest;

import Proyecto.Aplication.Dto.UsuarioDto;
import Proyecto.Aplication.Mapper.UsuarioMapper;
import Proyecto.Domain.Entity.Usuario;
import Proyecto.Domain.Puerto_in.UsuarioServiceport;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/usuarios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource {
    
    @Inject
    UsuarioServiceport usuarioService;
    
    @Inject
    UsuarioMapper usuarioMapper;
    
    @POST
    public Response crear(UsuarioDto dto) {
        try {
            Usuario usuario = usuarioMapper.toEntity(dto);
            Usuario usuarioCreado = usuarioService.crear(usuario);
            UsuarioDto resultado = usuarioMapper.toDto(usuarioCreado);
            return Response.status(Response.Status.CREATED).entity(resultado).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage())).build();
        }
    }
    
    @GET
    public Response listarTodos() {
        List<UsuarioDto> usuarios = usuarioService.listarTodos()
                .stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
        return Response.ok(usuarios).build();
    }
    
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        return usuarioService.buscarPorId(id)
                .map(usuarioMapper::toDto)
                .map(dto -> Response.ok(dto).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("Usuario no encontrado")).build());
    }

    
    @GET
    @Path("/rol/{rol}")
    public Response listarPorRol(@PathParam("rol") String rol) {
        List<UsuarioDto> usuarios = usuarioService.listarPorRol(rol)
                .stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
        return Response.ok(usuarios).build();
    }
    
    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, UsuarioDto dto) {
        try {
            Usuario usuario = usuarioMapper.toEntity(dto);
            Usuario usuarioActualizado = usuarioService.actualizar(id, usuario);
            UsuarioDto resultado = usuarioMapper.toDto(usuarioActualizado);
            return Response.ok(resultado).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(e.getMessage())).build();
        }
    }
    
    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Long id) {
        try {
            usuarioService.eliminar(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ErrorResponse(e.getMessage())).build();
        }
    }
    
    // Clase interna para respuestas de error
    public static class ErrorResponse {
        public String error;
        
        public ErrorResponse(String error) {
            this.error = error;
        }
    }
}