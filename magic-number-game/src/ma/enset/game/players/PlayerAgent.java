package ma.enset.game.players;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;

public class PlayerAgent extends GuiAgent {
    private PlayerGui playerGui;

    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {
        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        request.addReceiver(new AID("server", AID.ISLOCALNAME));
        String message = (String) guiEvent.getParameter(0);
        request.setContent(message);
        send(request);
    }

    @Override
    protected void setup() {
        playerGui = (PlayerGui) getArguments()[0];
        playerGui.setPlayerAgent(this);

        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage response = receive();
                if (response != null){
                    playerGui.showMessage(response.getSender().getLocalName() + " : " + response.getContent());
                    if (response.getContent().contains("Game over")) doDelete();
                } else {
                    block();
                }
            }
        });
    }
}
