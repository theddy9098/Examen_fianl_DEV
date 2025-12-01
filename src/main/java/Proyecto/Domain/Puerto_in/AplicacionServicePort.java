package Proyecto.Domain.Puerto_in;

import Proyecto.Domain.Entity.Aplicacion;


import java.util.List;
import java.util.Optional;

public interface AplicacionServicePort {
    Aplicacion crear(Aplicacion aplicacion);  // ← Era Usuario

    Optional<Aplicacion> buscarPorId(Long id);  // ← Era Usuario

    List<Aplicacion> listarTodas();  // ← Era Usuario

    List<Aplicacion> listarPorUsuario(Long usuarioId);

    Aplicacion actualizar(Long id, Aplicacion aplicacion);  // ← Era Usuario

    void eliminar(Long id);


}
