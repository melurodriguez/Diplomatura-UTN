import java.util.Date;

public abstract class Cliente implements Calificable{
    protected int clienteID;
    protected String direccion;
    protected String telefono;
    protected String correoElectronico;
    protected Date fechaAlta;
    protected double saldo;

    //Constructo vacio
    public Cliente(){
    }

    //Constructor con parametros
    public Cliente(int clienteID, String direccion, String telefono, String correoElectronico, Date fechaAlta, double saldo){
        this.clienteID=clienteID;
        this.direccion=direccion;
        this.telefono=telefono;
        this.correoElectronico=correoElectronico;
        this.fechaAlta=fechaAlta;
        this.saldo= saldo;
    }

    @Override
    public String toString() {
        return "Cliente ID: " + clienteID + ", Direccion: " + direccion;
    }


}
