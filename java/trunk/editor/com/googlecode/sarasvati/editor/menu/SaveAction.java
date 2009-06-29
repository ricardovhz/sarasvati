/*
    This file is part of Sarasvati.

    Sarasvati is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    Sarasvati is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with Sarasvati.  If not, see <http://www.gnu.org/licenses/>.

    Copyright 2008 Paul Lorenz
*/
package com.googlecode.sarasvati.editor.menu;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.googlecode.sarasvati.editor.GraphEditor;
import com.googlecode.sarasvati.editor.model.EditorGraph;
import com.googlecode.sarasvati.editor.model.EditorScene;

public class SaveAction extends AbstractAction
{
  private static final long serialVersionUID = 1L;

  private final boolean isSaveAs;

  public SaveAction (boolean isSaveAs)
  {
    super( isSaveAs ? "Save As.." : "Save" );
    this.isSaveAs = isSaveAs;

    if ( !isSaveAs )
    {
      putValue( Action.ACCELERATOR_KEY, KeyStroke.getKeyStroke( KeyEvent.VK_S, KeyEvent.CTRL_MASK ) );
      putValue( Action.MNEMONIC_KEY, KeyEvent.VK_S );
    }
  }

  @Override
  public void actionPerformed (ActionEvent e)
  {
    EditorScene scene = GraphEditor.getInstance().getCurrentScene();

    if ( scene == null )
    {
      return;
    }

    EditorGraph graph = scene.getGraph();

    List<String> errors = graph.validateGraph();

    if ( !errors.isEmpty() )
    {
      StringBuilder buf = new StringBuilder ();
      for ( String error : errors )
      {
        buf.append( error );
        buf.append( "\n" );
      }

      JOptionPane.showMessageDialog( GraphEditor.getInstance().getMainWindow(),
                                     buf.toString(),
                                     "Invalid Process Definition",
                                     JOptionPane.ERROR_MESSAGE );
      return;
    }

    GraphEditor editor = GraphEditor.getInstance();

    if ( isSaveAs || scene.getGraph().getFile() == null )
    {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory( editor.getLastFile() );

      int retVal = fileChooser.showSaveDialog( GraphEditor.getInstance().getMainWindow() );

      if ( retVal == JFileChooser.APPROVE_OPTION )
      {
        editor.setLastFile( fileChooser.getSelectedFile() );
        editor.saveProcessDefinition( graph, fileChooser.getSelectedFile() );
      }
      else
      {
        return;
      }
    }

    editor.saveProcessDefinition( graph, graph.getFile() );
  }
}