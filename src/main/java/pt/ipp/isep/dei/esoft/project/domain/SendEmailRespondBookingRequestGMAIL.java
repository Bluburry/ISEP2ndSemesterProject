package pt.ipp.isep.dei.esoft.project.domain;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SendEmailRespondBookingRequestGMAIL implements SendMessage {

    @Override
    public String getSender() {
        return emailAgent;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String getReceiver() {
        return emailClient;
    }


    private String emailAgent;
    private String agentName;
    private String agentPhone;
    private String clientName;
    private String emailClient;
    private String message = " ";
    private String SMTPServer;

    private static final String FLDR = "./sem2Files/";
    private static final String FILEA = "EmailRespondVisitRequestGMAILAccepted.txt";
    private static final String FILER = "EmailRespondVisitRequestGMAILRejected.txt";
    private static final String MESSAGEA = "Your Visit Request is been Schedule.\nHave a nice day";
    private static final String MESSAGER = "Unfortunally it will not be possible to to Schedule your Visit Request.\nPlease Schedule another time to visit the Property.";

    public static void sendMessageVisitRejected(String keepEmailClientVisitRequest, String keepNameClientVisitRequest
            , String keepAgentEmail, String keepAgentName, double keepAgentPhone, String emailDomain) {
        File file = new File(FLDR + FILER);
        String toWrite = String.format("Email Domain: %s\n"
                        +"AgentEmail to Send: %s\n "
                        +"\tClientEmail to Receive: %s\n "
                        +"Dear Mrs or Ms %s\n "
                        +MESSAGER
                        +"\n"
                        +"Real State Agent: %s"
                        +"PhoneNumber: %f"
                ,emailDomain,keepAgentEmail,keepEmailClientVisitRequest,keepNameClientVisitRequest,keepAgentName,keepAgentPhone);
        if (!file.exists()) {
            try {
                createFileR();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter writer = new FileWriter(file,true);
            writer.write(toWrite);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void sendMessageVisitAccepted(String keepEmailClientVisitRequest, String keepNameClientVisitRequest
            , String keepAgentEmail, String keepAgentName, double keepAgentPhone, String emailDomain) {
        File file = new File(FLDR + FILEA);
        String toWrite = String.format("Email Domain: %s\n"
                        +"AgentEmail to Send: %s\n "
                        +"\tClientEmail to Receive: %s\n "
                        +"Dear Mrs or Ms %s\n "
                        +MESSAGEA
                        +"\n"
                        +"Real State Agent: %s"
                        +"PhoneNumber: %f"
                ,emailDomain,keepAgentEmail,keepEmailClientVisitRequest,keepNameClientVisitRequest,keepAgentName,keepAgentPhone);
        if (!file.exists()) {
            try {
                createFileA();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(toWrite);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void createFileA() throws IOException {
        File file = new File(FLDR + FILER);
        if (!file.exists()) {
            File fldr = new File(FLDR);
            if (!fldr.exists()) {
                if (fldr.mkdir())
                    file.createNewFile();
                else
                    throw new IOException("Failed to create directory: " + FLDR);
            } else
                file.createNewFile();
        }
    }
    private static void createFileR() throws IOException {
        File file = new File(FLDR + FILER);
        if (!file.exists()) {
            File fldr = new File(FLDR);
            if (!fldr.exists()) {
                if (fldr.mkdir())
                    file.createNewFile();
                else
                    throw new IOException("Failed to create directory: " + FLDR);
            } else
                file.createNewFile();
        }
    }
}
