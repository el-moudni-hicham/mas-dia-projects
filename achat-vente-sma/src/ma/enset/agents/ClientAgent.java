package ma.enset.agents;
import jade.core.AID;
import jade.core.behaviours.*;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.wrapper.ControllerException;
import ma.enset.containers.ClientContainer;

public class ClientAgent extends GuiAgent {
    protected ClientContainer clientContainer;
    @Override
    public void onGuiEvent(GuiEvent guiEvent) {
        if(guiEvent.getType() == 1){
            String bookName = (String) guiEvent.getParameter(0);
            System.out.println("Agent : "+getAID().getName()+" "+bookName);
            ACLMessage aclMessage = new ACLMessage(ACLMessage.REQUEST);
            aclMessage.setContent(bookName);
            aclMessage.addReceiver(new AID("BuyerAgent",AID.ISLOCALNAME));
            send(aclMessage);
        }
    }

    @Override
    protected void setup() {
        ParallelBehaviour parallelBehaviour = new ParallelBehaviour();
        addBehaviour(parallelBehaviour);

        System.out.println("Agent");
        if(this.getArguments().length == 1){
            clientContainer = (ClientContainer) this.getArguments()[0];
            clientContainer.clientAgent = this;
        }
        System.out.println("Name : " + this.getAID().getName());

        parallelBehaviour.addSubBehaviour(new Behaviour() {
            int cpt = 0;
            @Override
            public void action() {
                System.out.println("-----------------------------");
                System.out.println("Counter step "+ cpt);
                System.out.println("-----------------------------");
                cpt ++;
            }

            @Override
            public boolean done() {
                return cpt == 5;
            }
        });

        parallelBehaviour.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.println("OneShotBehaviour");
            }
        });

        /*parallelBehaviour.addSubBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {

                MessageTemplate messageTemplate = MessageTemplate.MatchPerformative(ACLMessage.CFP);
                MessageTemplate messageTemplate = MessageTemplate.and(
                        MessageTemplate.MatchPerformative(ACLMessage.CFP),
                        MessageTemplate.MatchLanguage("fr")
                );
                ACLMessage message = receive(messageTemplate);


                ACLMessage message = receive();
                if(message != null){
                    System.out.println("Sender :"+message.getSender().getName());
                    System.out.println("Message Content :"+message.getContent());
                    System.out.println("Speech Act :"+ACLMessage.getPerformative(message.getPerformative()));

                    ACLMessage reply = new ACLMessage(ACLMessage.CONFIRM);
                    reply.addReceiver(message.getSender());
                    reply.setContent("PRICE = 1500 DH");
                    send(reply);

                } else{
                    System.out.println("BLOCK .....");
                    block();
                }
            }
        });*/

        /*
        addBehaviour(new Behaviour() {
            int cpt = 0;
            @Override
            public void action() {
                System.out.println("-----------------------------");
                System.out.println("Counter step "+ cpt);
                System.out.println("-----------------------------");
                cpt ++;
            }

            @Override
            public boolean done() {
                return cpt == 5;
            }
        });

        addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                System.out.println("OneShotBehaviour");
            }
        });

        addBehaviour(new CyclicBehaviour() {
            int cpt = 0;
            @Override
            public void action() {
                System.out.println("CyclicBehaviour");
                System.out.println("-----------------------------");
                System.out.println("Counter step "+ cpt);
                System.out.println("-----------------------------");
                cpt ++;
            }
        });

        addBehaviour(new TickerBehaviour(this,3000) { // 5s
            @Override
            protected void onTick() {
                System.out.println("On tick per  3 secondes");
                System.out.println("My Name is : " + myAgent.getAID().getLocalName());
            }
        });

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy:HH:mm");
        Date date;
        try {
            date = simpleDateFormat.parse("14/02/2023:00:17");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        addBehaviour(new WakerBehaviour(this, date) {
            @Override
            protected void onWake() {
                System.out.println("WakerBehaviour ....");
            }
        });

         */

    }



    @Override
    protected void takeDown() {
        System.out.println("entrain de detruire :( ");
    }

    @Override
    protected void beforeMove() {
        try {
            System.out.println("Before Move From : "+this.getContainerController().getContainerName());
            System.out.println("------------------------------------");
        } catch (ControllerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void afterMove() {
        try {
            System.out.println("After Move To : "+this.getContainerController().getContainerName());
            System.out.println("------------------------------------");
        } catch (ControllerException e) {
            throw new RuntimeException(e);
        }
    }

}
