
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 *
 * @author EDWIN
 */
public class Streams {

        /*
         * Las principales funcionalidades del stream es para realicer filtros o hacer
         * maps, los filtros nos permiten traer datos dada una condicion en especifico,
         * y los maps nos permiten modificar los datos antes de entregarlos o
         * retornalos. Ademas con Java 8 podemos realicer programacion funcional o
         * funciones lambda, las cuales nos permiten hacer de forma mas legible y en
         * menos codigo muchos de los eventos tipicos, como los For, entre otras
         * funcionalidades. Y con los Regex Pattern podemos validar que las cadenas de
         * caracteres ingresadas cumplan cierta estructura.
         * 
         * Cabe aclrar que los Sreams pueden ser de un tipo de objeto especifico creado
         * dada una necesidad funcional del programa, como Usuario, Producto, e.t.c.
         */
        private static final int[] ARRAY_NUMBEROS = { 10, 1, 9, 2, 8, 3, 7, 4, 6, 5 };
        private static final List<String> ARRAY_STRING = new ArrayList<String>(Arrays.asList("Hola", "como", "ESTAN",
                        "Muy2", "b13n", "Me", "alegro", "MUCHO", "p0r", "ust3d3s", "1235", "30412345678"));

        private static void pruebasNumeros() {
                System.out.println("** INICIO DE LOS STREAMS NUMERICOS **");
                IntStream.of(ARRAY_NUMBEROS).min().ifPresent(System.out::println);
                IntStream.of(ARRAY_NUMBEROS).max().ifPresent(System.out::println);
                IntStream.of(ARRAY_NUMBEROS).average().ifPresent(System.out::println);
                IntStream.of(ARRAY_NUMBEROS).count();
                IntStream.of(ARRAY_NUMBEROS).sum();
                IntStream.of(ARRAY_NUMBEROS).sorted().forEach(element -> System.out.print(element + " "));
                System.out.println("\n__ Map para elevar al cuadrado todos los numeros __");
                IntStream.of(ARRAY_NUMBEROS).map(element -> element * element)
                                .forEach(element -> System.out.print(element + " "));
                System.out.println("\n__ Filter para solo mostrar los numeros pares __");
                IntStream.of(ARRAY_NUMBEROS).filter(element -> (element % 2 == 0))
                                .forEach(element -> System.out.print(element + " "));
                System.out.println("\n** FIN STREAMS NUMERICOS **");
        }

        private static void pruebaString() {
                String soloNumeros = "[0-9]*";
                String soloLetras = "[a-zA-Z]*";
                String soloLetrasMayusculas = "[A-Z]*";
                String soloLetrasMinusculas = "[a-z]*";
                System.out.println("\n** INICIO DE LOS STREAMS STRINGS **");
                ARRAY_STRING.forEach(element -> System.out.print(element + " "));
                System.out.println("\n__ Imprimir el primer elemento si existe __");
                ARRAY_STRING.stream().findFirst().ifPresent(System.out::println);
                System.out.println("__ Filtro string que solo tengan numeros __");
                ARRAY_STRING.stream().filter(element -> element.matches(soloNumeros))
                                .forEach(element -> System.out.print(element + " "));
                System.out.println("\n__ Filtro string que solo tengan letras __");
                ARRAY_STRING.stream().filter(element -> element.matches(soloLetras))
                                .forEach(element -> System.out.print(element + " "));
                System.out.println("\n__ Filtro string que solo tengan mayusculas __");
                ARRAY_STRING.stream().filter(element -> element.matches(soloLetrasMayusculas))
                                .forEach(element -> System.out.print(element + " "));
                System.out.println("\n__ Filtro string que solo tengan minusculas __");
                ARRAY_STRING.stream().filter(element -> element.matches(soloLetrasMinusculas))
                                .forEach(element -> System.out.print(element + " "));
                System.out.println("\n** FIN STREAMS STRINGS **");
        }

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) {
                pruebasNumeros();
                pruebaString();
        }

}
