package warehouse.cliente.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Esta clase representa el menó de la aplicación del punto de venta
 */
public class BarraMenuPuntoDeVenta extends JMenuBar implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String SALIR = "salir";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal del punto de venta
     */
    private InterfazPuntoDeVenta ventanaPrincipal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el menó e inicializa sus componentes
     * @param ipv Es una referencia a la ventana principal del punto de venta
     */
    BarraMenuPuntoDeVenta( InterfazPuntoDeVenta ipv )
    {
        ventanaPrincipal = ipv;

        // Es el menó Punto de Venta
        JMenu menuPuntoDeVenta = new JMenu( "Punto de Venta" );
        menuPuntoDeVenta.setMnemonic( KeyEvent.VK_P );
        add( menuPuntoDeVenta );

        // Es la opción del menó para salir de la aplicación
        JMenuItem itemSalir = new JMenuItem( "Salir" );
        itemSalir.setMnemonic( KeyEvent.VK_S );
        itemSalir.setActionCommand( SALIR );
        itemSalir.addActionListener( this );
        menuPuntoDeVenta.add( itemSalir );
    }

    // -----------------------------------------------------------------
    // Mótodos
    // -----------------------------------------------------------------

    /**
     * Es el mótodo que se llama cuando se selecciona una de las opciones del menó
     * @param evento Es el evento de seleccionar una de las opciones
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if( comando.equals( SALIR ) )
        {
            ventanaPrincipal.salir( );
        }
    }
}