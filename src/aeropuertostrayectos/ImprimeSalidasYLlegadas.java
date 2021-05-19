package aeropuertostrayectos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class ImprimeSalidasYLlegadas {

    public static void main(String[] args) {

        //Listas para almacenar las llegadas y las salidas
        List<String> listaLlegadas = new ArrayList<String>();
        List<String> listaSalidas = new ArrayList<String>();

        System.out.print("Leyendo fichero salidas.dat... ");
        try ( FileInputStream fis = new FileInputStream("salidas.dat");  ObjectInputStream ois = new ObjectInputStream(fis)) {

            listaSalidas = (List<String>) ois.readObject();
            System.out.println("OK");

        } catch (FileNotFoundException e) {
            System.out.println("ERROR, no se encuentra el fichero.");
        } catch (IOException e) {
            System.out.println("ERROR, no se puede acceder al fichero.");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR, no se encuentra la clase.");
        }

        System.out.print("Leyendo fichero llegadas.dat... ");
        try ( FileInputStream fis = new FileInputStream("llegadas.dat");  ObjectInputStream ois = new ObjectInputStream(fis)) {

            listaLlegadas = (List<String>) ois.readObject();
            System.out.println("OK");

        } catch (FileNotFoundException e) {
            System.out.println("ERROR, no se encuentra el fichero.");
        } catch (IOException e) {
            System.out.println("ERROR, no se puede acceder al fichero.");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR, no se encuentra la clase.");
        }

        //Imprimimos los resultados: lista de ciudades que son salida o llegada
        System.out.println(listaSalidas.size() + " "
                + "Salidas:" + listaSalidas.toString());
        System.out.println(listaLlegadas.size() + " "
                + "Llegadas:" + listaLlegadas.toString());
    }

}
