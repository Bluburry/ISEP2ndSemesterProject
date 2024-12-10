package pt.ipp.isep.dei.esoft.project.application.controller;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;
import pt.ipp.isep.dei.esoft.project.repository.FileDataCollection;
import java.io.FileNotFoundException;

import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.Store;

import java.util.List;

public class ImportFileController {
    /**
     * Access to the class Repositories
     */
    private Repositories repositories;

    /**
     * Access to the class FileDataCollection
     */
    private FileDataCollection fileDataCollection;

    /**
     * Creates an instance of ImportFileController
     * where gives the to repositories an instance of Repositories
     */


    public ImportFileController(){
        repositories = Repositories.getInstance();
        fileDataCollection=new FileDataCollection();
    }

    public boolean importInformation(String fileLocation) throws FileNotFoundException{
        return fileDataCollection.importInformation(fileLocation);
    }

}
