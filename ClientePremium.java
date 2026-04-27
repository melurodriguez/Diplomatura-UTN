import java.util.Date;

public class ClientePremium extends Cliente {

    private int limitePreferencial;
    private String ejecutivoCuenta;
    private String beneficiosAdicionales;

    public ClientePremium(){

    }

    public ClientePremium(int clienteID, String direccion, String telefono, String correoElectronico, Date fechaAlta,double saldo,int limitePreferencial, String ejecutivoCuenta, String beneficiosAdicionales){
        super(clienteID, direccion, telefono, correoElectronico, fechaAlta, saldo);
        this.limitePreferencial=limitePreferencial;
        this.ejecutivoCuenta=ejecutivoCuenta;
        this.beneficiosAdicionales=beneficiosAdicionales;
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
    public void setLimitePreferencial(int limitePreferencial){
        this.limitePreferencial=limitePreferencial;
    }

    public void setEjecutivoCuenta(String ejecutivoCuenta){
        this.ejecutivoCuenta=ejecutivoCuenta;
    }
    public void setBeneficiosAdicionales(String beneficiosAdicionales){
        this.beneficiosAdicionales=beneficiosAdicionales;
    }


    //GETTERS
    public int getLimitePreferencial(){
        return limitePreferencial;
    }
    public String getEjecutivoCuenta(){
        return ejecutivoCuenta;
    }
    public String getBeneficiosAdicionales(){
        return beneficiosAdicionales;
    }
   
    
}
