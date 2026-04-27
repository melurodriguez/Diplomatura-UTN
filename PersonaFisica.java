import java.util.Date;

public class PersonaFisica extends Cliente{
    private String dni;
    private String nombre;
    private Date fechaNacimineto;
    private String profesion;
    private double ingresosDeclarados;

    public PersonaFisica(){

    }

    public PersonaFisica(int clienteID, String direccion, String telefono, String correoElectronico, Date fechaAlta,double saldo,String dni, String nombre, Date fechaNacimiento, String profesion, double ingresosDeclarados){
        super(clienteID, direccion, telefono, correoElectronico, fechaAlta, saldo);
        this.dni= dni;
        this.nombre= nombre;
        this.fechaNacimineto= fechaNacimiento;
        this.profesion= profesion;
        this.ingresosDeclarados= ingresosDeclarados;
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
    public void setDni(String dni){
        this.dni=dni;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public void setFechaNacimiento(Date fechaNacimiento){
        this.fechaNacimineto=fechaNacimiento;
    }

    public void setProfesion(String profesion){
        this.profesion=profesion;
    }

    public void setIngresosDeclarados(double ingresosDeclarados){
        this.ingresosDeclarados=ingresosDeclarados;
    }


    //GETTERS
    public String getDni(){
        return dni;
    }

    public String getNombre(){
        return nombre;
    }

    public Date getFechaNacimiento( ){
        return fechaNacimineto;
    }

    public String getProfesion(){
        return profesion;
    }

    public double getIngresosDeclarados(){
        return ingresosDeclarados;
    }
   
}
