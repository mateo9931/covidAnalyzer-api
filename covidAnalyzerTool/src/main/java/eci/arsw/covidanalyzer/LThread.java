package eci.arsw.covidanalyzer;


import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class LThread extends Thread{
    private List<File> files;
    private int contador;
    private int contador2;
    private TestReader testReader;
    private ResultAnalyzer resultAnalyzer;
    private AtomicInteger amountOfFilesProcessed;

/**
 * creador clase Thread
 * @param name
 * @param files
 * @param contador   para el rango inicial 
 * @param contador2 para el rango final
 */
    public LThread(String name, List<File> files, int contador, int contador2){
        super(name);
        this.files=files;
        this.contador=contador;
        this.contador2=contador2;
        resultAnalyzer = new ResultAnalyzer();
        testReader = new TestReader();
        amountOfFilesProcessed = new AtomicInteger();
    }
    
    
    /**
     * inicia la agregacion de resultados al cada hilo ejecutar el run
     */

    public void run(){
        amountOfFilesProcessed.set(0);
        for (int i = contador; i<contador2;i++) {
            List<Result> results = testReader.readResultsFromFile(files.get(i));
            for (Result result : results) {
                resultAnalyzer.addResult(result);
            }
            amountOfFilesProcessed.incrementAndGet();
        }
    }
}
