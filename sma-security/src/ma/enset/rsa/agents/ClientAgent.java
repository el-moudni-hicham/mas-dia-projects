package ma.enset.rsa.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

public class ClientAgent extends Agent {
    @Override
    protected void setup() {
        //PublicKey publicKey = (PublicKey) getArguments()[0];
        String encodedPublicKey = (String) getArguments()[0];
        String message = "HICHAM EL MOUDNI";

        try {
            Cipher cipher = Cipher.getInstance("RSA");
            byte[] decode = Base64.getDecoder().decode(encodedPublicKey);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(decode));
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
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
