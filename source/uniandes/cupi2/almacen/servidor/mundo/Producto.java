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

package uniandes.cupi2.almacen.servidor.mundo;

/**
 * Esta clase representa un producto en el inventario de la tienda
 */
public class Producto
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * El código del producto
     */
    private String codigo;

    /**
     * El nombre del producto
     */
    private String nombre;

    /**
     * El precio de una unidad del producto
     */
    private int precio;

    /**
     * El nómero de unidades disponibles del producto
     */
    private int unidades;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye un nuevo producto a partir de los datos suministrados
     * @param codigoP El código del producto
     * @param nombreP El nombre del producto
     * @param precioP El precio de una unidad del producto
     * @param unidadesP El nómero de unidades disponibles del producto
     */
    public Producto( String codigoP, String nombreP, int precioP, int unidadesP )
    {
        codigo = codigoP;
        nombre = nombreP;
        precio = precioP;
        unidades = unidadesP;
    }

    // -----------------------------------------------------------------
    // Mótodos
    // -----------------------------------------------------------------

    /**
     * Retorna el código del producto
     * @return el código del producto
     */
    public String darCodigo( )
    {
        return codigo;
    }

    /**
     * Retorna el nombre del producto
     * @return el nombre del producto
     */
    public String darNombre( )
    {
        return nombre;
    }

    /**
     * Retorna el precio del producto
     * @return el precio del producto
     */
    public int darPrecio( )
    {
        return precio;
    }

    /**
     * Retorna el nómero de unidades disponibles del producto
     * @return el nómero de unidades disponibles del producto
     */
    public int darUnidades( )
    {
        return unidades;
    }

    /**
     * Retorna una cadena para identificar al producto
     * @return una cadena para identificar al producto
     */
    public String toString( )
    {
        return nombre + " (" + codigo + ") - " + unidades + " unidades ($" + precio + ")";
    }
}