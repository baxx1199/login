
package controladores;

import dao.UsuarioDao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelos.Usuarios;
import vistas.VistaBienvenido;
import vistas.VistaInicioDeSesion;
import vistas.VistaRegistro;


public class Control implements ActionListener{
    
    private VistaInicioDeSesion vis;
    private VistaRegistro vr;
    private VistaBienvenido vb;
    private UsuarioDao usDao;
    private UsuariosJpaController ujpc;

    public Control(VistaInicioDeSesion vis, VistaRegistro vr, VistaBienvenido vb, UsuarioDao usDao) {
        this.vis = vis;
        this.vr = vr;
        this.vb = vb;
        this.usDao = usDao;
        assignment();
    }
    
    //asignar evento botones
    public void assignment(){
        vis.getLogInBtn().addActionListener(this);
        vis.getSingUpBtn().addActionListener(this);
        vr.getNewSingUpBtn().addActionListener(this);
        vr.getBackBtn().addActionListener(this);
        vb.getCloseSessionBtn().addActionListener(this);
    }
    
    
    // iniciar sesion
    public void validarinicio(){
        String us=vis.getUserNameTxt().getText();
        String con=vis.getPasswordTxt().getText();
        if (us.equals("")|| con.equals("")) {
            JOptionPane.showMessageDialog(null, "Debes llenar los campos"); 
            
        }else{
            boolean acceso= usDao.checkLogIn(us, con);
            if (acceso == true) {
                vb.setVisible(true);
                vis.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null, "Usuario no encontrado" );
            }
        }
    }
    
    
    //registrar usuario
    
    public void nuevoRegistro(){
        String nuevoUsuario = vr.getNewUserNameTxt().getText();
        String nuevaContraseña = vr.getNewPasswordTxt().getText();
        String confirmacionContraseña = vr.getComfirmPasswordTxt1().getText();
        
        
        usDao.addNewUser(nuevoUsuario, nuevaContraseña, confirmacionContraseña);
        
       
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (vis.getLogInBtn()==e.getSource()) {
            validarinicio();
        }else if (vis.getSingUpBtn()==e.getSource()) {
            vr.setVisible(true);
            vis.setVisible(false);
        }else if (vr.getNewSingUpBtn()==e.getSource()) {
            nuevoRegistro();
        }else if (vr.getBackBtn()==e.getSource()) {
            vis.setVisible(true);
            vr.setVisible(false);
        }else if (vb.getCloseSessionBtn()==e.getSource()) {
            vis.setVisible(true);
            vb.setVisible(false);
        }
    }
    
    
    
}
