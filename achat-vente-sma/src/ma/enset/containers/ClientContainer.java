package ma.enset.containers;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.gui.GuiEvent;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import ma.enset.agents.ClientAgent;

public class ClientContainer {
    public ClientAgent clientAgent;
    public ClientContainer() {
    }
    public void startContainer(){
        Runtime runtime = Runtime.instance();
        ProfileImpl profile =  new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST,"localhost");
        AgentContainer container = runtime.createAgentContainer(profile);
        AgentController clientAgentController = null;
        try {
            clientAgentController = container.createNewAgent("Client","ma.enset.agents.ClientAgent",new Object[]{this});
            clientAgentController.start();
        } catch (StaleProxyException e) {
            throw new RuntimeException(e);
        }

    }
    public void Gui(String arg){
        GuiEvent guiEvent = new GuiEvent(this,1);
        guiEvent.addParameter(arg);
        clientAgent.onGuiEvent(guiEvent);
    }
}