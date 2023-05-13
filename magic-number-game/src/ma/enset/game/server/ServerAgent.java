package ma.enset.game.server;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;

import java.util.Random;

public class ServerAgent extends GuiAgent {
    ServerGui serverGui;
    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {

    }

    @Override
    protected void setup() {
        int magicNb = new Random().nextInt(20);

        serverGui = (ServerGui) this.getArguments()[0];
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                String message = "";
                int playerNb;
                ACLMessage response = receive();

                if (response != null){
                    serverGui.showMessage(response.getSender().getLocalName() + " : " + response.getContent());
                    ACLMessage request = new ACLMessage(ACLMessage.INFORM);
                    ACLMessage request1 = new ACLMessage(ACLMessage.INFORM);

                    playerNb = Integer.parseInt(response.getContent());
                    if(playerNb < magicNb ){
                        message = "Your guess is lower than the number I picked !";
                        request.addReceiver(response.getSender());
                        request.setContent(message);
                        send(request);
                    } else if (playerNb > magicNb) {
                        message = "Your guess is higher than the number I picked !";
                        request.addReceiver(response.getSender());
                        request.setContent(message);
                        send(request);
                    } else {
                        message = "Game over Player " + response.getSender().getLocalName() + " is the winner !";
                        String msgWinner = "Congrats you are the winner !";

                        AMSAgentDescription[] agents = null;
                        try {
                            SearchConstraints c = new SearchConstraints();
                            c.setMaxResults (new Long(-1));
                            agents = AMSService.search(this.getAgent(), new AMSAgentDescription (), c );
                        }
                        catch (Exception e) {
                            System.out.println( "Problem searching AMS: " + e );
                            e.printStackTrace();
                        }

                        //AID myID = getAID();

                        for (int i=0; i<agents.length;i++)
                        {
                            AID agentID = agents[i].getName();
                            if (!agentID.equals(response.getSender())){
                                request.setContent(message);
                                request.addReceiver(agentID);
                            } else {
                                request1.setContent(msgWinner);
                                request1.addReceiver(agentID);
                            }

                        }
                        send(request);
                        send(request1);
                        doDelete();
                        //System.exit(0);
                    }

                } else {
                    block();
                }
            }
        });
    }

    @Override
    protected void takeDown() {
        System.out.println("GAME OVER !");
    }
}
