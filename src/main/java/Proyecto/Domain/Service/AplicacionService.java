package Proyecto.Domain.Service;

import Proyecto.Domain.Entity.Aplicacion;
import Proyecto.Domain.Puerto_in.AplicacionServicePort;
import Proyecto.Domain.Puerto_out.AplicacionRepositoryPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AplicacionService implements AplicacionServicePort {
    @Inject
    AplicacionRepositoryPort aplicacionRepository;

    @Override
    @Transactional
    public Aplicacion crear(Aplicacion aplicacion) {
        if (aplicacion.getNombre() == null || aplicacion.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la aplicación es obligatorio");
        }
        return aplicacionRepository.guardar(aplicacion);
    }

    @Override
    public Optional<Aplicacion> buscarPorId(Long id) {
        return aplicacionRepository.buscarPorId(id);
    }

    @Override
    public List<Aplicacion> listarTodas() {
        return aplicacionRepository.listarTodas();
    }

    @Override
    public List<Aplicacion> listarPorUsuario(Long usuarioId) {
        return aplicacionRepository.listarPorUsuario(usuarioId);
    }

    @Override
    @Transactional
    public Aplicacion actualizar(Long id, Aplicacion aplicacion) {
        Aplicacion aplicacionExistente = aplicacionRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Aplicación no encontrada"));
        aplicacionExistente.setNombre(aplicacion.getNombre());
        return aplicacionRepository.guardar(aplicacionExistente);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!aplicacionRepository.buscarPorId(id).isPresent()) {
            throw new IllegalArgumentException("Aplicación no encontrada");
        }
        aplicacionRepository.eliminar(id);
    }
}
