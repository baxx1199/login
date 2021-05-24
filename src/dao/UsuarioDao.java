
package dao;

import controladores.UsuariosJpaController;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;



import modelos.Usuarios;


public class UsuarioDao {
    private Usuarios usuario = new Usuarios();
    private UsuariosJpaController ujc = new UsuariosJpaController();
    
    public boolean checkLogIn(String usuario, String con){
        EntityManager em = ujc.getEntityManager();
        boolean logueado=false ;
        
        try {
            Query query = em.createQuery("SELECT u.usuario, u.contrasena FROM Usuarios u WHERE u.usuario = :usuario AND u.contrasena= :contrasena ");
            query.setParameter("usuario", usuario);
            query.setParameter("contrasena", con);
            List result = query.getResultList();
            
            if (!result.isEmpty()) {
                logueado = true;
            } else{
                logueado = false;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return logueado;
    }
    
    public void addNewUser(String user, String con, String con2){
        if (con.equals(con2)) {
            try {
                usuario.setUsuario(user);
                usuario.setContrasena(con);

                ujc.create(usuario);
                JOptionPane.showMessageDialog(null, " registrado exitosamente");
            } catch (Exception e) {
                 JOptionPane.showMessageDialog(null, "ups...Algo a salido mal, conexion a la base de datos fallida"+ e);
            }
        }else {
            JOptionPane.showMessageDialog(null, "contraseñas no coinciden");
        }
    }

}
