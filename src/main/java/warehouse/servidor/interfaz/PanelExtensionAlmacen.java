package warehouse.servidor.interfaz;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Es el panel que contiene los botones para ejecutar los puntos de extensión
 */
public class PanelExtensionAlmacen extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * El comando para el botón 1
     */
    private final String OPCION_1 = "opcion 1";

    /**
     * El comando para el botón 2
     */
    private final String OPCION_2 = "opcion 2";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la referencia a la interfaz de la aplicación
     */
    private InterfazAlmacen ventana;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel con una referencia a la ventana principal de la aplicación <br>
     * <b>post: </b> Construyó el panel <br>
     * @param ia - Referencia a la ventana principal - ia!=null
     */
    PanelExtensionAlmacen( InterfazAlmacen ia )
    {
        ventana = ia;
        inicializar( );
    }

    // -----------------------------------------------------------------
    // Mótodos
    // -----------------------------------------------------------------

    /**
     * Inicializa los componentes del panel <br>
     * <b>post: </b> Se inicializaron y se ubicaron los componentes del panel <br>
     */
    private void inicializar( )
    {
        setBorder( new TitledBorder( "Puntos de Extensión" ) );

        setLayout( new GridBagLayout( ) );

        JButton botonOpcion1 = new JButton( "Opción 1" );
        botonOpcion1.setActionCommand( OPCION_1 );
        botonOpcion1.addActionListener( this );

        JButton botonOpcion2 = new JButton( "Opción 2" );
        botonOpcion2.setActionCommand( OPCION_2 );
        botonOpcion2.addActionListener( this );

        add( botonOpcion1 );
        add( botonOpcion2 );
    }

    /**
     * Este mótodo se llama cuando se presiona uno de los botones <br>
     * <b>post: </b> Se ejecutó la acción correspondiente al botón presionado <br>
     * @param event El evento del click en el botón
     */
    public void actionPerformed( ActionEvent event )
    {
        String comando = event.getActionCommand( );
        if( OPCION_1.equals( comando ) )
        {
            ventana.reqFuncOpcion1( );
        }
        else if( OPCION_2.equals( comando ) )
        {
            ventana.reqFuncOpcion2( );
        }
    }
}
