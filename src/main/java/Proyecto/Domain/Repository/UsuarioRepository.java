package Proyecto.Domain.Repository;

import Proyecto.Domain.Entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    Usuario Save(Usuario usuario);
    Optional <Usuario> findById(Long id);
    List<Usuario> findAll();
    Usuario delete(Long id);


}
