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
class PanelDatosNuevoProducto extends JPanel
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

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     */
    PanelDatosNuevoProducto( )
    {
        setLayout( new GridBagLayout( ) );

        GridBagConstraints gbcEtiqueta = new GridBagConstraints( 0, 0, 1, 1, 1, 0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets( 5, 5, 5, 5 ), 0, 0 );

        JLabel etiquetaCodigo = new JLabel( "Código:" );
        add( etiquetaCodigo, gbcEtiqueta );

        gbcEtiqueta.gridy = 1;

        JLabel etiquetaNombre = new JLabel( "Nombre:" );
        add( etiquetaNombre, gbcEtiqueta );

        gbcEtiqueta.gridy = 2;

        JLabel etiquetaPrecio = new JLabel( "Precio:" );
        add( etiquetaPrecio, gbcEtiqueta );

        gbcEtiqueta.gridy = 3;

        JLabel etiquetaUnidades = new JLabel( "Unidades Iniciales:" );
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
    String darCodigo( )
    {
        return txtCodigo.getText( );
    }

    /**
     * Retorna el nombre del producto
     * @return el nombre del producto
     */
    String darNombre( )
    {
        return txtNombre.getText( );
    }

    /**
     * Retorna el precio del producto
     * @return el precio del producto
     */
    String darPrecio( )
    {
        return txtPrecio.getText( );
    }

    /**
     * Retorna el nómero de unidades iniciales del producto
     * @return el nómero de unidades iniciales del producto
     */
    String darUnidades( )
    {
        return txtUnidades.getText( );
    }
}