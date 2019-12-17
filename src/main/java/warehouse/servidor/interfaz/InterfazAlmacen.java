package warehouse.servidor.interfaz;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import warehouse.servidor.mundo.Almacen;
import warehouse.servidor.mundo.ServidorAlmacen;

/**
 * Esta es la ventana principal de la aplicación.
 */
public class InterfazAlmacen extends JFrame
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Clase principal del mundo
     */
    private Almacen almacen;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel donde se muestran los productos que hay actualmente en el almacón
     */
    private PanelProductosAdministrador panelProductos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Descripción <br>
     * @param elAlmacen Es el almacón que se va a usar en esta aplicación
     * @throws Exception Se lanza esta excepción si hay problemas cargando el archivo de propiedades
     */
    private InterfazAlmacen( Almacen elAlmacen ) throws Exception
    {
        // Crea la clase principal
        almacen = elAlmacen;
        construirForma( );
        construirMenu( );
        actualizarProductos( );
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
            ServidorAlmacen servidor = new ServidorAlmacen( "almacen.properties" );
            InterfazAlmacen interfaz = new InterfazAlmacen( servidor.darAlmacen( ) );
            interfaz.setVisible( true );
            servidor.recibirConexiones( );
        }
        catch ( SQLException e )
        {
            SQLException s2 = e;
            while ( s2 != null )
            {
                System.out.println( s2.getMessage( ) );
                s2 = s2.getNextException( );
            }
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
        setLayout( new BorderLayout( ) );

        panelProductos = new PanelProductosAdministrador( );
        add( panelProductos, BorderLayout.CENTER );

        // Es el panel donde se muestran los botones para controlar la aplicación del administrador
        PanelBotonesAdministrador panelBotones = new PanelBotonesAdministrador( this );
        add( panelBotones, BorderLayout.EAST );

        // Es el panel con los botones para los puntos de extensión
        PanelExtensionAlmacen panelExtension = new PanelExtensionAlmacen( this );
        add( panelExtension, BorderLayout.SOUTH );

        setSize( 630, 330 );
        setTitle( "Administrador" );
        setDefaultCloseOperation( JFrame.DISPOSE_ON_CLOSE );
    }

    /**
     * Construye el menó de la aplicación
     */
    private void construirMenu( )
    {
        // Es la barra con el menó para la aplicación
        BarraMenuAdministrador barraMenu = new BarraMenuAdministrador( this );
        setJMenuBar( barraMenu );
    }

    /**
     * Este es el mótodo llamado cuando se cierra la ventana principal. <br>
     * Este mótodo desconecta el administrador del almacón y cierra la aplicación.
     */
    public void dispose( )
    {
        super.dispose( );
        salir( );
    }

    /**
     * Inicializa la base de datos
     */
    public void inicializarBaseDeDatos( )
    {
        try
        {
            almacen.inicializarTabla( );
            actualizarProductos( );
        }
        catch( SQLException e )
        {
            JOptionPane.showMessageDialog( this, "No se pudo inicializar la base de datos:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Este mótodo desconecta el administrador del almacón y cierra la aplicación
     */
    void salir( )
    {
        try
        {
            almacen.desconectarBD( );
        }
        catch ( SQLException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        System.exit( 0 );
    }

    /**
     * Muestra el diólogo para agregar un producto
     */
    void mostrarDialogoAgregarProducto( )
    {
        DialogoNuevoProducto dnp = new DialogoNuevoProducto( this );
        dnp.setVisible( true );
    }

    /**
     * Agrega un nuevo producto al almacón. <br>
     * Si el producto se agrega satisfactoriamente entonces el diólogo donde se introdujeron los datos debe cerrarse
     * @param dialogo El diólogo que se usó para ingresar los datos del nuevo producto
     * @param codigo El código del nuevo producto
     * @param nombre El nombre del nuevo producto
     * @param precio El precio del nuevo producto
     * @param unidades El nómero de unidades iniciales
     */
    void agregarProducto( DialogoNuevoProducto dialogo, String codigo, String nombre, int precio, int unidades )
    {
        try
        {
            almacen.agregarProducto( codigo, nombre, precio, unidades );
            dialogo.dispose( );
            actualizarProductos( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "No se pudo agregar el producto:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Actualiza la lista de productos comunicóndose con el servidor y muestra la lista actualizada
     */
    void refrescar( )
    {
        try
        {
            actualizarProductos( );
        }
        catch( Exception e )
        {
            JOptionPane.showMessageDialog( this, "No se pudo refrescar la lista de productos:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Actualiza la lista de productos mostrada
     */
    private void actualizarProductos( )
    {
        try
        {
            panelProductos.actualizarProductos( almacen.listarProductos( ) );
        }
        catch( SQLException e )
        {
            JOptionPane.showMessageDialog( this, "No se pudo actualizar la lista de productos:\n" + e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
            e.printStackTrace( );
        }
    }

    /**
     * Mótodo para la extensión 1
     */
    void reqFuncOpcion1( )
    {
        String resultado = almacen.metodo1( );
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
        String resultado = almacen.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }
}