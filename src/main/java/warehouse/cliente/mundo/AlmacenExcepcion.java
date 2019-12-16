package warehouse.cliente.mundo;

/**
 * Esta excepción se lanza cuando una operación no se puede realizar porque el almacón reportó un error
 */
class AlmacenExcepcion extends Exception
{
    /**
     * Construye la excepción con el mensaje indicado
     * @param mensaje Mensaje que describe el error
     */
    AlmacenExcepcion( String mensaje )
    {
        super( mensaje );
    }
}