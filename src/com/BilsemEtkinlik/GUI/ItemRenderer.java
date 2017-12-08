/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BilsemEtkinlik.GUI;
import com.BilsemEtkinlik.Is.Cift;
import java.awt.Component;
/*from  ww w . j  av  a2 s. c o  m*/
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

/**
 *
 * @author serkancam
 */
class ItemRenderer extends BasicComboBoxRenderer {
  @Override
  public Component getListCellRendererComponent(JList list, Object value,
      int index, boolean isSelected, boolean cellHasFocus) {
    super.getListCellRendererComponent(list, value, index, isSelected,
        cellHasFocus);
    if (value != null) {
      Cift item = (Cift) value;
      setText(item.value.toUpperCase());
    }
    if (index == -1) {
      Cift item = (Cift) value;
      setText("" + item.key);
    }
    return this;
  }
}