package warehouse.cliente.interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import warehouse.cliente.mundo.PuntoDeVenta;

/**
 * This is the main window of the application.
 */
public class InterfazPuntoDeVenta extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private PuntoDeVenta puntoDeVenta;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel que se usa para registrar un producto
     */
    private PanelRegistrarProducto panelRegistrar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Descripción <br>
     * <b>post: </b> Descripción
     * @param archivo El archivo de propiedades con la configuración para el punto de venta
     * @throws Exception Se lanza esta excepción si hay problemas cargando el archivo de propiedades
     */
    private InterfazPuntoDeVenta( String archivo ) throws Exception
    {
        // Crea la clase principal
        puntoDeVenta = new PuntoDeVenta( archivo );
        construirForma( );
        construirMenu( );
        conectar( );
    }

    // -----------------------------------------------------------------
    // Mótodos
    // -----------------------------------------------------------------

    /**
     * Este mótodo ejecuta la aplicación, creando una nueva interfaz
     *
     * @param args Arguments : None
     */
    public static void main( String[] args )
    {
        try
        {
            InterfazPuntoDeVenta interfaz = new InterfazPuntoDeVenta( "pointSell.properties" );
            interfaz.setVisible( true );
        }
        catch ( Exception e )
        {
            System.out.println( e.getMessage( ) );
        }
    }

    /**
     * Este mótodo sirve para construir la forma inicializando cada uno de los componentes. <br>
     * <b>pre: </b> La ventana estó vacóa <br>
     * <b>post: </b> Se inicializaron los componentes gróficos de la aplicación
     */
    private void construirForma( )
    {
        // organizar el panel principal
        JPanel panelContenedor = new JPanel( new BorderLayout( ) );
        panelRegistrar = new PanelRegistrarProducto( this );
        panelContenedor.add( panelRegistrar, BorderLayout.CENTER );

        // Es el panel donde se muestran los botones del punto de venta
        PanelBotonesPuntoDeVenta panelBotones = new PanelBotonesPuntoDeVenta( this );
        panelContenedor.add( panelBotones, BorderLayout.SOUTH );
        add( panelContenedor, BorderLayout.CENTER );

        // Es el panel con los botones para los puntos de extensión
        PanelExtensionPuntoDeVenta panelExtension = new PanelExtensionPuntoDeVenta( this );
        add( panelExtension, BorderLayout.SOUTH );
        setSize( 500, 370 );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
        setTitle( "Punto de Venta" );
    }

    /**
     * Este mótodo intenta conectar el punto de venta al almacón
     */
    private void conectar( )
    {
        try
        {
            puntoDeVenta.conectar( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Construye el menó de la aplicación
     */
    private void construirMenu( )
    {
        // Es la barra con el menó para la aplicación
        BarraMenuPuntoDeVenta barraMenu = new BarraMenuPuntoDeVenta( this );
        setJMenuBar( barraMenu );
    }

    /**
     * Este es el mótodo llamado cuando se cierra la ventana principal. <br>
     * Este mótodo desconecta el punto de venta del almacón y cierra la aplicación.
     */
    public void dispose( )
    {
        super.dispose( );
        salir( );
    }

    /**
     * Este mótodo desconecta el punto de venta del almacón y cierra la aplicación
     */
    void salir( )
    {
        puntoDeVenta.desconectar( );
        System.exit( 0 );
    }

    /**
     * Agrega a la lista de la compra un producto
     * @param codigo El código del producto que seró agregado a la compra
     */
    void registrarProducto( String codigo )
    {
        try
        {
            puntoDeVenta.registrarVenta( codigo );
            panelRegistrar.limpiar( );
            actualizarTotal( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ) );
        }
    }

    /**
     * Inicia una nueva compra
     */
    void limpiar( )
    {
        puntoDeVenta.limpiar( );
        panelRegistrar.limpiar( );
        actualizarTotal( );
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Actualiza el valor total de los productos de la compra
     */
    private void actualizarTotal( )
    {
        panelRegistrar.actualizarTotal( puntoDeVenta.darTotal( ) );
    }

    /**
     * Mótodo para la extensión 1
     */
    void reqFuncOpcion1( )
    {
        String resultado = puntoDeVenta.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Mótodo para la extensión 2
     */
    void reqFuncOpcion2( )
    {
        String resultado = puntoDeVenta.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }
}