package com.rbrubaker.refrigerant_inventory_manager.database;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import org.simpleyaml.configuration.ConfigurationSection;
import org.simpleyaml.configuration.file.YamlFile;

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
public class DatabaseManager {

	private static YamlFile database;
	
	private static ArrayList<InventoryItemBean> inventoryItems = new ArrayList<InventoryItemBean>();
	
	public static boolean loadDatabase() {
		String path = "@refrigerant-inventory-manager@database.yml";
		path = path.replace("@", File.separator);
		database = new YamlFile(path);
		
		try {
			if (database.exists()) {
				System.out.println("File already exists, loading configurations...\nFile located at: " + database.getFilePath() + "\n");
				database.load(); // Loads the entire file
			} else {
				System.out.println("Copying default config file: " + database.getFilePath() + "\n");
				copy(DatabaseManager.class.getResourceAsStream("/default_files/database.yml"), path);
				database.load();
			}
		} catch(FileNotFoundException e) {		
			e.printStackTrace();
			System.out.println("Couldn't load the config file. FileNotFound");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		// LOAD DATA
		ConfigurationSection inv = database.getConfigurationSection("inventory");
		if (inv == null) {
			database.set("inventory.createsection", " ");
			try {
				database.save();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
		
		for (String key : inv.getKeys(false)) {
			InventoryItemBean itemBean = new InventoryItemBean();
			itemBean.setName(database.getString("inventory." + key + ".name"));
			itemBean.setRefrigerantType(database.getString("inventory." + key + ".refrigerantType"));
			itemBean.setCheckInWeight(database.getDouble("inventory." + key + ".checkInWeight"));
			itemBean.setReturnWeight(database.getDouble("inventory." + key + ".returnWeight"));
			itemBean.setIdentifierBarcode(key);
			inventoryItems.add(itemBean);
		}
		
		return true;
	}
	
	public static void addInventoryItem(InventoryItemBean item) {
		inventoryItems.add(item);
	}
	
	public static InventoryItemBean getInventoryItem(String identifierBarcode) {
		for (InventoryItemBean item : inventoryItems) {
			if (item.getIdentifierBarcode().equals(identifierBarcode)) {
				return item;
			}
		}
		return null;
	}
	
	public static YamlFile getDatabaseFile() {
		return database;
	}
	
	public static void save() {
		for (InventoryItemBean item : inventoryItems) {
			String key = item.getIdentifierBarcode();
			database.set("inventory." + key + ".name", item.getName());
			database.set("inventory." + key + ".refrigerantType", item.getRefrigerantType());
			database.set("inventory." + key + ".checkInWeight", item.getCheckInWeight());
			database.set("inventory." + key + ".returnWeight", item.getReturnWeight());
		}
		
		try {
			database.save();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 /**
     * Copy a file from source to destination.
     *
     * @param source
     *        the source
     * @param destination
     *        the destination
     * @return True if succeeded , False if not
     */
    public static boolean copy(InputStream source , String destination) {
        boolean success = true;

        System.out.println("Copying ->" + source + "\n\tto ->" + destination);       		
        
        try {
        	 File file = new File(destination);
        	 if (file.exists()) {
        		 // Nothing
        	 } else {
        		 file.mkdirs();
        		 file.createNewFile();
        	 }
            Files.copy(source, Paths.get(destination), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
        	System.out.println("Failed to copy default config file.");
        	ex.printStackTrace();
          success = false;
        }

        return success;

    }
	
}
