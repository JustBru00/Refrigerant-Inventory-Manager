package com.rbrubaker.refrigerant_inventory_manager;

import java.awt.EventQueue;

import com.rbrubaker.refrigerant_inventory_manager.database.DatabaseManager;
import com.rbrubaker.refrigerant_inventory_manager.gui.GUIWindow;

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
public class RefrigerantInventoryManagerMain {
	public static void main(String[] args) {
		System.out.println("Refrigerant-Inventory-Manager is a simple inventory management program.\r\n"
				+ "  Copyright (C) 2022 Rufus Brubaker Refrigeration. Created by Justin Brubaker.\r\n"
				+ "\r\n"
				+ "   This program is free software: you can redistribute it and/or modify\r\n"
				+ "   it under the terms of the GNU General Public License as published by\r\n"
				+ "   the Free Software Foundation, either version 3 of the License, or\r\n"
				+ "   (at your option) any later version.\r\n"
				+ "\r\n"
				+ "   This program is distributed in the hope that it will be useful,\r\n"
				+ "   but WITHOUT ANY WARRANTY; without even the implied warranty of\r\n"
				+ "   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the\r\n"
				+ "   GNU General Public License for more details.\r\n"
				+ "\r\n"
				+ "   You should have received a copy of the GNU General Public License\r\n"
				+ "   along with this program.  If not, see <https://www.gnu.org/licenses/>.\r\n"
				+ "\r\n"
				+ "   You can contact us at rbr@rbrubaker.com.\r\n"
				+ "   https://github.com/JustBru00/Refrigerant-Inventory-Manager");
		
		DatabaseManager.loadDatabase();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIWindow window = new GUIWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
