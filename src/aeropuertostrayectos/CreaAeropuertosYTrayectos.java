package aeropuertostrayectos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.TreeMap;

public class CreaAeropuertosYTrayectos {

    public static void main(String[] args) {

        //Mapa clave-valor: código de aeropuerto-ciudad
        Map<String, String> aeropuertos = new TreeMap<>();
        aeropuertos.put("LEI", "Almería");
        aeropuertos.put("TXL", "Berlín");
        aeropuertos.put("BRU", "Bruselas");
        aeropuertos.put("ODB", "Córdoba");
        aeropuertos.put("GRX", "Granada");
        aeropuertos.put("XRY", "Jerez de la Fra.");
        aeropuertos.put("MAD", "Madrid");
        aeropuertos.put("AGP", "Málaga");
        aeropuertos.put("PMI", "Palma de Mallorca");
        aeropuertos.put("SVQ", "Sevilla");

        //Array de trayectos aéreos
        String[] trayectos = {"MAD-LEI", "MAD-GRX", "AGP-SVQ", "LEI-SVQ", "SVQ-MAD",
            "SVQ-LEI", "AGP-LEI", "TXL-MAD", "AGP-PMI", "PMI-XRY", "ODB-MAD", "XRY-PMI"};

        //Creación del fichero de aeropuertos
        System.out.print("Creando el fichero de aeropuertos... ");
        try ( FileOutputStream fos = new FileOutputStream("aeropuertos.dat");  ObjectOutputStream oos = new ObjectOutputStream(fos)) {       
            oos.writeObject(aeropuertos);
            System.out.println("OK");
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR, no se encuentra el fichero.");
        } catch (IOException ex) {
            System.out.println("ERROR, no se puede acceder al fichero.");
        }

        //Creación del fichero de trayectos
        System.out.print("Creando el fichero de trayectos... ");
        try ( FileOutputStream fos = new FileOutputStream("trayectos.dat");  ObjectOutputStream oos = new ObjectOutputStream(fos)) {           
            oos.writeObject(trayectos);
            System.out.println("OK");
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR, no se encuentra el fichero.");
        } catch (IOException ex) {
            System.out.println("ERROR, no se puede acceder al fichero.");
        }

    }
}
