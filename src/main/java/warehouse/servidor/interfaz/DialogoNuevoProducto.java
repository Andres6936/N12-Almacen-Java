/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotó - Colombia)
 * Departamento de Ingenieróa de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_almacen
 * Autor: Mario Sónchez - 6/11/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package warehouse.servidor.interfaz;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Es el diólogo que se usa para agregar los datos de un nuevo producto
 */
public class DialogoNuevoProducto extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal del administrador
     */
    private InterfazAlmacen ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel donde se ingresan los datos del nuevo producto
     */
    private PanelDatosNuevoProducto panelDatos;

    /**
     * Es el panel con los botones que controlan el diólogo
     */
    private PanelBotonesNuevoProducto panelBotones;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el diólogo e inicializa sus componentes
     * @param ia Es una referencia a la ventana principal del administrador
     */
    public DialogoNuevoProducto( InterfazAlmacen ia )
    {
        super( ia, true );
        ventanaPrincipal = ia;

        panelDatos = new PanelDatosNuevoProducto( );
        panelBotones = new PanelBotonesNuevoProducto( this );

        add( panelDatos, BorderLayout.CENTER );
        add( panelBotones, BorderLayout.SOUTH );

        setResizable( false );
        setTitle( "Agregar Producto" );
        pack( );
    }

    // -----------------------------------------------------------------
    // Mótodos
    // -----------------------------------------------------------------

    /**
     * Agrega un nuevo producto al almacón
     */
    public void agregarProducto( )
    {
        try
        {
            String codigo = panelDatos.darCodigo( );
            String nombre = panelDatos.darNombre( );
            int precio = Integer.parseInt( panelDatos.darPrecio( ) );
            int unidades = Integer.parseInt( panelDatos.darUnidades( ) );

            ventanaPrincipal.agregarProducto( this, codigo, nombre, precio, unidades );
        }
        catch( NumberFormatException nfe )
        {
            JOptionPane.showMessageDialog( this, "El precio y las unidades deben ser nómeros enteros", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }
}