package Proyecto.infrastructure.Peristen;

import Proyecto.Domain.Entity.Aplicacion;

import Proyecto.Domain.Puerto_out.AplicacionRepositoryPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AplicacionRepositoreImp  implements AplicacionRepositoryPort {

    @Inject
    EntityManager entityManager;

    @Override
    public Aplicacion guardar(Aplicacion aplicacion) {
        if (aplicacion.getId() == null) {
            entityManager.persist(aplicacion);
            return aplicacion;
        } else {
            return entityManager.merge(aplicacion);
        }
    }

    @Override
    public Optional<Aplicacion> buscarPorId(Long id) {
        Aplicacion aplicacion = entityManager.find(Aplicacion.class, id);
        return Optional.ofNullable(aplicacion);
    }

    @Override
    public List<Aplicacion> listarTodas() {
        return entityManager.createQuery(
                        "SELECT a FROM Aplicacion a LEFT JOIN FETCH a.usuario ORDER BY a.nombre",
                        Aplicacion.class)
                .getResultList();
    }

    @Override
    public List<Aplicacion> listarPorUsuario(Long usuarioId) {
        return entityManager.createQuery(
                        "SELECT a FROM Aplicacion a WHERE a.usuario.id = :usuarioId ORDER BY a.nombre",
                        Aplicacion.class)
                .setParameter("usuarioId", usuarioId)
                .getResultList();
    }

    @Override
    @Transactional
    public Aplicacion actualizar(Long id, Aplicacion aplicacion) {
        Aplicacion aplicacionExistente = entityManager.find(Aplicacion.class, id);
        if (aplicacionExistente == null) {
            throw new IllegalArgumentException("Aplicación no encontrada");
        }

        if (aplicacion.getNombre() != null && !aplicacion.getNombre().trim().isEmpty()) {
            aplicacionExistente.setNombre(aplicacion.getNombre());
        }
        
        return entityManager.merge(aplicacionExistente);
    }


    @Override
    public void eliminar(Long id) {
        Aplicacion aplicacion = entityManager.find(Aplicacion.class, id);
        if (aplicacion != null) {
            entityManager.remove(aplicacion);
        }
    }



}
