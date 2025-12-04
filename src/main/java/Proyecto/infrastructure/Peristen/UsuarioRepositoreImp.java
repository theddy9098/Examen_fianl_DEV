package Proyecto.infrastructure.Peristen;

import Proyecto.Domain.Entity.Usuario;
import Proyecto.Domain.Puerto_out.UsuarioRepositoryPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UsuarioRepositoreImp implements UsuarioRepositoryPort {
    @Inject
    UsuarioRepositoryPort UsuarioRepository;

    @Override
    @Transactional
    public Usuario guardar(Usuario usuario) {
        // Validar campos obligatorios
        if (usuario.getClave() == null || usuario.getClave().trim().isEmpty()) {
            throw new IllegalArgumentException("La clave es obligatoria");
        }
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (usuario.getRol() == null || usuario.getRol().trim().isEmpty()) {
            throw new IllegalArgumentException("El rol es obligatorio");
        }

        return UsuarioRepository.guardar(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return UsuarioRepository.buscarPorId(id);
    }

    @Override
    public List<Usuario> listarTodos() {
        return UsuarioRepository.listarTodos();
    }

    @Override
    public List<Usuario> listarPorRol(String rol) {
        return UsuarioRepository.listarPorRol(rol);
    }

    @Override
    @Transactional
    public Usuario actualizar(Long id, Usuario usuario) {
        Usuario usuarioExistente = UsuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));

        // Actualizar campos
        usuarioExistente.setClave(usuario.getClave());
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setRol(usuario.getRol());

        return UsuarioRepository.guardar(usuarioExistente);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!UsuarioRepository.buscarPorId(id).isPresent()) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        UsuarioRepository.eliminar(id);
    }

}
