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

import java.util.ArrayList;
import java.util.List;

public class Game {
	private static final int	MAX_PLAYERS	= 4;
	private static Game			instance;
	private List<Player>		players;
	private List<Field>			fields;
	private int					currentPlayer;

	/**
	 * Returns the current instance or initializes a new {@link Game}.O
	 * 
	 * @return the {@link Game}'s instance
	 */
	public static Game getInstance() {
		if (instance == null) {
			instance = new Game();
		}
		return instance;
	}

	/**
	 * Initializes a new {@link Game}.
	 * 
	 * @param players
	 * @param fields
	 */
	private Game() {
		this.players = new ArrayList<Player>();
		this.fields = new ArrayList<Field>();
		this.currentPlayer = 0;
	}

	/**
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * @param players
	 *            the players to set
	 */
	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	/**
	 * @return the fields
	 */
	public List<Field> getFields() {
		return fields;
	}

	/**
	 * @param fields
	 *            the fields to set
	 */
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	/**
	 * @return the currentPlayer
	 */
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * @param currentPlayer
	 *            the currentPlayer to set
	 */
	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	/**
	 * Increments the current player counter & sets it to 0 if the last player
	 * moved.
	 */
	public void nextPlayer() {
		this.currentPlayer++;
		if (this.currentPlayer == MAX_PLAYERS) {
			this.currentPlayer = 0;
		}
	}
}
