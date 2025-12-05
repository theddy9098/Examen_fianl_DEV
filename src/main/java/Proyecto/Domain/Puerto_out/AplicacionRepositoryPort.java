package Proyecto.Domain.Puerto_out;

import Proyecto.Domain.Entity.Aplicacion;

import java.util.List;
import java.util.Optional;

public interface AplicacionRepositoryPort {
    Aplicacion guardar(Aplicacion aplicacion);

    Optional<Aplicacion> buscarPorId(Long id);

    List<Aplicacion> listarTodas();

    List<Aplicacion> listarPorUsuario(Long usuarioId);


    void eliminar(Long id);


}
