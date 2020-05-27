package com.kodilla.game.logic;

import static com.sun.xml.internal.ws.client.ContentNegotiation.none;
import static java.awt.Color.*;

public interface Figure {
    static enum PlayerColor getColor(){
        WHITE, BLACK, None;
    }
}
