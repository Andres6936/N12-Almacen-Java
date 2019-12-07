/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id: ManejadorPuntoVenta.java,v 1.3 2007/10/23 15:45:11 dm.puentes64-2 Exp $
 * Universidad de los Andes (Bogotó - Colombia)
 * Departamento de Ingenieróa de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_almacen
 * Autor: Mario Sónchez - 6/11/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package warehouse.servidor.mundo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


/**
 * Clase que representa la conexion de un punto de venta. Esta clase se ejecuta sobre un nuevo hilo de ejecución
 */
public class ManejadorPuntoVenta extends Thread
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------
    /**
     * Comando para desconectar un cliente
     */
    public final static String DESCONECTAR = "desconectar";

    /**
     * Comando para registrar la venta de un producto
     */
    public final static String REGISTAR_VENTA = "registrar_venta";

    /**
     * Indica un error en la ejecución de un comando
     */
    public final static String RESULTADO_ERROR = "Error";

    /**
     * Indica un resultado exitoso en la ejecución de un comando
     */
    public final static String RESULTADO_OK = "Ok";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------
    /**
     * El flujo de salida asociado con el canal de comunicación
     */
    private PrintWriter out;

    /**
     * El flujo de entrada asociado con el canal de comunicación
     */
    private BufferedReader in;

    /**
     * Asociación hacia el almacón, responsable de manejar el inventario de productos.
     */
    private Almacen almacen;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Mótodo constructor de la clase
     * @param in2 Es el flujo de entrada asociado con el canal de comunicación
     * @param out2 Es el flujo de salida asociado con el canal de comunicación
     * @param nAlmacen Es el almacón
     */
    public ManejadorPuntoVenta( BufferedReader in2, PrintWriter out2, Almacen nAlmacen )
    {
        in = in2;
        out = out2;
        almacen = nAlmacen;
    }

    // -----------------------------------------------------------------
    // Mótodos
    // -----------------------------------------------------------------

    /**
     * Mótodo que se ejecuta cuando se quiere comenzar el hilo de ejecución
     */
    public void run( )
    {
        try
        {
            recibirComandosPuntoDeVenta( );
        }
        catch( IOException e )
        {
            e.printStackTrace( );
        }
    }

    /**
     * Este mótodo se encarga de recibir comandos enviados desde el punto de venta
     * @throws IOException Se lanza esta excepción si hay problemas con la comunicación
     */
    private void recibirComandosPuntoDeVenta( ) throws IOException
    {
        boolean conexionTerminada = false;
        while( !conexionTerminada )
        {
            // Leer un comando
            String comando = in.readLine( );

            // Comando para desconectar al cliente
            if( ManejadorPuntoVenta.DESCONECTAR.equals( comando ) )
            {
                out.println( ManejadorPuntoVenta.RESULTADO_OK );
                conexionTerminada = true;
            }
            // Comando para registrar una venta
            else if( ManejadorPuntoVenta.REGISTAR_VENTA.equals( comando ) )
            {
                registrarVenta( );
            }
            // Comando no reconocido
            else
            {
                out.println( ManejadorPuntoVenta.RESULTADO_ERROR );
                out.println( "Comando no reconocido: " + comando );
            }
        }
    }

    /**
     * Este mótodo se encarga de registrar la venta de un producto actualizando la cantidad de unidades disponibles. <br>
     * Se espera que lleguen parómetros de la siguiente forma: <br>
     * código<br>
     */
    private void registrarVenta( )
    {
        try
        {
            // Procesar los parómetros
            String codigo = in.readLine( );

            // Buscar el producto
            Producto p = almacen.consultarProducto( codigo );

            // Contactar al almacón y registrar la venta del producto
            almacen.registrarVenta( codigo );

            // Enviar el mensaje de OK y el precio del producto
            out.println( ManejadorPuntoVenta.RESULTADO_OK );
            out.println( "" + p.darPrecio( ) );
        }
        catch( AlmacenExcepcion e )
        {
            out.println( ManejadorPuntoVenta.RESULTADO_ERROR );
            out.println( e.getMessage( ) );
        }
        catch( SQLException e )
        {
            out.println( ManejadorPuntoVenta.RESULTADO_ERROR );
            out.println( "Problemas en la comunicación con la base de datos" );
        }
        catch( IOException e )
        {
            out.println( ManejadorPuntoVenta.RESULTADO_ERROR );
            out.println( "No se pudo leer los parómetros" );
        }
    }
}