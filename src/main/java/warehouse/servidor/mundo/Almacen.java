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

package warehouse.servidor.mundo;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Esta es la clase que representa un almacón y se encarga de la comunicación con la base de datos
 */
public class Almacen
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la conexión a la base de datos
     */
    private Connection conexion;

    /**
     * Es el conjunto de propiedades que contienen la configuración de la aplicación
     */
    private Properties configuracion;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el almacón
     * @param propiedades Las propiedades para la configuración del almacón
     */
    public Almacen( Properties propiedades )
    {
        configuracion = propiedades;

        // Establecer la ruta donde va a estar la base de datos.
        // Derby utiliza la propiedad del sistema derby.system.home para saber donde estón los datos
        File directorioData = new File( "./data/DB" );
        System.setProperty( "derby.system.home", directorioData.getAbsolutePath( ) );
    }

    // -----------------------------------------------------------------
    // Mótodos
    // -----------------------------------------------------------------

    /**
     * Conecta el almacón a la base de datos
     * @throws SQLException Se lanza esta excepción si hay problemas realizando la operación
     * @throws Exception Se lanza esta excepción si hay problemas con los drivers
     */
    public void conectarABD( ) throws SQLException, Exception
    {
        String driver = configuracion.getProperty( "almacen.db.driver" );
        Class.forName( driver ).newInstance( );

        String url = configuracion.getProperty( "almacen.db.url" );
        conexion = DriverManager.getConnection( url );
    }

    /**
     * Desconecta el almacón de la base de datos y detiene la base de datos
     * @throws SQLException Se lanza esta excepción si hay problemas realizando la operación
     */
    public void desconectarBD( ) throws SQLException
    {
        conexion.close( );
        String shutdownURL = configuracion.getProperty( "almacen.db.shutdown" );
        try
        {
            DriverManager.getConnection( shutdownURL );
        }
        catch( SQLException e )
        {
            // Al bajar la base de datos DEBE producirse una excepción
        }
    }

    /**
     * Si la tabla de productos no ha sido creada, lo hace y le ingresa algunos productos iniciales
     * @throws SQLException Se lanza esta excepción si hay problemas creando la tabla
     */
    public void inicializarTabla( ) throws SQLException
    {
        Statement s = conexion.createStatement( );
        boolean crearTabla = false;
        try
        {
            // Verificar si ya existe la tabla productos
            s.executeQuery( "Select * from PRODUCTO where 1=2" );
        }
        catch( SQLException se )
        {
            // La excepción se lanza si la tabla PRODUCTO no existe
            crearTabla = true;
        }

        // Se crea una nueva tabla de productos vacóa
        if( crearTabla )
        {
            s.execute( "create table PRODUCTO(codigo varchar(13), nombre varchar(40), precio int, unidades int, PRIMARY KEY (codigo))" );

            // Inserta algunos productos en la base de datos
            String sqlInsert = "insert into PRODUCTO (codigo, nombre, precio, unidades) VALUES ";
            String sql1 = sqlInsert + "('7706821000108','El Tiempo', 1200, 100)";
            String sql2 = sqlInsert + "('9788466616447','Libro', 24900, 7)";
            String sql3 = sqlInsert + "('9788466616928','Revista', 4900, 15)";
            String sql4 = sqlInsert + "('7701002000212','Pan Tajado Carulla', 2100, 25)";
            s.executeUpdate( sql1 );
            s.executeUpdate( sql2 );
            s.executeUpdate( sql3 );
            s.executeUpdate( sql4 );
        }
        s.close( );
    }

    /**
     * Este mótodo se encarga de agregar un producto.
     * @param codigo Es el código del nuevo producto
     * @param nombre Es el nombre del nuevo producto
     * @param precio Es el precio de una unidad del nuevo producto
     * @param unidades Es el nómero inicial de unidades disponibles
     * @throws AlmacenExcepcion Se lanza esta excepción si ya hay un producto en la base de datos con el mismo código
     * @throws SQLException Se lanza esta excepción si hay problemas en la comunicación con la base de datos
     */
    public void agregarProducto( String codigo, String nombre, int precio, int unidades ) throws AlmacenExcepcion, SQLException
    {
        Statement st = conexion.createStatement( );

        String sql1 = "SELECT nombre, precio, unidades FROM producto WHERE codigo ='" + codigo + "'";

        ResultSet resultado = st.executeQuery( sql1 );
        if( resultado.next( ) )
        {
            resultado.close( );
            st.close( );
            throw new AlmacenExcepcion( "No se pudo crear un nuevo producto con el código indicado (" + codigo + "): el código ya estó en uso" );
        }
        else
        {
            String sql2 = "INSERT INTO Producto (codigo, nombre, precio, unidades) VALUES " + "('" + codigo + "','" + nombre + "'," + precio + "," + unidades + ")";
            int insertados = st.executeUpdate( sql2 );
            st.close( );

            if( insertados != 1 )
            {
                throw new AlmacenExcepcion( "No se pudo crear un nuevo producto con el código indicado (" + codigo + "): el código ya estó en uso" );
            }
        }
    }

    /**
     * Este mótodo se encarga de registrar la venta de un producto actualizando la cantidad de unidades disponibles.
     * @param codigo Es el producto del cual se quiere registrar una venta
     * @throws AlmacenExcepcion Se lanza esta excepción si no existe en la base de datos un producto con el código indicado
     * @throws SQLException Se lanza esta excepción si hay problemas en la comunicación con la base de datos
     */
    public void registrarVenta( String codigo ) throws AlmacenExcepcion, SQLException
    {
        Statement st = conexion.createStatement( );

        String sql = "UPDATE producto SET unidades = unidades - 1 where codigo ='" + codigo + "'";
        int modificados = st.executeUpdate( sql );
        st.close( );

        if( modificados != 1 )
        {
            throw new AlmacenExcepcion( "No se pudo registrar la venta del producto " + codigo + ". Verifique el código por favor." );
        }
    }

    /**
     * Este mótodo se encarga de buscar un producto y enviar la información sobre óste que se tiene en la base de datos <br>
     * @param codigo El código del producto del cual se estó buscando información
     * @return Retorna el producto buscado
     * @throws AlmacenExcepcion Se lanza esta excepción si no existe en la base de datos un producto con el código indicado
     * @throws SQLException Se lanza esta excepción si hay problemas en la comunicación con la base de datos
     */
    public Producto consultarProducto( String codigo ) throws AlmacenExcepcion, SQLException
    {
        String sql = "SELECT nombre, precio, unidades FROM producto WHERE codigo ='" + codigo + "'";

        Statement st = conexion.createStatement( );
        ResultSet resultado = st.executeQuery( sql );
        if( resultado.next( ) )
        {
            String nombre = resultado.getString( 1 );
            int precio = resultado.getInt( 2 );
            int unidades = resultado.getInt( 3 );

            Producto p = new Producto( codigo, nombre, precio, unidades );

            resultado.close( );
            st.close( );
            return p;
        }
        else
        {
            resultado.close( );
            st.close( );
            throw new AlmacenExcepcion( "No se encontró un producto con el código indicado (" + codigo + ")" );
        }
    }

    /**
     * Este mótodo se encarga de enviar la información sobre todos los productos que estón en la base de datos.
     * @return Retorna la lista de productos
     * @throws SQLException Se lanza esta excepción si hay problemas en la comunicación con la base de datos
     */
    public ArrayList listarProductos( ) throws SQLException
    {
        String sql = "SELECT codigo, nombre, precio, unidades FROM PRODUCTO";

        Statement st = conexion.createStatement( );
        ResultSet resultado = st.executeQuery( sql );
        ArrayList productos = new ArrayList( );
        while( resultado.next( ) )
        {
            String codigo = resultado.getString( 1 );
            String nombre = resultado.getString( 2 );
            int precio = resultado.getInt( 3 );
            int unidades = resultado.getInt( 4 );
            Producto p = new Producto( codigo, nombre, precio, unidades );
            productos.add( p );
        }

        resultado.close( );
        st.close( );
        return productos;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Mótodo para la extensión 1
     * @return respuesta1
     */
    public String metodo1( )
    {
        return "respuesta1";
    }

    /**
     * Mótodo para la extensión2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "respuesta2";
    }
}