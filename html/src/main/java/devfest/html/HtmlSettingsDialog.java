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

package devfest.html;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import devfest.core.SettingsDialog;

/**
 * Native GWT implementation of the settings dialog.
 */
public class HtmlSettingsDialog implements SettingsDialog {

  @Override
  public void open(final Listener listener) {
    final ListBox listBox = new ListBox(false);
    listBox.addItem("1");
    listBox.addItem("2");
    listBox.addItem("3");
    listBox.addItem("4");

    Label label = new Label(TITLE);

    Button button = new Button("OK");

    VerticalPanel panel = new VerticalPanel();
    panel.add(label);
    panel.add(listBox);
    panel.add(button);

    final PopupPanel popup = new PopupPanel(false, true);
    popup.add(panel);
    popup.center();

    button.addClickHandler(new ClickHandler() {
      @Override
      public void onClick(final ClickEvent event) {
        listener.onSettingsDialogClosed(new Settings(
            Integer.valueOf(listBox.getItemText(listBox.getSelectedIndex()))));
        popup.hide();
      }
    });
  }

}
