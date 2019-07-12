/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotó - Colombia)
 * Departamento de Ingenieróa de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_almacen
 * Autor: Mario Sónchez - 06-nov-2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.almacen.servidor.interfaz;

import java.awt.BorderLayout;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import uniandes.cupi2.almacen.servidor.mundo.Almacen;
import uniandes.cupi2.almacen.servidor.mundo.ServidorAlmacen;

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
     * Es la barra con el menó para la aplicación
     */
    private BarraMenuAdministrador barraMenu;

    /**
     * Es el panel donde se muestran los productos que hay actualmente en el almacón
     */
    private PanelProductosAdministrador panelProductos;

    /**
     * Es el panel donde se muestran los botones para controlar la aplicación del administrador
     */
    private PanelBotonesAdministrador panelBotones;

    /**
     * Es el panel con los botones para los puntos de extensión
     */
    private PanelExtensionAlmacen panelExtension;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Descripción <br>
     * @param elAlmacen Es el almacón que se va a usar en esta aplicación
     * @throws Exception Se lanza esta excepción si hay problemas cargando el archivo de propiedades
     */
    public InterfazAlmacen( Almacen elAlmacen ) throws Exception
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
     * Este mótodo sirve para construir la forma inicializando cada uno de los componentes. <br>
     * <b>pre: </b> La ventana estó vacóa <br>
     * <b>post: </b> Se inicializaron los componentes gróficos de la aplicación
     */
    private void construirForma( )
    {
        setLayout( new BorderLayout( ) );

        panelProductos = new PanelProductosAdministrador( );
        add( panelProductos, BorderLayout.CENTER );

        panelBotones = new PanelBotonesAdministrador( this );
        add( panelBotones, BorderLayout.EAST );

        panelExtension = new PanelExtensionAlmacen( this );
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
        barraMenu = new BarraMenuAdministrador( this );
        setJMenuBar( barraMenu );
    }

    /**
     * Este mótodo desconecta el administrador del almacón y cierra la aplicación
     */
    public void salir( )
    {
        try
        {
            almacen.desconectarBD( );
        }
        catch( SQLException e )
        {
            JOptionPane.showMessageDialog( this, e.getMessage( ), "Error", JOptionPane.ERROR_MESSAGE );
        }
        System.exit( 0 );
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
     * Muestra el diólogo para agregar un producto
     */
    public void mostrarDialogoAgregarProducto( )
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
    public void agregarProducto( DialogoNuevoProducto dialogo, String codigo, String nombre, int precio, int unidades )
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
    public void refrescar( )
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

    /**
     * Actualiza la lista de productos mostrada
     */
    public void actualizarProductos( )
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

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Mótodo para la extensión 1
     */
    public void reqFuncOpcion1( )
    {
        String resultado = almacen.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Mótodo para la extensión 2
     */
    public void reqFuncOpcion2( )
    {
        String resultado = almacen.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este mótodo ejecuta la aplicación, creando una nueva interfaz
     * @param args
     */
    public static void main( String[] args )
    {
        try
        {
            ServidorAlmacen servidor = new ServidorAlmacen( "./data/almacen.properties" );
            InterfazAlmacen interfaz = new InterfazAlmacen( servidor.darAlmacen( ) );
            interfaz.setVisible( true );
            servidor.recibirConexiones( );
        }
        catch( SQLException e )
        {
            SQLException s2 = e;
            while( s2 != null )
            {
                System.out.println( s2.getMessage( ) );
                s2 = s2.getNextException( );
            }
        }
        catch( Exception e )
        {
            System.out.println( e.getMessage( ) );
        }
    }
}