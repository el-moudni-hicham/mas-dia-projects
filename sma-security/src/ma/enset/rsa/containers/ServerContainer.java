package ma.enset.rsa.containers;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;

import java.security.NoSuchAlgorithmException;

public class ServerContainer {
    public static void main(String[] args) throws ControllerException, NoSuchAlgorithmException {
        Runtime instance = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST,"localhost");
        AgentContainer agentContainer = instance.createAgentContainer(profile);

        String privateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAmluNCve6q" +
                "QACNOJ77r4DiWLXEHe3mJIblvLNuICYzQwfXcHHYNRMxIUU7YQlRKlidNZHo1JFfqwkkTd6SgwFy" +
                "wIDAQABAkAhQt0PofYc+J2BG9Ssy1Ejx6KkL8fmHoXHbyaZPUyv0jgc1lVOnCInn0FeO5+wu0cppY0ZY" +
                "RoOzi6v/zjuiJfBAiEA6bFDGHm4jXHr6ZhJ7k7HYbzYFtmkh866ESifqxClRfECIQCpF5XiVQuQRHgoqMc/+024Q" +
                "3RxRPrd2PuC7Vy7gXMbewIgRapLVzOKGdh5PITinUEs3O0f+gfdrYKqI01Z8/QdYPECIQCFahuTueBm0u8vvYOAowq+cV" +
                "KlsHRzqClCM4AwXcHxkwIgJpFtJbr1uXFY9xHqrp5LPdEtPhBr4m0aL/gPJsDgSvc=";

        AgentController serverAgent = agentContainer.createNewAgent("server",
                "ma.enset.rsa.agents.ServerAgent", new Object[]{privateKey});
        serverAgent.start();
    }
}
