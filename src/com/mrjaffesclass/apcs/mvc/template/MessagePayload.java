package com.mrjaffesclass.apcs.mvc.template;

/**
 * This is the message payload class.  Instantiate this class when sending
 * field / value message data for the up/down buttons
 * 
 * @author Roger Jaffe
 * @version 1.0
 */
public class MessagePayload {
  private final int directionX;
  private final int directionY;
  /**
   * Class constructor
   * @param _directionX Direction (1 - 8)
   * @param _directionY Direction (1 - 8)
   */
  public MessagePayload(int _directionX, int _directionY) {
    directionX = _directionX;
    directionY = _directionY;
  }
  /**
   * Getter method for Y direction
   * @return directionY
   */
  public int getDirectionY() {
    return directionY;     
  }
  /**
   * Getter method for the X direction
   * @return directionX
   */
  public int getDirectionX() {
    return directionX;
  }

  
}
