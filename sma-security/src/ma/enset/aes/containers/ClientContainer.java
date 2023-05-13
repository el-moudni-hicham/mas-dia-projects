package ma.enset.aes.containers;

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

        String key = "1234561234561234";

        AgentController clientAgent = agentContainer.createNewAgent("client"
                ,"ma.enset.aes.agents.ClientAgent", new Object[]{key});
        clientAgent.start();
    }
}
