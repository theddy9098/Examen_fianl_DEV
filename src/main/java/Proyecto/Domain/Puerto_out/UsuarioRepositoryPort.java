package Proyecto.Domain.Puerto_out;

import Proyecto.Domain.Entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepositoryPort {
    Usuario guardar(Usuario usuario);

    Optional<Usuario> buscarPorId(Long id);

    List<Usuario> listarTodos();

    List<Usuario> listarPorRol(String rol);

    Usuario actualizar(Long id, Usuario usuario);

    void eliminar(Long id);


}
