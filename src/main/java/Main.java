import com.example.NalogServices;
import javax.xml.soap.SOAPException;

public class Main {
    public static void main(String[] args) throws SOAPException {

        for (String INN:args) {
            System.out.println(INN + " " + NalogServices.getContractorState(INN));
        }

    }

}
