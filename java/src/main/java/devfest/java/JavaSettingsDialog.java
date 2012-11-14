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

package devfest.java;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import devfest.core.SettingsDialog;

/**
 * Native Swing implementation of the settings dialog.
 */
public class JavaSettingsDialog implements SettingsDialog {

  @Override
  public void open(final Listener listener) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        final JDialog dialog = new JDialog(new JFrame(), TITLE, true);
        dialog.setSize(50, 100);
        dialog.setLocationRelativeTo(null);
        final JComboBox comboBox = new JComboBox(new String[] {
            "1", "2", "3", "4"
        });
        dialog.getContentPane().add(comboBox);
        JButton ok = new JButton("Ok");
        dialog.getContentPane().add(ok, "South");
        ok.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(final ActionEvent evt) {
            listener.onSettingsDialogClosed(new Settings(Integer.valueOf((String) comboBox.getSelectedItem())));
            dialog.setVisible(false);
          }
        });
        dialog.setVisible(true);
      }
    });
  }

}
