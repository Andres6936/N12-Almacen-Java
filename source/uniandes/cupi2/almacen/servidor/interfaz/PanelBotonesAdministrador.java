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

package uniandes.cupi2.almacen.servidor.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Es el panel donde se encuentran los botones para controlar el administrador
 */
public class PanelBotonesAdministrador extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la opción de agregar un nuevo producto
     */
    private static final String AGREGAR_PRODUCTO = "agregarProducto";

    /**
     * Constante para la opción de refrescar la visualización de los productos
     */
    private static final String REFRESCAR = "refrescar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal de la interfaz
     */
    private InterfazAlmacen ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el botón para agregar un producto
     */
    private JButton botonAgregarProducto;

    /**
     * Es el botón para refrescar la lista de productos
     */
    private JButton botonRefrescar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param ia Es una referencia a la ventana principal del administrador
     */
    public PanelBotonesAdministrador( InterfazAlmacen ia )
    {
        ventanaPrincipal = ia;

        setLayout( new GridBagLayout( ) );

        GridBagConstraints gbcBoton = new GridBagConstraints( 0, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets( 5, 15, 5, 15 ), 0, 0 );

        botonAgregarProducto = new JButton( "Agregar Producto" );
        botonAgregarProducto.setActionCommand( AGREGAR_PRODUCTO );
        botonAgregarProducto.addActionListener( this );
        add( botonAgregarProducto, gbcBoton );

        botonRefrescar = new JButton( "Refrescar" );
        botonRefrescar.setActionCommand( REFRESCAR );
        botonRefrescar.addActionListener( this );
        gbcBoton.gridy = 2;
        add( botonRefrescar, gbcBoton );
    }

    // -----------------------------------------------------------------
    // Mótodos
    // -----------------------------------------------------------------

    /**
     * Es el mótodo que se llama cuando se hace click sobre alguno de los botones
     * @param evento El evento del click sobre el botón
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if( AGREGAR_PRODUCTO.equals( comando ) )
        {
            ventanaPrincipal.mostrarDialogoAgregarProducto( );
        }
        else if( REFRESCAR.equals( comando ) )
        {
            ventanaPrincipal.refrescar( );
        }
    }
}