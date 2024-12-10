package pt.ipp.isep.dei.esoft.project.domain;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import pt.ipp.isep.dei.esoft.project.repository.Repositories;

public class Serialization {
    private static final String FOLDER = "./sem2Files/";
    private static Repositories repositories = Repositories.getInstance();

    public static void toWrite(List<Object> list, String file) {
        try {
            File serialFile = new File(FOLDER + file);
            if (!serialFile.exists())
                mkFile(serialFile);
            try {
                PrintWriter pw = new PrintWriter(serialFile);
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FOLDER + file));
            try {
                out.writeObject(list);
            } finally {
                out.close();
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Object> toRead(String file) {
        File serialFile = new File(FOLDER + file);
        if (serialFile.exists()) {
            List<Object> objects;
            try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(FOLDER + file));

                try {
                    objects = (List<Object>) in.readObject();
                } finally {
                    in.close();
                }
                return objects;
            } catch (EOFException e) {
                return new ArrayList<>();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        } else {
            try {
                mkFile(serialFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }
    }

    public static void saveAll() {
        repositories.getAdvertisementRepository().saveAdvertisementRepository();
        repositories.getPersonRepository().savePersonRepository();
        repositories.getPropertyRepository().savePropertyRepository();
        repositories.getRequestRepository().saveRequestRepository();
        repositories.getStoreRepository().saveStoreRepository();
    }

    public static void loadAll() {
        repositories.getAdvertisementRepository().loadAdvertisementRepository();
        repositories.getPersonRepository().loadPersonRepository();
        repositories.getPropertyRepository().loadPropertyRepository();
        repositories.getRequestRepository().loadRequestRepository();
        repositories.getStoreRepository().loadStoreRepository();
    }

    private static void mkFile(File serialFile) throws IOException {
        File fldr = new File(FOLDER);
        if (fldr.exists())
            serialFile.createNewFile();
        else {
            fldr.mkdir();
            serialFile.createNewFile();
        }
    }
}
