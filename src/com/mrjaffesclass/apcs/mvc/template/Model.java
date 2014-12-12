package com.mrjaffesclass.apcs.mvc.template;

import com.mrjaffesclass.apcs.messenger.*;

/**
 * The model represents the data that the app uses.
 * @author Roger Jaffe
 * @version 1.0
 */
public class Model implements MessageHandler {

  // Messaging system for the MVC
  private final int BLANK = 0;
  private final int MINE = 1;
  private final int BSIZE = 8;
  private final int LIVE = 3;
  private final int MINEN = 10;
  private final int board [][] = new int[BSIZE][BSIZE];
  private final Messenger mvcMessaging;

  // Model's data variables
  private int variable1;
  private int variable2;
  private int ab;
  private int ba;
  /**
   * Model constructor: Create the data representation of the program
   * @param messages Messaging class instantiated by the Controller for 
   *   local messages between Model, View, and controller
   */
  public Model(Messenger messages) {
    mvcMessaging = messages;
  }
  
  /**
   * Initialize the model here and subscribe to any required messages
   */
  public void init() {
    mvcMessaging.subscribe("view:changeButton", this);
    setVariable1(LIVE);
    setVariable2(0);
    for (int i=0; i < BSIZE; i++)
        for (int j=0; j < BSIZE; j++)
            board[i][j] = BLANK;
    
    for (int a=0; a < MINEN; a++)
    {
        ab = (int)(Math.random()*BSIZE);
        ba = (int)(Math.random()*BSIZE);
        if (board[ab][ba] == BLANK)
        {
            board[ab][ba] = MINE;
        }
        else {
            a--;
        }        
    }
  }
  
  @Override
  public void messageHandler(String messageName, Object messagePayload) {
    if (messagePayload != null) {
      System.out.println("MSG: received by model: "+messageName+" | "+messagePayload.toString());
    } else {
      System.out.println("MSG: received by model: "+messageName+" | No data sent");
    }
    MessagePayload payload = (MessagePayload)messagePayload;
    int directionX = payload.getDirectionX();
    int directionY = payload.getDirectionY();
    if (messageName.equals("view:changeButton")) {
        if (board[directionX][directionY] == BLANK)
            {
                if (getVariable1() != 0){
                setVariable2(getVariable2() +1);
                mvcMessaging.notify("model:minecheck", 0, true);
                }
            }
            else if(board[directionX][directionY] == MINE){
                if (getVariable1() != 0){
                    mvcMessaging.notify("model:minecheck", 1, true); 
                    setVariable1(getVariable1() -1);
                }
            }
    }
  }

  /**
   * Getter function for variable 1
   * @return Value of variable1
   */
  public int getVariable1() {
    return variable1;
  }

  /**
   * Setter function for variable 1
   * @param v New value of variable1
   */
  public void setVariable1(int v) {
    variable1 = v;
    mvcMessaging.notify("model:variable1Changed", variable1, true);
    if(getVariable1() == 0){
        mvcMessaging.notify("model:gameover", 1, true);
    }
  }
  
  /**
   * Getter function for variable 1
   * @return Value of variable2
   */
  public int getVariable2() {
    return variable2;
  }
  
  /**
   * Setter function for variable 2
   * @param v New value of variable 2
   */
  public void setVariable2(int v) {
    variable2 = v;
    mvcMessaging.notify("model:variable2Changed", variable2, true);
  }
}
