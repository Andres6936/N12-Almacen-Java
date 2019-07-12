/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad de los Andes (Bogotó - Colombia)
 * Departamento de Ingenieróa de Sistemas y Computación 
 * Licenciado bajo el esquema Academic Free License versión 2.1
 *
 * Proyecto Cupi2 (http://cupi2.uniandes.edu.co)
 * Ejercicio: n12_almacen
 * Autor: Mario Sónchez - 6/11/2005
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.almacen.cliente.mundo;

/**
 * Esta excepción se lanza cuando una operación no se puede realizar porque el almacón reportó un error
 */
public class AlmacenExcepcion extends Exception
{
    /**
     * Construye la excepción con el mensaje indicado
     * @param mensaje Mensaje que describe el error
     */
    public AlmacenExcepcion( String mensaje )
    {
        super( mensaje );
    }
}