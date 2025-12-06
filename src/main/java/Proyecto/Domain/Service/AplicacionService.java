package Proyecto.Domain.Service;

import Proyecto.Domain.Entity.Aplicacion;
import Proyecto.Domain.Entity.Usuario;
import Proyecto.Domain.Puerto_in.AplicacionServicePort;
import Proyecto.Domain.Puerto_out.AplicacionRepositoryPort;
import Proyecto.Domain.Puerto_out.UsuarioRepositoryPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class AplicacionService implements AplicacionServicePort {
    @Inject
    AplicacionRepositoryPort aplicacionRepository;

    @Inject
    UsuarioRepositoryPort usuarioRepository;

    public AplicacionService(AplicacionRepositoryPort repository) {

    }

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


        if (aplicacion.getUsuario() != null && aplicacion.getUsuario().getId() != null) {
            Usuario usuarioCompleto = usuarioRepository.buscarPorId(aplicacion.getUsuario().getId())
                    .orElseThrow(() -> new IllegalArgumentException("El usuario no existe"));
            aplicacionExistente.setUsuario(usuarioCompleto);
        }

        // Actualizar todos los campos
        aplicacionExistente.setNombre(aplicacion.getNombre());
        aplicacionExistente.setProveedor(aplicacion.getProveedor());
        aplicacionExistente.setCategoria(aplicacion.getCategoria());
        aplicacionExistente.setLenguajePrincipal(aplicacion.getLenguajePrincipal());
        aplicacionExistente.setLenguajeSecundario(aplicacion.getLenguajeSecundario());
        aplicacionExistente.setUsaBd(aplicacion.getUsaBd());
        aplicacionExistente.setRequiereConexionRed(aplicacion.getRequiereConexionRed());
        aplicacionExistente.setNumBits(aplicacion.getNumBits());
        aplicacionExistente.setSistemaOperativo(aplicacion.getSistemaOperativo());
        aplicacionExistente.setRequisitosHardware(aplicacion.getRequisitosHardware());
        aplicacionExistente.setLicencia(aplicacion.getLicencia());
        aplicacionExistente.setPrecio(aplicacion.getPrecio());
        aplicacionExistente.setDescripcion(aplicacion.getDescripcion());
        aplicacionExistente.setWeb(aplicacion.getWeb());
        aplicacionExistente.setCorreo(aplicacion.getCorreo());
        aplicacionExistente.setTamanoInstalador(aplicacion.getTamanoInstalador());

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
