package warehouse.servidor.interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Esta clase representa el menó de la aplicación del administrador
 */
public class BarraMenuAdministrador extends JMenuBar implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Constante asociada con la opción del comando de salida 
     */
    private static final String SALIR = "salir";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal del administrador
     */
    private InterfazAlmacen ventanaPrincipal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el menó e inicializa sus componentes
     * @param ia Es una referencia a la ventana principal del administrador
     */
    BarraMenuAdministrador( InterfazAlmacen ia )
    {
        ventanaPrincipal = ia;

        JMenu menuAdministrador = new JMenu( "Administrador" );
        menuAdministrador.setMnemonic( KeyEvent.VK_A );
        add( menuAdministrador );

        // Es la opción del menó para salir de la aplicación
        JMenuItem itemSalir = new JMenuItem( "Salir" );
        itemSalir.setMnemonic( KeyEvent.VK_S );
        itemSalir.setActionCommand( SALIR );
        itemSalir.addActionListener( this );
        menuAdministrador.add( itemSalir );
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