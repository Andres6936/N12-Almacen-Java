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

package uniandes.cupi2.almacen.test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import junit.framework.TestCase;
import uniandes.cupi2.almacen.servidor.mundo.Almacen;
import uniandes.cupi2.almacen.servidor.mundo.Producto;

/**
 * Esta es la clase usada para verificar que los mótodos de la clase Almacen estón correctamente implementados
 */
public class AlmacenTest extends TestCase
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es la clase donde se harón las pruebas
     */
    private Almacen almacen;

    /**
     * Es el conjunto de propiedades para configurar las pruebas
     */
    private Properties configuracion;

    /**
     * Es la conexión usada para las pruebas
     */
    private Connection conexionPruebas;

    // -----------------------------------------------------------------
    // Mótodos
    // -----------------------------------------------------------------

    /**
     * Inicializa la base de datos y construye un nuevo Almacen conectado a esta base de datos
     */
    private void setupEscenario1( )
    {
        almacen = null;
        File directorioData = new File( "./data" );
        System.setProperty( "derby.system.home", directorioData.getAbsolutePath( ) );
        configuracion = new Properties( );
        configuracion.setProperty( "almacen.db.url", "jdbc:derby:testAlmacen;create=true" );
        configuracion.setProperty( "almacen.test.url", "jdbc:derby:testAlmacen" );
        configuracion.setProperty( "almacen.db.driver", "org.apache.derby.jdbc.EmbeddedDriver" );
        configuracion.setProperty( "almacen.db.shutdown", "jdbc:derby:;shutdown=true" );

        // Conectar a la base de datos
        try
        {
            String driver = configuracion.getProperty( "almacen.db.driver" );
            Class.forName( driver ).newInstance( );
            String url = configuracion.getProperty( "almacen.db.url" );
            conexionPruebas = DriverManager.getConnection( url );
        }
        catch( Exception e )
        {
            fail( "Falló la conexión a la base de datos" );
        }

        try
        {
            // Crear la tabla de productos si es necesario
            crearTablas( );
        }
        catch( SQLException e1 )
        {
            fail( "No se pudo crear la tabla" );
        }

        try
        {
            // Limpia todos los datos existentes e Inserta los datos iniciales
            inicializarTablas( );
        }
        catch( SQLException e2 )
        {
            fail( "No se pudo crear la tabla" );
        }

        // Construir el almacón con las propiedades indicadas
        almacen = new Almacen( configuracion );
        try
        {
            almacen.conectarABD( );
        }
        catch( Exception e3 )
        {
            fail( "No se pudo conectar el almacón a la BD" );
        }
    }

    /**
     * Crea las tablas necesarias para el almacón
     * @throws SQLException Se lanza esta excepción si hay problemas creando la tabla
     */
    private void crearTablas( ) throws SQLException
    {
        Statement s = conexionPruebas.createStatement( );

        boolean crearTabla = false;
        try
        {
            // Verificar si ya existe la tabla productos
            s.executeQuery( "Select * from PRODUCTO" );
        }
        catch( SQLException se )
        {
            crearTabla = true;
        }

        if( crearTabla )
        {
            s.execute( "create table producto(codigo varchar(13), " + "nombre varchar(40), precio int, unidades int, PRIMARY KEY (codigo))" );
        }
        s.close( );
    }

    /**
     * Limpia las tablas del almacón y las inicializa
     * @throws SQLException Se lanza esta excepción si hay problemas inicializando la tabla
     */
    private void inicializarTablas( ) throws SQLException
    {
        Statement s = conexionPruebas.createStatement( );

        // Limpiar la tabla productos
        s.executeUpdate( "DELETE from PRODUCTO" );

        // Insertar los datos iniciales
        s.executeUpdate( "INSERT INTO Producto (codigo, nombre, precio, unidades) VALUES ('111','Arroz',6000,50)" );
        s.executeUpdate( "INSERT INTO Producto (codigo, nombre, precio, unidades) VALUES ('222','Azucar',3000 ,50)" );
        s.executeUpdate( "INSERT INTO Producto (codigo, nombre, precio, unidades) VALUES ('333','Sal',3000 ,40)" );
    }

    /**
     * Este mótodo, que se llama despuós de cada prueba, se encarga de detener el almacón
     * @throws Exception Se lanza esta excepción si hay problemas en la desconexión
     */
    protected void tearDown( ) throws Exception
    {
        // Desconectar el almacón de la base de datos
        try
        {
            if( almacen != null )
            {
                almacen.desconectarBD( );
            }
        }
        catch( Exception npe )
        {
            fail( "No se deberóa lanzar una excepción desconectando" );
        }
    }

    /**
     * Verifica el mótodo agregarProducto para el caso en el que se indican datos correctos
     */
    public void testAgregarProductoOk( )
    {
        // Configuración bósica
        setupEscenario1( );

        // Agregar un producto en la base de datos con un código que no existe
        try
        {
            almacen.agregarProducto( "999", "Helado", 10000, 10 );
        }
        catch( Exception e )
        {
            fail( "No se deberóa lanzar una excepción" );
        }

        try
        {
            // Verificar que se haya agregado correctamente el producto contactando a la base de datos
            // directamente
            Connection con = DriverManager.getConnection( configuracion.getProperty( "almacen.test.url" ) );
            Statement st = con.createStatement( );
            ResultSet rs = st.executeQuery( "SELECT nombre, precio, unidades FROM producto WHERE codigo = '999'" );
            if( !rs.next( ) )
            {
                rs.close( );
                st.close( );
                con.close( );

                fail( "No se encontró el producto 999" );
            }
            else
            {
                String nombre = rs.getString( 1 );
                int precio = rs.getInt( 2 );
                int unidades = rs.getInt( 3 );

                rs.close( );
                st.close( );
                con.close( );

                assertEquals( "El nombre del nuevo producto no es correcto", "Helado", nombre );
                assertEquals( "El precio del nuevo producto no es correcto", 10000, precio );
                assertEquals( "El nómero de unidades no es correcto", 10, unidades );
            }
        }
        catch( SQLException e1 )
        {
            fail( "No se deberóa lanzar una excepción conectando a la base de datos: " + e1.getMessage( ) );
        }

    }

    /**
     * Verifica el mótodo agregarProducto para el caso en el que se indica un código que ya existe en la base de datos
     */
    public void testAgregarProductoError( )
    {
        // Configuración bósica
        setupEscenario1( );

        // Agregar un nuevo producto con un código que ya existe en la base de datos
        try
        {
            almacen.agregarProducto( "111", "ProductoError", 123, 123 );
            fail( "Se deberóa lanzar una excepción porque el código 111 ya existe" );
        }
        catch( Exception e )
        {

        }
    }

    /**
     * Verifica el mótodo consultarProducto para el caso en el que se indican el código de un producto que existe
     */
    public void testConsultarProductoOk( )
    {
        // Configuración bósica
        setupEscenario1( );

        // Consultar un producto que existe
        try
        {
            Producto p = almacen.consultarProducto( "111" );
            assertEquals( "El nombre del producto es incorrecto", "Arroz", p.darNombre( ) );
            assertEquals( "El precio del producto es incorrecto", 6000, p.darPrecio( ) );
            assertEquals( "El nómero de unidades del producto es incorrecto", 50, p.darUnidades( ) );
        }
        catch( Exception e )
        {
            fail( "No se deberóa lanzar una excepción" );
        }
    }

    /**
     * Verifica el mótodo consultarProducto para el caso en el que se indica un código que no existe en la base de datos
     */
    public void testConsultarProductoError( )
    {
        // Configuración bósica
        setupEscenario1( );

        // Consultar un producto con un código que no existe en la base de datos
        try
        {
            almacen.consultarProducto( "999" );
            fail( "Se deberóa lanzar una excepción porque el código 999 no existe" );
        }
        catch( Exception e )
        {

        }
    }

    /**
     * Verifica el mótodo listarProductos
     */
    public void testListarProductos( )
    {
        // Configuración bósica
        setupEscenario1( );

        // Consultar los productos
        try
        {
            ArrayList productos = almacen.listarProductos( );
            assertEquals( "El nómero de productos no es correcto", 3, productos.size( ) );

            String cod0, cod1, cod2;

            Producto p = ( Producto )productos.get( 0 );
            cod0 = p.darCodigo( );
            assertTrue( "Se retornó un producto equivocado", p.darCodigo( ).equals( "111" ) || p.darCodigo( ).equals( "222" ) || p.darCodigo( ).equals( "333" ) );

            p = ( Producto )productos.get( 1 );
            cod1 = p.darCodigo( );
            assertTrue( "Se retornó un producto equivocado", p.darCodigo( ).equals( "111" ) || p.darCodigo( ).equals( "222" ) || p.darCodigo( ).equals( "333" ) );

            p = ( Producto )productos.get( 2 );
            cod2 = p.darCodigo( );
            assertTrue( "Se retornó un producto equivocado", p.darCodigo( ).equals( "111" ) || p.darCodigo( ).equals( "222" ) || p.darCodigo( ).equals( "333" ) );

            assertFalse( "Se listó dos veces el mismo producto", cod0.equals( cod1 ) || cod1.equals( cod2 ) || cod0.equals( cod2 ) );
        }
        catch( Exception e )
        {
            fail( "No se deberóa lanzar una excepción" );
        }
    }

    /**
     * Verifica el mótodo registrarVenta para el caso en el que se indica un código correcto
     */
    public void testRegistrarVentaOk( )
    {
        // Configuración bósica
        setupEscenario1( );

        // Registrar la venta de un producto existente en la base de datos
        try
        {
            almacen.registrarVenta( "111" );
        }
        catch( Exception e )
        {
            fail( "No se deberóa lanzar una excepción" );
        }

        try
        {
            // Verificar que se hayan disminuido correctamente las unidades contactando a la base de datos
            // directamente
            Connection con = DriverManager.getConnection( configuracion.getProperty( "almacen.test.url" ) );
            Statement st = con.createStatement( );
            ResultSet rs = st.executeQuery( "SELECT unidades FROM producto WHERE codigo = '111'" );
            if( !rs.next( ) )
            {
                rs.close( );
                st.close( );
                con.close( );

                fail( "No se encontró el producto 111" );
            }
            else
            {
                int unidades = Integer.parseInt( rs.getString( 1 ) );

                rs.close( );
                st.close( );
                con.close( );

                assertEquals( "El nómero de unidades no aumentó correctamente", 49, unidades );
            }
        }
        catch( SQLException e1 )
        {
            fail( "No se deberóa lanzar una excepción conectando a la base de datos: " + e1.getMessage( ) );
        }

    }

    /**
     * Verifica el mótodo registrarVenta para el caso en el que se indica un código que no existe en la base de datos
     */
    public void testRegistrarVentaError( )
    {
        // Configuración bósica
        setupEscenario1( );

        // Registrar la venta de un producto no existente en la base de datos
        try
        {
            almacen.registrarVenta( "999" );
            fail( "Se deberóa lanzar una excepción porque el código 999 no existe" );
        }
        catch( Exception e )
        {
        }
    }
}