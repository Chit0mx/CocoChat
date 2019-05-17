/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

/**
 *
 * @author Alan Franco
 */
public class MenuDesplegable extends AbstractAction{
    private String textoOpcion;

	public MenuDesplegable(String textoOpcion) {
		this.textoOpcion = textoOpcion;
		this.putValue(Action.NAME, textoOpcion);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Pulsado " + textoOpcion);
	}
}
