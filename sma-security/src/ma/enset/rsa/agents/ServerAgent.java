package ma.enset.rsa.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class ServerAgent extends Agent {
    @Override
    protected void setup() {
        String encodedPrivateKey = (String) getArguments()[0];
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receive = receive();
                if(receive != null) {
                    String message = receive.getContent();
                    byte[] cryptedDecodeMessage = Base64.getDecoder().decode(message);
                    try {
                        byte[] decode = Base64.getDecoder().decode(encodedPrivateKey);
                        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
                        PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decode));

                        Cipher cipher = Cipher.getInstance("RSA");
                        cipher.init(Cipher.DECRYPT_MODE, privateKey);
                        byte[] decryptedMessage = cipher.doFinal(cryptedDecodeMessage);
                        System.out.println(new String(decryptedMessage));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

    }
}
