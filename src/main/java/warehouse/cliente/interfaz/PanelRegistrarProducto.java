package warehouse.cliente.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * Este es el panel donde se registra un producto para agregarlo a la lista de la compra
 */
public class PanelRegistrarProducto extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    private static final String REGISTRAR = "registrar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal del punto de venta
     */
    private InterfazPuntoDeVenta ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el campo de texto donde se debe escribir el código del producto
     */
    private JTextField txtCodigo;

    /**
     * Es el campo donde se muestra el total de la compra
     */
    private JTextField txtTotal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el panel e inicializa sus componentes
     * @param ipv Es una referencia a la ventana principal del punto de venta
     */
    PanelRegistrarProducto( InterfazPuntoDeVenta ipv )
    {
        ventanaPrincipal = ipv;

        setLayout( new GridBagLayout( ) );
        setBorder( new CompoundBorder( new EmptyBorder( 5, 5, 5, 5 ), new TitledBorder( "Venta Actual" ) ) );

        GridBagConstraints gbc = new GridBagConstraints( 1, 0, 2, 1, 0, 0, GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );

        // Es la etiqueta Código del Producto
        JLabel etiquetaCodigo = new JLabel( "Código del Producto" );
        add( etiquetaCodigo, gbc );

        gbc = new GridBagConstraints( 1, 1, 2, 1, 0, 0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets( 5, 5, 5, 5 ), 0, 0 );
        txtCodigo = new JTextField( "", 13 );
        add( txtCodigo, gbc );

        gbc = new GridBagConstraints( 1, 3, 2, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );

        // Es el botón usado para registrar el producto indicado en la venta
        JButton botonRegistrar = new JButton( "Vender Unidad" );
        botonRegistrar.addActionListener( this );
        botonRegistrar.setActionCommand( REGISTRAR );
        add( botonRegistrar, gbc );

        gbc = new GridBagConstraints( 1, 2, 1, 1, 1, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets( 5, 5, 5, 5 ), 0, 0 );

        // Es la etiqueta Total
        JLabel etiquetaTotal = new JLabel( "Total: " );
        add( etiquetaTotal, gbc );

        gbc = new GridBagConstraints( 2, 2, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets( 5, 5, 5, 5 ), 0, 0 );

        txtTotal = new JTextField( "", 6 );
        txtTotal.setHorizontalAlignment( JTextField.RIGHT );
        txtTotal.setEditable( false );
        add( txtTotal, gbc );
    }

    // -----------------------------------------------------------------
    // Mótodos
    // -----------------------------------------------------------------

    /**
     * Cambia el total mostrado para la compra
     * @param total El valor total de la compra
     */
    void actualizarTotal( int total )
    {
        txtTotal.setText( "$" + total );
    }

    /**
     * Limpia el campo de texto del código de barras
     */
    void limpiar( )
    {
        txtCodigo.setText( "" );
    }

    /**
     * Es el mótodo que se ejecuta cuando se hace click sobre el botón de registrar
     * @param evento Es el evento del click sobre el botón
     */
    public void actionPerformed( ActionEvent evento )
    {
        String comando = evento.getActionCommand( );
        if( REGISTRAR.equals( comando ) )
        {
            if( txtCodigo.getText( ).length( ) != 0 )
            {
                ventanaPrincipal.registrarProducto( txtCodigo.getText( ) );
            }
        }
    }
}