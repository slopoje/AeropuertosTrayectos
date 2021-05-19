package aeropuertostrayectos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreaSalidasYLlegadas {

    public static void main(String[] args) {

        //Lectura de mapa clave-valor aeropuerto-ciudad serializado
        Map<String, String> aeropuertos = null;
        try ( FileInputStream fis = new FileInputStream("aeropuertos.dat");  ObjectInputStream ois = new ObjectInputStream(fis)) {
            aeropuertos = (Map<String, String>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR, no se encuentra el fichero.");
        } catch (IOException e) {
            System.out.println("ERROR, no se puede acceder al fichero.");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR, no se encuentra la clase.");
        }

        //Lectura de array de trayectos serializado
        String[] trayectos = null;
        try ( FileInputStream fis = new FileInputStream("trayectos.dat");  ObjectInputStream ois = new ObjectInputStream(fis)) {
            trayectos = (String[]) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR, no se encuentra el fichero.");
        } catch (IOException e) {
            System.out.println("ERROR, no se puede acceder al fichero.");
        } catch (ClassNotFoundException e) {
            System.out.println("ERROR, no se encuentra la clase.");
        }

        //Listas para almacenar las llegadas y las salidas
        List<String> listaLlegadas = new ArrayList<String>();
        List<String> listaSalidas = new ArrayList<String>();

        Scanner teclado = new Scanner(System.in);
        String aeropuerto;

        System.out.println("A E R O P U E R T O S");
        System.out.println("-------------------------------------");

        //A continuación se muestran tres formas de recorrer el mapa clave-valor de aeropuertos:
        //1ª forma, utilizando un iterador:
        Iterator<Map.Entry<String, String>> iterador = aeropuertos.entrySet().iterator();
        while (iterador.hasNext()) {
            Map.Entry<String, String> entradaMapa = iterador.next();
            System.out.println(entradaMapa.getKey() + "-" + entradaMapa.getValue());
        }
        //2ª forma, utilizando un buche for-each:
        //for (Map.Entry<String, String> entradaMapa : aeropuertos.entrySet()) {
        //    System.out.println(entradaMapa.getKey() + "-" + entradaMapa.getValue());
        //}
        //
        //3ª forma, utilizando una expresión lambda:
        //aeropuertos.forEach((k, v) -> System.out.println(k + "- " + v));

        //Se pide un código de aeropueto por teclado
        System.out.println("-------------------------------------");
        System.out.print("Introduce un código de aeropuerto:");
        aeropuerto = teclado.nextLine().toUpperCase();

        //Procesamiento de los trayectos, para extraer las salidas y llegadas 
        //del aeropuerto introducido por teclado
        Pattern patronOrigen = Pattern.compile(aeropuerto + "-.{3}");
        Pattern patronDestino = Pattern.compile(".{3}-" + aeropuerto);
        for (String trayecto : trayectos) {
            Matcher matcherOrigen = patronOrigen.matcher(trayecto);
            Matcher matcherDestino = patronDestino.matcher(trayecto);
            if (matcherOrigen.matches()) {
                listaSalidas.add(aeropuertos.get(trayecto.substring(4, 7)));
            } else if (matcherDestino.matches()) {
                listaLlegadas.add(aeropuertos.get(trayecto.substring(0, 3)));
            }
        }

        //Esta forma también es válida, aunque menos eficiente
        //for (String trayecto : trayectos) {
        //    if (trayecto.matches(aeropuerto + ".*")) {
        //        listaSalidas.add(aeropuertos.get(trayecto.substring(4, 7)));
        //    } else if (trayecto.matches(".*" + aeropuerto)) {
        //        listaLlegadas.add(aeropuertos.get(trayecto.substring(0, 3)));
        //    }
        //}
        System.out.print("Grabando fichero salidas.dat... ");
        try ( FileOutputStream fos = new FileOutputStream("salidas.dat");  ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(listaSalidas);
            System.out.println("OK");

        } catch (FileNotFoundException e) {
            System.out.println("ERROR, no se encuentra el fichero.");
        } catch (IOException e) {
            System.out.println("ERROR, no se puede acceder al fichero.");
        }

        System.out.print("Grabando fichero llegadas.dat... ");
        try ( FileOutputStream fos = new FileOutputStream("llegadas.dat");  ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(listaLlegadas);
            System.out.println("OK");

        } catch (FileNotFoundException e) {
            System.out.println("ERROR, no se encuentra el fichero.");
        } catch (IOException e) {
            System.out.println("ERROR, no se puede acceder al fichero.");
        }

    }

}
