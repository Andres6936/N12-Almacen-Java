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

package uniandes.cupi2.almacen.servidor.interfaz;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import uniandes.cupi2.almacen.servidor.mundo.Producto;

/**
 * Es el panel donde se muestran al administrador los productos que hay actualmente en el almacón
 */
public class PanelProductosAdministrador extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Es la lista donde se muestran los productos disponibles en el almacón
     */
    private JList listaProductos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     */
    public PanelProductosAdministrador( )
    {
        setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
        setLayout( new BorderLayout( ) );

        JScrollPane scroll = new JScrollPane( );
        scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scroll.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );

        listaProductos = new JList( );
        listaProductos.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        scroll.getViewport( ).add( listaProductos );
        add( scroll );
    }

    // -----------------------------------------------------------------
    // Mótodos
    // -----------------------------------------------------------------

    /**
     * Retorna el producto seleccionado actualmente.<br>
     * Si no hay ningón producto seleccionado retorna null
     * @return producto seleccionado
     */
    public Producto darProductoSeleccionado( )
    {
        return ( Producto )listaProductos.getSelectedValue( );
    }

    /**
     * Actualiza la lista de productos mostrados
     * @param productos La lista con los productos que deben mostrarse
     */
    public void actualizarProductos( ArrayList productos )
    {
        listaProductos.setListData( productos.toArray( ) );
    }
}