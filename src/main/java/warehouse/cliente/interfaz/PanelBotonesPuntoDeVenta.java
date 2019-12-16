package warehouse.cliente.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Es el panel con el botón para cerrar una venta
 */
public class PanelBotonesPuntoDeVenta extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String NUEVA_VENTA = "nuevaVenta";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal de la interfaz
     */
    private InterfazPuntoDeVenta ventanaPrincipal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param ipv Es una referencia a la ventana principal del punto de venta
     */
    PanelBotonesPuntoDeVenta( InterfazPuntoDeVenta ipv )
    {
        ventanaPrincipal = ipv;

        // Es el botón para iniciar una nueva venta
        JButton botonNuevaVenta = new JButton( "Cerrar Venta" );
        botonNuevaVenta.setActionCommand( NUEVA_VENTA );
        botonNuevaVenta.addActionListener( this );
        add( botonNuevaVenta );
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
        if( NUEVA_VENTA.equals( comando ) )
        {
            ventanaPrincipal.limpiar( );
        }
    }
}