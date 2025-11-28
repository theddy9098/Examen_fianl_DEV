package Proyecto.Domain.Repository;

import Proyecto.Domain.Entity.Aplicacion;

import java.util.List;
import java.util.Optional;

public interface AplicacionRepository {
    Aplicacion Save(Aplicacion aplicacion);
    Optional <Aplicacion> findById(Long id);
    List<Aplicacion> findAll();
    Aplicacion delete(Long id);
}
