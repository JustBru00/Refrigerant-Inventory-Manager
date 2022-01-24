package com.rbrubaker.refrigerant_inventory_manager.database;

/**
 * Refrigerant-Inventory-Manager is a simple inventory management program.
 *  Copyright (C) 2022 Rufus Brubaker Refrigeration. Created by Justin Brubaker.
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 *   You can contact us at rbr@rbrubaker.com.
 *   https://github.com/JustBru00/Refrigerant-Inventory-Manager
 * @author Justin Brubaker
 *
 */
public class InventoryItemBean {

	private String name;
	private String identifierBarcode;	
	private String refrigerantType;
	/**
	 * The total weight of the cylinder when received at the shop.
	 */
	private double checkInWeight;
	/**
	 * The total weight of the cylinder when is returns from the job site at the shop.
	 */
	private double returnWeight;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIdentifierBarcode() {
		return identifierBarcode;
	}
	
	public void setIdentifierBarcode(String identifierBarcode) {
		this.identifierBarcode = identifierBarcode;
	}
	
	public String getRefrigerantType() {
		return refrigerantType;
	}
	
	public void setRefrigerantType(String refrigerantType) {
		this.refrigerantType = refrigerantType;
	}
	
	public double getCheckInWeight() {
		return checkInWeight;
	}
	
	public void setCheckInWeight(double checkInWeight) {
		this.checkInWeight = checkInWeight;
	}
	
	public double getReturnWeight() {
		return returnWeight;
	}
	
	public void setReturnWeight(double returnWeight) {
		this.returnWeight = returnWeight;
	}
	
}
