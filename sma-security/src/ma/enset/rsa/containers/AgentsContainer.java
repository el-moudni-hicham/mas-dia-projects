package ma.enset.rsa.containers;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import ma.enset.rsa.security.CryptographyUtils;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class AgentsContainer {
    public static void main(String[] args) throws ControllerException, NoSuchAlgorithmException {
        Runtime instance = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer = instance.createAgentContainer(profile);

        KeyPair keyPair = CryptographyUtils.generateRSAKeys();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        AgentController serverAgent = agentContainer.createNewAgent("server", "ma.enset.agents.ServerAgent", new Object[]{privateKey});
        AgentController clientAgent = agentContainer.createNewAgent("client","ma.enset.agents.ClientAgent", new Object[]{publicKey});
        serverAgent.start();
        clientAgent.start();
    }
}
