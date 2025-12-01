package Proyecto.Domain.Puerto_in;

import Proyecto.Domain.Entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioServiceport {
    Usuario crear(Usuario usuario);

    Optional<Usuario> buscarPorId(Long id);

    Optional<Usuario> buscarPorClave(String clave);

    List<Usuario> listarTodos();

    List<Usuario> listarPorRol(String rol);

    Usuario actualizar(Long id, Usuario usuario);

    void eliminar(Long id);

    boolean existePorClave(String clave);
}
