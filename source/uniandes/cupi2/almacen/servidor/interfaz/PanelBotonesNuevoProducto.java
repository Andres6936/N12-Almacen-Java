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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Es el panel donde estón los botones para agregar un nuevo producto
 */
public class PanelBotonesNuevoProducto extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante para la opción de agregar un nuevo producto
     */
    private static final String AGREGAR = "agregar";

    /**
     * Constante para la opción de cancelar la operación
     */
    private static final String CANCELAR = "cancelar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el diólogo del cual hace parte este panel
     */
    private DialogoNuevoProducto dialogo;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * El botón para agregar un producto con los datos actuales
     */
    private JButton botonAgregar;

    /**
     * El botón para cerrar el diólogo
     */
    private JButton botonCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param dnp Es una referencia al diólogo del cual hace parte este panel
     */
    public PanelBotonesNuevoProducto( DialogoNuevoProducto dnp )
    {
        dialogo = dnp;

        botonAgregar = new JButton( "Agregar" );
        botonAgregar.setActionCommand( AGREGAR );
        botonAgregar.addActionListener( this );
        add( botonAgregar );

        botonCancelar = new JButton( "Cancelar" );
        botonCancelar.setActionCommand( CANCELAR );
        botonCancelar.addActionListener( this );
        add( botonCancelar );
    }

    // -----------------------------------------------------------------
    // Mótodos
    // -----------------------------------------------------------------

    /**
     * Es el mótodo que se llama cuando se hace click sobre alguno de los botones del panel
     * @param evento Es el evento de click sobre uno de los botones
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if( AGREGAR.equals( comando ) )
        {
            dialogo.agregarProducto( );
        }
        else if( CANCELAR.equals( comando ) )
        {
            dialogo.dispose( );
        }
    }
}