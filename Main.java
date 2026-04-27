import java.util.Date;

public class Main {

    public static void main(String[] args) {


        ClienteEmpresa empresa1 = new ClienteEmpresa(
                1,
                "Av. Siempre Viva 123",
                "20111111112",
                "empresa1@email.com",
                new Date(),
                5000,
                "Instagram Empresa 1",
                "11111111",
                "Software",
                "Juan Perez"
        );

        ClienteEmpresa empresa2 = new ClienteEmpresa(
                2,
                "Av. Corrientes 456",
                "20222222223",
                "empresa2@email.com",
                new Date(),
                80000,
                "LinkedIn Empresa 2",
                "22222222",
                "Consultoría",
                "Ana Gomez"
        );

        CalificacionCliente calificacionEmpresa1= empresa1.calcularCalificacion();
        CalificacionCliente calificacionEmpresa2= empresa2.calcularCalificacion();

        //-----------------------------------------------------------//

        ClientePremium premium1 = new ClientePremium(
                3,
                "Calle Premium 1",
                "30111111113",
                "premium1@email.com",
                new Date(),
                7000.00,
                3000,
                "Ejecutivo A",
                "Acceso VIP"
        );

        ClientePremium premium2 = new ClientePremium(
                4,
                "Calle Premium 2",
                "30222222224",
                "premium2@email.com",
                new Date(),
                9000.00,
                50000,
                "Ejecutivo B",
                "Descuentos exclusivos"
        );

        CalificacionCliente calificacionPremiuma1= premium1.calcularCalificacion();
        CalificacionCliente calificacionPremium2= premium2.calcularCalificacion();

        //-----------------------------------------------------------------------------//


        PersonaFisica persona1 = new PersonaFisica(
                5,
                "Calle Fisica 1",
                "40111111115",
                "persona1@email.com",
                new Date(),
                3500.00,
                "12345678",
                "Maria Lopez",
                new Date(),
                "Administrativa",
                120000.00
        );

        PersonaFisica persona2 = new PersonaFisica(
                6,
                "Calle Fisica 2",
                "40222222226",
                "persona2@email.com",
                new Date(),
                45000.00,
                "87654321",
                "Carlos Diaz",
                new Date(),
                "Ingeniero",
                250000.00
        );       

        CalificacionCliente calificacionFisica1= persona1.calcularCalificacion();
        CalificacionCliente calificacionFisica2= persona2.calcularCalificacion();


        //----------------------------------------------------------------------//


        System.out.println("CALIFICACION CLIENTE: " + calificacionEmpresa1);
        System.out.println("CALIFICACION CLIENTE: " +  calificacionEmpresa2);
    
        System.out.println("CALIFICACION CLIENTE: " + calificacionPremiuma1 );
        System.out.println("CALIFICACION CLIENTE: " + calificacionPremium2);

        System.out.println("CALIFICACION CLIENTE: " + calificacionFisica1);
        System.out.println("CALIFICACION CLIENTE: " + calificacionFisica2);
    
    
    }
}