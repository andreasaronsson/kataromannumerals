package nu.aron.kataromannumerals;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.collection.CharSeq;
import io.vavr.collection.Stream;

/**
 * Entry point
 */
class Main {
    Function2<Integer, String, String> charMultiple = (i, s) -> Stream.continually(s).take(i).mkString();

    Function1<Integer, String> mille = number -> charMultiple.apply(number % (1000 * 10) / 1000, "M");
    Function1<Integer, String> centum = number -> charMultiple.apply(number % (100 * 10) / 100, "C");
    Function1<Integer, String> decem = number -> charMultiple.apply(number % (10 * 10) / 10, "X");
    Function1<Integer, String> unus = number -> charMultiple.apply(number % (1 * 10) / 1, "I");

    Function1<String, String> reduceQuinque = s -> CharSeq.of(s).replace("IIIII", "V").toString();
    Function1<String, String> reduceQuinquaginta = s -> CharSeq.of(s).replace("XXXXX", "L").toString();
    Function1<String, String> reduceQuingenti = s -> CharSeq.of(s).replace("CCCCC", "D").toString();

    Function1<String, String> reduceNovem = s -> CharSeq.of(s).replace("VIIII", "IX").toString();
    Function1<String, String> reduceNonaginta = s -> CharSeq.of(s).replace("LXXXX", "XC").toString();
    Function1<String, String> reduceNongentesimus = s -> CharSeq.of(s).replace("DCCCC", "CM").toString();

    Function1<String, String> reduceQuattuor = s -> CharSeq.of(s).replace("IIII", "IV").toString();
    Function1<String, String> reduceQuadraginta = s -> CharSeq.of(s).replace("XXXX", "XL").toString();

    String numeral2(int number) {
        return mille.apply(number)
                .concat(reduceNongentesimus.apply(reduceQuingenti.apply(centum.apply(number))))
                .concat(reduceQuadraginta.apply(reduceNonaginta.apply(reduceQuinquaginta.apply(decem.apply(number)))))
                .concat(reduceQuattuor.apply(reduceNovem.apply(reduceQuinque.apply(unus.apply(number)))));
    }

    Function1<String, String> reduceHundreds = reduceQuingenti.andThen(reduceNongentesimus);
    Function1<String, String> reduceTens = reduceQuinquaginta.andThen(reduceNonaginta).andThen(reduceQuadraginta);
    Function1<String, String> reduceOnes = reduceQuinque.andThen(reduceNovem).andThen(reduceQuattuor);

    String numeral(int number) {
        return mille.apply(number)
                .concat(reduceHundreds.apply(centum.apply(number)))
                .concat(reduceTens.apply(decem.apply(number)))
                .concat(reduceOnes.apply(unus.apply(number)));
    }
}
