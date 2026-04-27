import java.util.Date;

public class ClienteEmpresa extends Cliente {
    private String razonSocial;
    private String cuit;
    private String actividad;
    private String representanteLegal;

    public ClienteEmpresa(){

    }

    public ClienteEmpresa(int clienteID, String direccion, String telefono, String correoElectronico, Date fechaAlta,double saldo,String razonSocial, String cuit, String actividad, String representanteLegal){
        super(clienteID, direccion, telefono, correoElectronico, fechaAlta, saldo);
        this.razonSocial=razonSocial;
        this.cuit=cuit;
        this.actividad=actividad;
        this.representanteLegal=representanteLegal;
    }

    @Override
    public CalificacionCliente calcularCalificacion() {
        if (saldo >= 10000){
            return CalificacionCliente.ALTO;
        }

        if (saldo >= 5000){
            return CalificacionCliente.AVANZADO;
        }

        if (saldo >= 3000){
            return CalificacionCliente.MEDIO;
        }

        if (saldo < 3000){
            return CalificacionCliente.BAJO;
        }
        return CalificacionCliente.BAJO;
    }

    //SETTERS
    public void setRazonSocial(String razonSocial){
        this.razonSocial=razonSocial;
    }

    public void setCuit(String cuit){
        this.cuit=cuit;
    }
    public void setActividad(String actividad){
        this.actividad=actividad;
    }
    public void setRepresentanteLegal(String representanteLegal){
        this.representanteLegal=representanteLegal;
    }

    //GETTERS
    public String getRazonSocial(){
        return razonSocial;
    }
    public String getCuit(){
        return cuit;
    }
    public String getActividad(){
        return actividad;
    }
    public String getRepresentanteLegal(){
        return representanteLegal;
    }

}
