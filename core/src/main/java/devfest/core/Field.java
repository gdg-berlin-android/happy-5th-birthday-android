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

import pythagoras.f.Point;

/**
 * A field on the playing board where a droid can jump to.
 */
@SuppressWarnings("serial")
class Field extends Point {

  /**
   * The id for the next field instance.
   */
  private static int nextId = 0;

  /**
   * The id of this field.
   */
  private final int id;

  /**
   * A link to another field id.
   * 
   * <p>
   * Indicates a shortcut or a trap.
   * </p>
   * 
   * <p>
   * -1 if the field has no link.
   * </p>
   */
  private final int link;

  /**
   * Construct a new Field without a link.
   * 
   * @param x X-coordinate of field droid position.
   * @param y Y-coordinate of field droid position.
   */
  Field(final float x, final float y) {
    this(x, y, -1);
  }

  /**
   * Construct a new Field with a link.
   * 
   * @param x X-coordinate of field droid position.
   * @param y Y-coordinate of field droid position.
   * @param link A link to another field id.
   */
  Field(final float x, final float y, final int link) {
    super(x, y);
    this.id = nextId++;
    this.link = link;
  }

  /**
   * @return The id of this field.
   */
  int id() {
    return id;
  }

  /**
   * @return A link to another field id. -1 if there is no link.
   */
  public int link() {
    return link;
  }

}
