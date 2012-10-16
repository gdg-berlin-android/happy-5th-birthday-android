/*
 * Copyright 2012 c-base e.V.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package devfest.core;

import pythagoras.f.Vector;


public class Field extends Vector {
  public final int jumpTo;

  public Field(float x, float y) {
    this(x, y, -1);
  }

  public Field(float x, float y, int jumpTo) {
    super(x, y);
    this.jumpTo = jumpTo;
  }

}
