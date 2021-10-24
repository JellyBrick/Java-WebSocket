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

import org.java_websocket.enums.Opcode;
import org.java_websocket.exceptions.InvalidDataException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit Test for the BinaryFrame class
 */
public class BinaryFrameTest {

  @Test
  public void testConstructor() {
    BinaryFrame frame = new BinaryFrame();
    assertEquals("Opcode must be equal", Opcode.BINARY, frame.getOpcode());
      assertTrue("Fin must be set", frame.isFin());
      assertFalse("TransferedMask must not be set", frame.getTransfereMasked());
    assertEquals("Payload must be empty", 0, frame.getPayloadData().capacity());
      assertFalse("RSV1 must be false", frame.isRSV1());
      assertFalse("RSV2 must be false", frame.isRSV2());
      assertFalse("RSV3 must be false", frame.isRSV3());
    try {
      frame.isValid();
    } catch (InvalidDataException e) {
      fail("InvalidDataException should not be thrown");
    }
  }

  @Test
  public void testExtends() {
    BinaryFrame frame = new BinaryFrame();
      assertTrue("Frame must extend dataframe", true);
  }

  @Test
  public void testIsValid() {
    BinaryFrame frame = new BinaryFrame();
    try {
      frame.isValid();
    } catch (InvalidDataException e) {
      fail("InvalidDataException should not be thrown");
    }
  }
}
