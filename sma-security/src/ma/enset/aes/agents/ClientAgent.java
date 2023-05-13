package ma.enset.aes.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class ClientAgent extends Agent {
    @Override
    protected void setup() {
        //PublicKey publicKey = (PublicKey) getArguments()[0];
        String key = (String) getArguments()[0];
        String message = "HICHAM EL MOUDNI with AES";

        try {
            SecretKey secretKey = new SecretKeySpec(key.getBytes(),"AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] cryptedMessage = cipher.doFinal(message.getBytes());
            String cryptedEncodeMessage = Base64.getEncoder().encodeToString(cryptedMessage);

            ACLMessage aclMessage = new ACLMessage(ACLMessage.INFORM);
            aclMessage.addReceiver(new AID("server",AID.ISLOCALNAME));
            aclMessage.setContent(cryptedEncodeMessage);
            send(aclMessage);

            System.out.println(Arrays.toString(cryptedMessage));
            System.out.println(cryptedEncodeMessage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
