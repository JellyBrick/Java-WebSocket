/*
 * Copyright (c) 2010-2020 Nathan Rajlich
 *
 *  Permission is hereby granted, free of charge, to any person
 *  obtaining a copy of this software and associated documentation
 *  files (the "Software"), to deal in the Software without
 *  restriction, including without limitation the rights to use,
 *  copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the
 *  Software is furnished to do so, subject to the following
 *  conditions:
 *
 *  The above copyright notice and this permission notice shall be
 *  included in all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 *  EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 *  OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 *  NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 *  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 *  WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 *  FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 *  OTHER DEALINGS IN THE SOFTWARE.
 */

package org.java_websocket.framing;

import java.nio.ByteBuffer;
import org.java_websocket.enums.Opcode;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit Test for the FramedataImpl1 class
 */
public class FramedataImpl1Test {

  @Test
  public void testDefaultValues() {
    FramedataImpl1 binary = FramedataImpl1.get(Opcode.BINARY);
    assertEquals("Opcode must be equal", Opcode.BINARY, binary.getOpcode());
    assertTrue("Fin must be set", binary.isFin());
    assertFalse("TransferedMask must not be set", binary.getTransfereMasked());
    assertEquals("Payload must be empty", 0, binary.getPayloadData().capacity());
    assertFalse("RSV1 must be false", binary.isRSV1());
    assertFalse("RSV2 must be false", binary.isRSV2());
    assertFalse("RSV3 must be false", binary.isRSV3());
  }

  @Test
  public void testGet() {
    FramedataImpl1 binary = FramedataImpl1.get(Opcode.BINARY);
      assertTrue("Frame must be binary", binary instanceof BinaryFrame);
    FramedataImpl1 text = FramedataImpl1.get(Opcode.TEXT);
      assertTrue("Frame must be text", text instanceof TextFrame);
    FramedataImpl1 closing = FramedataImpl1.get(Opcode.CLOSING);
      assertTrue("Frame must be closing", closing instanceof CloseFrame);
    FramedataImpl1 continuous = FramedataImpl1.get(Opcode.CONTINUOUS);
      assertTrue("Frame must be continuous", continuous instanceof ContinuousFrame);
    FramedataImpl1 ping = FramedataImpl1.get(Opcode.PING);
      assertTrue("Frame must be ping", ping instanceof PingFrame);
    FramedataImpl1 pong = FramedataImpl1.get(Opcode.PONG);
      assertTrue("Frame must be pong", pong instanceof PongFrame);
    try {
      FramedataImpl1.get(null);
      fail("IllegalArgumentException should be thrown");
    } catch (IllegalArgumentException e) {
      //Fine
    }
  }

  @Test
  public void testSetters() {
    FramedataImpl1 frame = FramedataImpl1.get(Opcode.BINARY);
    frame.setFin(false);
    assertFalse("Fin must not be set", frame.isFin());
    frame.setTransferemasked(true);
    assertTrue("TransferedMask must be set", frame.getTransfereMasked());
    ByteBuffer buffer = ByteBuffer.allocate(100);
    frame.setPayload(buffer);
    assertEquals("Payload must be of size 100", 100, frame.getPayloadData().capacity());
    frame.setRSV1(true);
    assertTrue("RSV1 must be true", frame.isRSV1());
    frame.setRSV2(true);
    assertTrue("RSV2 must be true", frame.isRSV2());
    frame.setRSV3(true);
    assertTrue("RSV3 must be true", frame.isRSV3());
  }

  @Test
  public void testAppend() {
    FramedataImpl1 frame0 = FramedataImpl1.get(Opcode.BINARY);
    frame0.setFin(false);
    frame0.setPayload(ByteBuffer.wrap("first".getBytes()));
    FramedataImpl1 frame1 = FramedataImpl1.get(Opcode.BINARY);
    frame1.setPayload(ByteBuffer.wrap("second".getBytes()));
    frame0.append(frame1);
    assertTrue("Fin must be set", frame0.isFin());
    assertArrayEquals("Payload must be equal", "firstsecond".getBytes(),
        frame0.getPayloadData().array());
  }
}
