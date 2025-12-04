package Proyecto.infrastructure.Rest;

import Proyecto.Aplication.Dto.AplicacionDto;
import Proyecto.Aplication.Mapper.AplicacionMapper;
import Proyecto.Domain.Entity.Aplicacion;
import Proyecto.Domain.Puerto_in.AplicacionServicePort;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/aplicaciones")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AplicacionResource {
    
    @Inject
    AplicacionServicePort aplicacionService;
    
    @Inject
    AplicacionMapper aplicacionMapper;
    
    @POST
    public Response crear(AplicacionDto dto) {
        try {
            Aplicacion aplicacion = aplicacionMapper.toEntity(dto);
            Aplicacion aplicacionCreada = aplicacionService.crear(aplicacion);
            AplicacionDto resultado = aplicacionMapper.toDto(aplicacionCreada);
            return Response.status(Response.Status.CREATED).entity(resultado).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(e.getMessage())).build();
        }
    }
    
    @GET
    public Response listarTodas() {
        List<AplicacionDto> aplicaciones = aplicacionService.listarTodas()
                .stream()
                .map(aplicacionMapper::toDto)
                .collect(Collectors.toList());
        return Response.ok(aplicaciones).build();
    }
    
    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        return aplicacionService.buscarPorId(id)
                .map(aplicacionMapper::toDto)
                .map(dto -> Response.ok(dto).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity(new ErrorResponse("Aplicación no encontrada")).build());
    }
    
    @GET
    @Path("/usuario/{usuarioId}")
    public Response listarPorUsuario(@PathParam("usuarioId") Long usuarioId) {
        List<AplicacionDto> aplicaciones = aplicacionService.listarPorUsuario(usuarioId)
                .stream()
                .map(aplicacionMapper::toDto)
                .collect(Collectors.toList());
        return Response.ok(aplicaciones).build();
    }
    
    @PUT
    @Path("/{id}")
    public Response actualizar(@PathParam("id") Long id, AplicacionDto dto) {
        try {
            Aplicacion aplicacion = aplicacionMapper.toEntity(dto);
            Aplicacion aplicacionActualizada = aplicacionService.actualizar(id, aplicacion);
            AplicacionDto resultado = aplicacionMapper.toDto(aplicacionActualizada);
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
            aplicacionService.eliminar(id);
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