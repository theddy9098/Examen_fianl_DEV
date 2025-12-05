package Proyecto.infrastructure.Peristen;

import Proyecto.Domain.Entity.Usuario;
import Proyecto.Domain.Puerto_out.UsuarioRepositoryPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UsuarioRepositoreImp implements UsuarioRepositoryPort {
    @Inject
    EntityManager entityManager;

    @Override
    public Usuario guardar(Usuario usuario) {
        if (usuario.getId() == null) {
            entityManager.persist(usuario);
            return usuario;
        } else {
            return entityManager.merge(usuario);
        }
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        return Optional.ofNullable(usuario);
    }

    @Override
    public List<Usuario> listarTodos() {
        return entityManager.createQuery(
                        "SELECT u FROM Usuario u ORDER BY u.nombre",
                        Usuario.class)
                .getResultList();
    }

    @Override
    public List<Usuario> listarPorRol(String rol) {
        return entityManager.createQuery(
                        "SELECT u FROM Usuario u WHERE u.rol = :rol ORDER BY u.nombre",
                        Usuario.class)
                .setParameter("rol", rol)
                .getResultList();
    }

    @Override
    public Usuario actualizar(Long id, Usuario usuario) {
        return null;
    }

    @Override
    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        if (usuario != null) {
            entityManager.remove(usuario);
        }
    }

}
