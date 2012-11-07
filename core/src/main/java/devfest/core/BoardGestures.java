/*
 * Copyright 2012 c-base e.V.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package devfest.core;

import playn.core.Mouse;
import playn.core.Mouse.ButtonEvent;
import playn.core.Mouse.MotionEvent;
import playn.core.Mouse.WheelEvent;
import playn.core.Pointer;
import playn.core.Pointer.Event;
import playn.core.Touch;
import pythagoras.f.Point;

/**
 * Touch, mouse and pointer gestures which can be done with an {@link Board}.
 */
class BoardGestures implements Touch.Listener, Mouse.Listener, Pointer.Listener {

  /**
   * Board to fiddle around with.
   */
  private final Board board;

  /**
   * Last touch/mouse position while dragging.
   * 
   * <p>
   * <code>null</code> when no dragging performed.
   * </p>
   */
  private Point lastDragPos;

  /**
   * Last distance between 2 pinch points while pinching.
   */
  private float lastPinchDistance;

  /**
   * Current mouse position.
   * 
   * <p>
   * <code>null</code> when no mouse movement occurred inside the PlayN
   * context.
   * </p>
   */
  private Mouse.MotionEvent curMousePos;

  /**
   * Construct a new {@link BoardGestures}.
   * 
   * @param board Board to fiddle around with
   */
  BoardGestures(final Board board) {
    this.board = board;
    board.imageLayer().addListener((Pointer.Listener) this);
  }

  @Override
  public void onTouchStart(final Touch.Event[] touches) {
    lastPinchDistance = 0;
  }

  @Override
  public void onTouchMove(final Touch.Event[] touches) {
    if (touches.length == 2) {
      onPinch(new Point(touches[0].x(), touches[0].y()), new Point(touches[1].x(), touches[1].y()));
    }
  }

  @Override
  public void onTouchEnd(final Touch.Event[] touches) {
    lastPinchDistance = 0;
  }

  @Override
  public void onMouseDown(final ButtonEvent event) {
    // Intentionally left empty
  }

  @Override
  public void onMouseMove(final MotionEvent event) {
    curMousePos = event;
  }

  @Override
  public void onMouseUp(final ButtonEvent event) {
    // Intentionally left empty
  }

  @Override
  public void onMouseWheelScroll(final WheelEvent event) {
    if (curMousePos != null) {
      board.scale(curMousePos.x(), curMousePos.y(), (event.velocity() > 0) ? 0.8f : 1.2f);
    }
  }

  private void onDrag(final float x, final float y) {
    board.translate(board.layer().transform().tx() - (lastDragPos.x() - x), board.layer().transform().ty()
        - (lastDragPos.y() - y));
    lastDragPos = new Point(x, y);
  }

  private void onPinch(final Point pinch1, final Point pinch2) {
    float curPinchDistance = pinch1.distance(pinch2);

    if (lastPinchDistance == 0) {
      lastPinchDistance = curPinchDistance;
    } else {
      board.scale((pinch2.x - pinch1.x) / 2 + pinch1.x, (pinch2.y - pinch1.y) / 2 + pinch1.y,
          curPinchDistance / lastPinchDistance);
      lastPinchDistance = curPinchDistance;
    }
  }

  @Override
  public void onPointerStart(final Event event) {
    lastDragPos = new Point(event.x(), event.y());
  }

  @Override
  public void onPointerDrag(final Event event) {
    if (lastDragPos != null) {
      onDrag(event.x(), event.y());
    }
  }

  @Override
  public void onPointerEnd(final Event event) {
    lastDragPos = null;
  }

}
