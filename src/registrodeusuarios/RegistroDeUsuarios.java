
package registrodeusuarios;

import controladores.Control;
import dao.UsuarioDao;
import vistas.VistaBienvenido;
import vistas.VistaInicioDeSesion;
import vistas.VistaRegistro;


public class RegistroDeUsuarios {

    
    public static void main(String[] args) {
        VistaInicioDeSesion vis = new VistaInicioDeSesion();
        VistaRegistro vr= new VistaRegistro();
        VistaBienvenido vb = new VistaBienvenido();
        UsuarioDao usDao = new UsuarioDao();
        
        Control control= new Control(vis, vr, vb, usDao);
        
        vis.setVisible(true);
    }
    
}
