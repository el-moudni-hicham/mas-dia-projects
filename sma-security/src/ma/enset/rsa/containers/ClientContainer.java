package ma.enset.rsa.containers;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

import java.security.NoSuchAlgorithmException;

public class ClientContainer {
    public static void main(String[] args) throws ControllerException, NoSuchAlgorithmException {
        Runtime instance = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer = instance.createAgentContainer(profile);

        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJpbjQr3uqkAAjTie+6+A4li1xB3t5iSG5byzb" +
                "iAmM0MH13Bx2DUTMSFFO2EJUSpYnTWR6NSRX6sJJE3ekoMBcsCAwEAAQ==";

        AgentController clientAgent = agentContainer.createNewAgent("client"
                ,"ma.enset.agents.rsa.ClientAgent", new Object[]{publicKey});
        clientAgent.start();
    }
}
