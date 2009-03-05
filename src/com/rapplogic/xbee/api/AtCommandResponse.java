/**
 * Copyright (c) 2008 Andrew Rapp. All rights reserved.
 *  
 * This file is part of XBee-API.
 *  
 * XBee-API is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *  
 * XBee-API is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *  
 * You should have received a copy of the GNU General Public License
 * along with XBee-API.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.rapplogic.xbee.api;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import com.rapplogic.xbee.util.ByteUtils;

public class AtCommandResponse extends XBeeFrameIdResponse {
	
	public enum Status {
		OK (0),
		ERROR (1),
		INVALID_COMMAND (2), // zigbee only
		INVALID_PARAMETER (3); // zigbee only

		private static final Map<Integer,Status> lookup = new HashMap<Integer,Status>();
		
		static {
			for(Status s : EnumSet.allOf(Status.class)) {
				lookup.put(s.getValue(), s);
			}
		}
		
		public static Status get(int value) { 
			return lookup.get(value); 
		}
		
	    private final int value;
	    
	    Status(int value) {
	        this.value = value;
	    }

		public int getValue() {
			return value;
		}
	}
	
	private int char1;
	private int char2;
	private Status status;
	// response value msb to lsb
	private int[] value;
	
	public AtCommandResponse() {

	}

	public int getChar1() {
		return char1;
	}


	public void setChar1(int char1) {
		this.char1 = char1;
	}


	public int getChar2() {
		return char2;
	}


	public void setChar2(int char2) {
		this.char2 = char2;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public boolean isOk() {
		return status == Status.OK;
	}

	public int[] getValue() {
		return value;
	}
	
	public void setValue(int[] data) {
		this.value = data;
	}
	
	public String getCommand() {
		return String.valueOf((char)this.char1) + String.valueOf((char)this.char2);
	}
	
	public String toString() {
		return super.toString() + ",command=" + this.getCommand() +
			",status=" + this.getStatus() + ",value=" + 
			(this.value == null ? "null" : ByteUtils.toBase16(this.getValue()));
	}
}