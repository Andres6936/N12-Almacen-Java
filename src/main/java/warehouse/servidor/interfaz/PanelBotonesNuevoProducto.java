package warehouse.servidor.interfaz;

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
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param dnp Es una referencia al diólogo del cual hace parte este panel
     */
    PanelBotonesNuevoProducto( DialogoNuevoProducto dnp )
    {
        dialogo = dnp;

        // El botón para agregar un producto con los datos actuales
        JButton botonAgregar = new JButton( "Agregar" );
        botonAgregar.setActionCommand( AGREGAR );
        botonAgregar.addActionListener( this );
        add( botonAgregar );

        // El botón para cerrar el diólogo
        JButton botonCancelar = new JButton( "Cancelar" );
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