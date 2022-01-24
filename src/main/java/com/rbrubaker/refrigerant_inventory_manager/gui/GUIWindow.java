package com.rbrubaker.refrigerant_inventory_manager.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.rbrubaker.refrigerant_inventory_manager.database.DatabaseManager;
import com.rbrubaker.refrigerant_inventory_manager.database.InventoryItemBean;

import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Font;

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
public class GUIWindow {

	public JFrame frame;
	private JTextField itemNameTxtField;
	private JTextField refrigerantTypeTxtField;
	private JTextField checkInWeightTxtField;
	private JTextField returnWeightTxtField;
	private JTextField idenfiferBarcodeTxtField;
	private JButton loadButton;
	private JButton saveButton;

	/**
	 * Create the application.
	 */
	public GUIWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 669, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JPanel identifierBarcodePanel = new JPanel();
		mainPanel.add(identifierBarcodePanel);
		
		JLabel identiferBarcodeLabel = new JLabel("ID Barcode:");
		identiferBarcodeLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		identifierBarcodePanel.add(identiferBarcodeLabel);
		
		idenfiferBarcodeTxtField = new JTextField();
		idenfiferBarcodeTxtField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		idenfiferBarcodeTxtField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				idenfiferBarcodeTxtField.selectAll();
			}
		});
		idenfiferBarcodeTxtField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadButton.doClick();
			}
		});
		identifierBarcodePanel.add(idenfiferBarcodeTxtField);
		idenfiferBarcodeTxtField.setColumns(20);
		
		JPanel itemNamePanel = new JPanel();
		mainPanel.add(itemNamePanel);
		
		JLabel itemNameLabel = new JLabel("Item Name:");
		itemNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		itemNamePanel.add(itemNameLabel);
		
		itemNameTxtField = new JTextField();
		itemNameTxtField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		itemNameTxtField.setColumns(20);
		itemNamePanel.add(itemNameTxtField);
		
		JPanel refrigerantTypePanel = new JPanel();
		mainPanel.add(refrigerantTypePanel);
		
		JLabel refrigerantTypeLabel = new JLabel("Refrigerant Type:");
		refrigerantTypeLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		refrigerantTypePanel.add(refrigerantTypeLabel);
		
		refrigerantTypeTxtField = new JTextField();
		refrigerantTypeTxtField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		refrigerantTypePanel.add(refrigerantTypeTxtField);
		refrigerantTypeTxtField.setColumns(20);
		
		JPanel checkInWeightPanel = new JPanel();
		mainPanel.add(checkInWeightPanel);
		
		JLabel checkInWeightLabel = new JLabel("Check-In Weight");
		checkInWeightLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		checkInWeightPanel.add(checkInWeightLabel);
		
		checkInWeightTxtField = new JTextField();
		checkInWeightTxtField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		checkInWeightPanel.add(checkInWeightTxtField);
		checkInWeightTxtField.setColumns(20);
		
		JPanel returnWeightPanel = new JPanel();
		mainPanel.add(returnWeightPanel);
		
		JLabel returnWeightLabel = new JLabel("Return Weight");
		returnWeightLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		returnWeightPanel.add(returnWeightLabel);
		
		returnWeightTxtField = new JTextField();
		returnWeightTxtField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		returnWeightPanel.add(returnWeightTxtField);
		returnWeightTxtField.setColumns(20);
		
		JPanel loadSaveButtonPanel = new JPanel();
		mainPanel.add(loadSaveButtonPanel);
		
		loadButton = new JButton("Load");
		loadButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		loadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idenfiferBarcodeTxtField.getText();
				itemNameTxtField.requestFocusInWindow();
				idenfiferBarcodeTxtField.requestFocusInWindow();
				
				InventoryItemBean item = DatabaseManager.getInventoryItem(id);
				if (item == null) {
					idenfiferBarcodeTxtField.setBackground(Color.RED);
					return;
				} else {
					idenfiferBarcodeTxtField.setBackground(Color.WHITE);
				}
				
				itemNameTxtField.setText(item.getName());
				refrigerantTypeTxtField.setText(item.getRefrigerantType());
				checkInWeightTxtField.setText(String.valueOf(item.getCheckInWeight()));
				returnWeightTxtField.setText(String.valueOf(item.getReturnWeight()));				
			}
		});
		loadSaveButtonPanel.add(loadButton);
		
		saveButton = new JButton("Save");
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = idenfiferBarcodeTxtField.getText();
				
				InventoryItemBean item = DatabaseManager.getInventoryItem(id);
				if (item == null) {
					item = new InventoryItemBean();
					
					item.setIdentifierBarcode(id);
					item.setName(itemNameTxtField.getText());
					item.setRefrigerantType(refrigerantTypeTxtField.getText());
					String checkInWeight = checkInWeightTxtField.getText();
					String returnWeight = returnWeightTxtField.getText();
					
					double checkInWeightD = -1.0;
					double returnWeightD = -1.0;
					
					try {
						checkInWeightD = Double.parseDouble(checkInWeight);						
					} catch (NumberFormatException ex) {
						checkInWeightD = -1.0;						
					}
					
					try {
						returnWeightD = Double.parseDouble(returnWeight);					
					} catch (NumberFormatException ex) {
						returnWeightD = -1.0;						
					}					
					
					item.setCheckInWeight(checkInWeightD);
					item.setReturnWeight(returnWeightD);
					DatabaseManager.addInventoryItem(item);
					DatabaseManager.save();
				} else {
					item.setName(itemNameTxtField.getText());
					item.setRefrigerantType(refrigerantTypeTxtField.getText());
					String checkInWeight = checkInWeightTxtField.getText();
					String returnWeight = returnWeightTxtField.getText();
					
					double checkInWeightD = 0.0;
					double returnWeightD = 0.0;
					
					try {
						checkInWeightD = Double.parseDouble(checkInWeight);
						returnWeightD = Double.parseDouble(returnWeight);
					} catch (NumberFormatException ex) {
						ex.printStackTrace();
						return;
					}
					
					item.setCheckInWeight(checkInWeightD);
					item.setReturnWeight(returnWeightD);
					
					DatabaseManager.save();
				}				
			}
		});
		loadSaveButtonPanel.add(saveButton);
	}

}
