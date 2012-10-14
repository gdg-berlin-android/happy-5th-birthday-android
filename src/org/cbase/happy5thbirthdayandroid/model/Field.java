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

package org.cbase.happy5thbirthdayandroid.model;

public class Field {
	private final int	x;
	private final int	y;
	private Field		next;
	private Field		alternative;

	/**
	 * Generates a new {@link Field} with an assigned x- & y-coordinate.
	 * 
	 * @param x
	 *            the {@link Field}'s x-coordinate
	 * @param y
	 *            the {@link Field}'s y-coordinate
	 */
	public Field(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Generates a new {@link Field} with an assigned x-, y-coordinate & the
	 * next {@link Field}.
	 * 
	 * @param x
	 *            the {@link Field}'s x-coordinate
	 * @param y
	 *            the {@link Field}'s y-coordinate
	 * @param next
	 *            the next {@link Field}
	 */
	public Field(int x, int y, Field next) {
		this.x = x;
		this.y = y;
		this.next = next;
	}

	/**
	 * Generates a new {@link Field} with an assigned x-, y-coordinate, the next
	 * {@link Field} & an alternative {@link Field}.
	 * 
	 * @param x
	 *            the {@link Field}'s x-coordinate
	 * @param y
	 *            the {@link Field}'s y-coordinate
	 * @param next
	 *            the next {@link Field}
	 * @param alternative
	 *            an alternative {@link Field}
	 */
	public Field(int x, int y, Field next, Field alternative) {
		this.x = x;
		this.y = y;
		this.next = next;
		this.alternative = alternative;
	}

	/**
	 * @return the x-coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y-coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the next
	 */
	public Field getNext() {
		return next;
	}

	/**
	 * @param next
	 *            the next to set
	 */
	public void setNext(Field next) {
		this.next = next;
	}

	/**
	 * @return the alternative
	 */
	public Field getAlternative() {
		return alternative;
	}

	/**
	 * @param alternative
	 *            the alternative to set
	 */
	public void setAlternative(Field alternative) {
		this.alternative = alternative;
	}
}
