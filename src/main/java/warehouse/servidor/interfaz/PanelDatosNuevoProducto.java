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

package warehouse.servidor.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Es el panel donde se introducen los datos del nuevo producto
 */
public class PanelDatosNuevoProducto extends JPanel
{
    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------
    /**
     * Es el campo para el código del producto
     */
    private JTextField txtCodigo;

    /**
     * Es el campo para el nombre del producto
     */
    private JTextField txtNombre;

    /**
     * Es el campo para el precio del producto
     */
    private JTextField txtPrecio;

    /**
     * Es el campo para el nómero de unidades iniciales del producto
     */
    private JTextField txtUnidades;

    /**
     * Etiqueta Nombre
     */
    private JLabel etiquetaNombre;

    /**
     * Etiqueta Precio
     */
    private JLabel etiquetaPrecio;

    /**
     * Etiqueta Unidades Iniciales
     */
    private JLabel etiquetaUnidades;

    /**
     * Etiqueta Código
     */
    private JLabel etiquetaCodigo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     */
    public PanelDatosNuevoProducto( )
    {
        setLayout( new GridBagLayout( ) );

        GridBagConstraints gbcEtiqueta = new GridBagConstraints( 0, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets( 5, 5, 5, 5 ), 0, 0 );

        etiquetaCodigo = new JLabel( "Código:" );
        add( etiquetaCodigo, gbcEtiqueta );

        gbcEtiqueta.gridy = 1;
        etiquetaNombre = new JLabel( "Nombre:" );
        add( etiquetaNombre, gbcEtiqueta );

        gbcEtiqueta.gridy = 2;
        etiquetaPrecio = new JLabel( "Precio:" );
        add( etiquetaPrecio, gbcEtiqueta );

        gbcEtiqueta.gridy = 3;
        etiquetaUnidades = new JLabel( "Unidades Iniciales:" );
        add( etiquetaUnidades, gbcEtiqueta );

        GridBagConstraints gbcCampo = new GridBagConstraints( 1, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets( 5, 5, 5, 5 ), 0, 0 );

        txtCodigo = new JTextField( "", 10 );
        add( txtCodigo, gbcCampo );

        gbcCampo.gridy = 1;
        txtNombre = new JTextField( "", 10 );
        add( txtNombre, gbcCampo );

        gbcCampo.gridy = 2;
        txtPrecio = new JTextField( "", 10 );
        add( txtPrecio, gbcCampo );

        gbcCampo.gridy = 3;
        txtUnidades = new JTextField( "", 10 );
        add( txtUnidades, gbcCampo );
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
        return txtCodigo.getText( );
    }

    /**
     * Retorna el nombre del producto
     * @return el nombre del producto
     */
    public String darNombre( )
    {
        return txtNombre.getText( );
    }

    /**
     * Retorna el precio del producto
     * @return el precio del producto
     */
    public String darPrecio( )
    {
        return txtPrecio.getText( );
    }

    /**
     * Retorna el nómero de unidades iniciales del producto
     * @return el nómero de unidades iniciales del producto
     */
    public String darUnidades( )
    {
        return txtUnidades.getText( );
    }
}