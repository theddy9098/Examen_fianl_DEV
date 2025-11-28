package Proyecto.Domain.Repository;

import Proyecto.Domain.Entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository {
    Usuario Save(Usuario usuario);
    Optional <Usuario> findByClave(String clave);
    List<Usuario> findAll();
    Usuario delete(Long id);


}
