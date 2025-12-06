package Proyecto.Domain.Service;

import Proyecto.Domain.Entity.Usuario;
import Proyecto.Domain.Puerto_in.UsuarioServiceport;
import Proyecto.Domain.Puerto_out.UsuarioRepositoryPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UsuarioService implements UsuarioServiceport {
    @Inject
    UsuarioRepositoryPort usuarioRepository;

    public UsuarioService(UsuarioRepositoryPort repository) {

    }

    @Override
    @Transactional
    public Usuario crear(Usuario usuario) {
        if (usuario.getClave() == null || usuario.getClave().trim().isEmpty()) {
            throw new IllegalArgumentException("La clave es obligatoria");
        }
        if (usuario.getNombre() == null || usuario.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (usuario.getRol() == null || usuario.getRol().trim().isEmpty()) {
            throw new IllegalArgumentException("El rol es obligatorio");
        }
        return usuarioRepository.guardar(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.buscarPorId(id);
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.listarTodos();
    }

    @Override
    public List<Usuario> listarPorRol(String rol) {
        return usuarioRepository.listarPorRol(rol);
    }

    @Override
    @Transactional
    public Usuario actualizar(Long id, Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.buscarPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        usuarioExistente.setClave(usuario.getClave());
        usuarioExistente.setNombre(usuario.getNombre());
        usuarioExistente.setRol(usuario.getRol());
        return usuarioRepository.guardar(usuarioExistente);
    }

    @Override
    @Transactional
    public void eliminar(Long id) {
        if (!usuarioRepository.buscarPorId(id).isPresent()) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }
        usuarioRepository.eliminar(id);
    }
}
