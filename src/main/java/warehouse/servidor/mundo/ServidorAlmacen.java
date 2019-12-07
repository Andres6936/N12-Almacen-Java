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

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Esta clase implementa el servidor del almacón. <br>
 * Se encarga de manejar las conexiones al programa servidor y de delegar en la clase Almacón la realización de las operaciones sobre la base de datos.
 */
public class ServidorAlmacen
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es el canal por el que escucha las solicitudes de conexión
     */
    private ServerSocket socket;

    /**
     * Es el conjunto de propiedades que contiene la configuración de la aplicación
     */
    private Properties configuracion;

    /**
     * Es el almacón con el que se encuentra relacionado este servidor
     */
    private Almacen almacen;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el servidor del almacón
     * @param archivo El archivo de propiedades que contiene la configuración para el almacón
     * @throws Exception Se lanza esta excepción si hay problemas con el archivo de propiedades o hay problemas en la conexión a la base de datos
     * @throws SQLException Se lanza esta excepción si hay problemas conectando el almacón a la base de datos.
     */
    public ServidorAlmacen( String archivo ) throws SQLException, Exception
    {
        cargarConfiguracion( archivo );
        almacen = new Almacen( configuracion );
        almacen.conectarABD( );
        almacen.inicializarTabla( );
    }

    /**
     * Carga la configuración a partir de un archivo de propiedades
     * @param archivo El archivo de propiedades que contiene la configuración que requiere el almacón
     * @throws Exception Se lanza esta excepción si hay problemas cargando el archivo de propiedades
     */
    private void cargarConfiguracion( String archivo ) throws Exception
    {
        InputStream file = getClass( ).getClassLoader( ).getResourceAsStream( archivo );

        // The file can be null. Avoid this situation.
        assert file != null;

        configuracion = new Properties( );
        configuracion.load( file );
    }

    // -----------------------------------------------------------------
    // Mótodos
    // -----------------------------------------------------------------
    /**
     * Retorna el almacón usado por el servidor
     * @return almacón
     */
    public Almacen darAlmacen( )
    {
        return almacen;
    }

    /**
     * Este mótodo se encarga de recibir todas las conexiones entrantes al almacón hasta que el administrador envóe la seóal para detener el servidor
     */
    public void recibirConexiones( )
    {
        int puerto = Integer.parseInt( configuracion.getProperty( "almacen.puerto" ) );
        try
        {
            socket = new ServerSocket( puerto );
            while( true )
            {
                // Esperar una nueva conexión
                Socket socketCliente = socket.accept( );
                PrintWriter out = new PrintWriter( socketCliente.getOutputStream( ), true );
                BufferedReader in = new BufferedReader( new InputStreamReader( socketCliente.getInputStream( ) ) );
                // Crea e inicia un nuevo hilo de ejecución para atender la conexión
                ManejadorPuntoVenta manejador = new ManejadorPuntoVenta( in, out, almacen );
                manejador.start( );
            }
        }
        catch( IOException e )
        {
            e.printStackTrace( );
        }
    }
}