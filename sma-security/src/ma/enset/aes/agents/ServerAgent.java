package ma.enset.aes.agents;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class ServerAgent extends Agent {
    @Override
    protected void setup() {
        String key = (String) getArguments()[0];
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receive = receive();
                if(receive != null) {
                    String message = receive.getContent();
                    byte[] cryptedDecodeMessage = Base64.getDecoder().decode(message);
                    try {
                        SecretKey secretKey = new SecretKeySpec(key.getBytes(),"AES");

                        Cipher cipher = Cipher.getInstance("AES");
                        cipher.init(Cipher.DECRYPT_MODE, secretKey);
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
