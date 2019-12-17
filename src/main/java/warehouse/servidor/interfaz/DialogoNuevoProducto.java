package warehouse.servidor.interfaz;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Es el diólogo que se usa para agregar los datos de un nuevo producto
 */
class DialogoNuevoProducto extends JDialog
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Es una referencia a la ventana principal del administrador
     */
    private InterfazAlmacen ventanaPrincipal;

    // -----------------------------------------------------------------
    // Atributos de la Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel donde se ingresan los datos del nuevo producto
     */
    private PanelDatosNuevoProducto panelDatos;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el diólogo e inicializa sus componentes
     * @param ia Es una referencia a la ventana principal del administrador
     */
    DialogoNuevoProducto( InterfazAlmacen ia )
    {
        super( ia, true );
        ventanaPrincipal = ia;

        panelDatos = new PanelDatosNuevoProducto( );

        // Es el panel con los botones que controlan el diólogo
        PanelBotonesNuevoProducto panelBotones = new PanelBotonesNuevoProducto( this );

        add( panelDatos, BorderLayout.CENTER );
        add( panelBotones, BorderLayout.SOUTH );

        setResizable( false );
        setTitle( "Agregar Producto" );
        pack( );
    }

    // -----------------------------------------------------------------
    // Mótodos
    // -----------------------------------------------------------------

    /**
     * Agrega un nuevo producto al almacón
     */
    void agregarProducto( )
    {
        try
        {
            String codigo = panelDatos.darCodigo( );
            String nombre = panelDatos.darNombre( );
            int precio = Integer.parseInt( panelDatos.darPrecio( ) );
            int unidades = Integer.parseInt( panelDatos.darUnidades( ) );

            ventanaPrincipal.agregarProducto( this, codigo, nombre, precio, unidades );
        }
        catch( NumberFormatException nfe )
        {
            JOptionPane.showMessageDialog( this, "El precio y las unidades deben ser nómeros enteros", "Error", JOptionPane.ERROR_MESSAGE );
        }
    }
}