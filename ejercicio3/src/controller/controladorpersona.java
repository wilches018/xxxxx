/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import model.MOcliente;
import model.MOproducto;
import model.MOvendedor;
import view.admin;
import view.cliente;
import view.login;
import view.producto;
import view.vendedor;
import view.Cliente1;
import view.Producto1;

/**
 *
 * @author SENA
 */
public class controladorpersona implements ActionListener {

    login log;
    admin admi = new admin();
    Cliente1 xxx = new Cliente1();
            

    Producto1 produsct1 = new Producto1();
    vendedor vende = new vendedor();

    ArrayList<MOcliente> listpersonas = new ArrayList<MOcliente>();
    ArrayList<MOvendedor> listvendedor = new ArrayList<MOvendedor>();
    ArrayList<MOproducto> listproducto = new ArrayList<MOproducto>();

    private String user = null;
    private String pass = null;

    private int ADid;
    private String ADnombre;
    private String ADapellido;
    private String ADdireccion;

    private int VEid;
    private String VEnombre;
    private String VEapellido;
    
    private int PRid;
    private String PRnombre;
    private String PRprecio;
    private int PRcategoria;

    public controladorpersona(login log) {

        this.log = log;
        this.log.Ingresar.addActionListener(this);
        this.admi.vercliente.addActionListener(this);
        this.admi.vervendedor.addActionListener(this);
        this.admi.verproducto.addActionListener(this);
        this.vende.VEguardar.addActionListener(this);
        this.vende.VEmostrarr.addActionListener(this);
        this.produsct1.PRnuevo_guardar.addActionListener(this);
        this.produsct1.PRnuevo_mostrar.addActionListener(this);
        this.xxx.NUguardar.addActionListener(this);
        this.xxx.NUmostrar.addActionListener(this);
        

    }

    public void iniciar() {

        this.log.setVisible(true);
        this.admi.vercliente.setVisible(true);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.log.Ingresar) {

            user = this.log.LOusuario.getText();
            pass = this.log.LOcontraseña.getText();

            boolean ff = false;

            if (user.equals("admin") && pass.equals("123")) {
                admi.setVisible(true);
                ff = true;
                this.log.LOusuario.setText("");
                this.log.LOcontraseña.setText("");

            }

            if (user.equals("cliente") && pass.equals("456")) {
                xxx.setVisible(true);
                ff = true;
                this.log.LOusuario.setText("");
                this.log.LOcontraseña.setText("");

            }

            if (user.equals("vendedor") && pass.equals("789")) {
                vende.setVisible(true);
                ff = true;
                this.log.LOusuario.setText("");
                this.log.LOcontraseña.setText("");
            }

            if (ff == false) {
                JOptionPane.showMessageDialog(null, "USUARIO INCORRECTO");
            }

        }
        if (this.xxx.NUguardar == e.getSource()) {
            ADid = Integer.parseInt(this.xxx.NUid.getText());
            ADnombre = this.xxx.NUnombre.getText();
            ADapellido = this.xxx.NUapellido.getText();
            ADdireccion = this.xxx.NUdireccion.getText();

            listpersonas.add(new MOcliente(ADid, ADnombre, ADapellido, ADdireccion));
            this.limpiar();
            cargartabla(this.vende.VEtabla, listvendedor );
            JOptionPane.showMessageDialog(xxx, "LOS DATOS FUERON GUARDADOS CON EXITOS");
        
        }

        if (admi.vercliente == e.getSource()){
            this.admi.xdd.add(xxx);
            xxx.show();
        }
        if (e.getSource() == this.admi.vervendedor) {
            vende.setVisible(true);
        }
         if (admi.verproducto == e.getSource()){
            this.admi.xdd.add(produsct1);
            produsct1.show();
        }
        if (e.getSource()== this.vende.VEmostrarr){
            cargartabla(this.vende.VEtabla,listvendedor);
        }
        if (e.getSource() == this.xxx.NUguardar) {
           tablecliente(this.xxx.nueva_tabla_de_cliente,listpersonas); 
           
        }
        if (e.getSource() == this.produsct1.PRnuevo_mostrar) {
           tableproducto(this.produsct1.nueva_tabla_de_producto,listproducto); 
           
        }
    

        if (this.vende.VEguardar == e.getSource()) {
            VEid = Integer.parseInt(this.vende.VEid.getText());
            VEnombre = this.vende.VEnombre.getText();
            VEapellido = this.vende.VEapellido.getText();

           listvendedor.add(new MOvendedor(VEid, VEnombre, VEapellido));
           tablecliente(this.vende.VEtabla,listpersonas );
            this.limpiar();
            JOptionPane.showMessageDialog(xxx, "LOS DATOS FUERON GUARDADOS CON EXITOS");

        }
        if(this.produsct1.PRnuevo_guardar == e.getSource()) {
            PRid = Integer.parseInt(this.produsct1.PRnuevaid.getText());
            PRnombre = this.produsct1.PRnuevo_nombre.getText();
            PRprecio = this.produsct1.PRnuevo_precio.getText();
            PRcategoria = this.produsct1.PRnueva_categoria.getSelectedIndex();

            listproducto.add(new MOproducto(PRid, PRnombre, PRprecio, PRcategoria));
            tableproducto(this.produsct1.nueva_tabla_de_producto,listproducto);
            this.limpiar();
            JOptionPane.showMessageDialog(produsct1, "LOS DATOS FUERON GUARDADOS CON EXITOS");

        }

    }

    private void limpiar() {
        xxx.NUid.setText("");
        xxx.NUnombre.setText("");
        xxx.NUapellido.setText("");
        xxx.NUdireccion.setText("");
        vende.VEid.setText("");
        vende.VEnombre.setText("");
        vende.VEapellido.setText(""); 
        produsct1.PRnuevaid.setText("");
        produsct1.PRnuevo_nombre.setText("");
       produsct1.PRnuevo_precio.setText(""); 
        
        
    }
    public void cargartabla (JTable VEtabla, ArrayList<MOvendedor> listvendedor){
        for (int i =0; i<listvendedor.size(); i++){
            VEtabla.setValueAt(listvendedor.get(i).getVEid(), i, 0);
            VEtabla.setValueAt(listvendedor.get(i).getVEnombre(), i, 1);
            VEtabla.setValueAt(listvendedor.get(i).getVEapellido(), i, 2);

        }

    }

    public void tablecliente(JTable CLtabla, ArrayList<MOcliente> listcliente) {
        for (int i = 0; i < listcliente.size(); i++) {
            CLtabla.setValueAt(listcliente.get(i).getADid(), i, 0);
            CLtabla.setValueAt(listcliente.get(i).getADnombre(), i, 1);
            CLtabla.setValueAt(listcliente.get(i).getADapellido(), i, 2);
            CLtabla.setValueAt(listcliente.get(i).getADdireccion(), i, 3);

        }

    }

    public void tableproducto(JTable PRtabla, ArrayList<MOproducto> listproducto) {
        for (int i = 0; i < listproducto.size(); i++) {
            PRtabla.setValueAt(listproducto.get(i).getPRid(), i, 0);
            PRtabla.setValueAt(listproducto.get(i).getPRnombre(), i, 1);
            PRtabla.setValueAt(listproducto.get(i).getPRprecio(), i, 2);
            PRtabla.setValueAt(listproducto.get(i).getPRcategoria(), i, 3);

        }
    }
}
