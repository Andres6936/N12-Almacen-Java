package warehouse.cliente.mundo;

import java.io.*;
import java.net.Socket;
import java.util.Properties;

/**
 * Esta clase representa un punto de venta del almacón
 */
public class PuntoDeVenta
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Indica un resultado exitoso en la ejecución de un comando
     */
    private final static String RESULTADO_OK = "Ok";

    /**
     * Indica un error en la ejecución de un comando
     */
    public final static String RESULTADO_ERROR = "Error";

    /**
     * Comando para registrar la venta de un producto
     */
    private final static String REGISTAR_VENTA = "registrar_venta";

    /**
     * Comando para desconectar un cliente
     */
    private final static String DESCONECTAR = "desconectar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * Es el canal de comunicación a travós del cual el punto de venta se comunica con el almacón
     */
    private Socket socket;

    /**
     * Es el conjunto de propiedades que contienen la configuración de la aplicación
     */
    private Properties configuracion;

    /**
     * Es el valor total de la compra acumulado hasta el momento
     */
    private int total;

    /**
     * Flujo de salida asociado con el canal de comunicación
     */
    private PrintWriter out;

    /**
     * Flujo de entrada asociado con el canal de comunicación
     */
    private BufferedReader in;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el punto de venta y carga la configuración de un archivo de propiedades
     * @param archivo El archivo de propiedades que contiene la configuración que requiere el punto de venta
     * @throws Exception Se lanza esta excepción si hay problemas cargando el archivo de propiedades
     */
    public PuntoDeVenta( String archivo ) throws Exception
    {
        cargarConfiguracion( archivo );
        total = 0;
    }

    // -----------------------------------------------------------------
    // Mótodos
    // -----------------------------------------------------------------

    /**
     * Carga la configuración a partir de un archivo de propiedades
     * @param archivo El archivo de propiedades que contiene la configuración que requiere el punto de venta
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

    /**
     * Conecta el punto de venta con el almacón
     * @throws Exception Se lanza esta excepción si hay problemas en la comunicación con el almacón
     */
    public void conectar( ) throws Exception
    {
        String ip = configuracion.getProperty( "almacen.ip" );
        int puerto = Integer.parseInt( configuracion.getProperty( "almacen.puerto" ) );

        socket = new Socket( ip, puerto );
        out = new PrintWriter( socket.getOutputStream( ), true );
        in = new BufferedReader( new InputStreamReader( socket.getInputStream( ) ) );
    }

    /**
     * Desconecta el punto de venta del almacón
     */
    public void desconectar( )
    {
        try
        {
            if( socket != null )
            {
                out.println( PuntoDeVenta.DESCONECTAR );
                in.readLine( );
                in.close( );
                out.close( );
                socket.close( );
            }
        }
        catch ( IOException ignored )
        {
        }
        socket = null;
    }

    /**
     * Registra la venta de un producto<br>
     * El punto de venta debe consultar al almacón el precio del producto dado su código.
     * @param codigo El código del producto que se va a comprar.
     * @throws AlmacenExcepcion Se lanza esta excepción si hay un problema con el código indicado
     * @throws Exception Se lanza esta excepción si hay problemas en la comunicación con el almacón
     */
    public void registrarVenta( String codigo ) throws AlmacenExcepcion, Exception
    {
        out.println( PuntoDeVenta.REGISTAR_VENTA );
        out.println( codigo );

        String resultado = in.readLine( );
        if( PuntoDeVenta.RESULTADO_OK.equals( resultado ) )
        {
            int precioProducto = Integer.parseInt( in.readLine( ) );
            total += precioProducto;
        }
        else
        {
            throw new AlmacenExcepcion( in.readLine( ) );
        }
    }

    /**
     * Da el valor total de la compra hasta el momento
     * @return total
     */
    public int darTotal( )
    {
        return total;
    }

    /**
     * Inicia una nueva compra haciendo que el total acumulado sea nuevamente 0
     */
    public void limpiar( )
    {
        total = 0;
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
     * Mótodo para la extensión 2
     * @return respuesta2
     */
    public String metodo2( )
    {
        return "respuesta2";
    }
}