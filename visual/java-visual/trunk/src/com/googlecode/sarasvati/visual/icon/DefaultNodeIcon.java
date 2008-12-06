package com.googlecode.sarasvati.visual.icon;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

import com.googlecode.sarasvati.Node;
import com.googlecode.sarasvati.NodeToken;
import com.googlecode.sarasvati.visual.FontUtil;
import com.googlecode.sarasvati.visual.NodeDrawConfig;

public class DefaultNodeIcon implements Icon
{
  public static final int WIDTH  = 100;
  public static final int HEIGHT = NodeDrawConfig.getMaxNodeRadius() << 1;

  protected Node node;
  protected NodeToken token;

  public DefaultNodeIcon (Node node, NodeToken token)
  {
    this.node = node;
    this.token = token;
  }

  @Override
  public int getIconHeight()
  {
    return HEIGHT;
  }

  @Override
  public int getIconWidth()
  {
    return WIDTH;
  }

  @Override
  public void paintIcon(Component c, Graphics g, int x, int y)
  {
    Graphics2D g2d = (Graphics2D)g;

    g.setColor( NodeDrawConfig.getColor( token ) );
    g.fillOval( x, y, WIDTH - 1, HEIGHT - 1 );

    //g.setColor( node.isStart() ? NodeDrawConfig.START_NODE_BORDER : NodeDrawConfig.NODE_BORDER);
    g.setColor( NodeDrawConfig.NODE_BORDER);

    float[] dashes = node.isJoin() ? new float[] { 10, 5 } : null;

    int offset = 1;

    BasicStroke stroke = new BasicStroke( 3, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER, 10, dashes, 0) ;
    g2d.setStroke( stroke );

    int width = WIDTH - ((offset << 1) + 1);
    int height = HEIGHT - ((offset<< 1) + 1);

    g.drawOval( x + offset, y + offset, width, height);
    offset += 3;

    g.setColor( Color.white );

    int padding = 2 + offset;
    int startX = x + padding;

    int maxWidth = getIconWidth() - (padding << 1);

    String name = node.getName();

    FontUtil.setSizedFont( g, name, 11, maxWidth );
    int strWidth = (int)Math.ceil( g.getFontMetrics().getStringBounds( name, g ).getWidth() );
    int strHeight = g.getFontMetrics().getAscent();
    int left = startX + ((maxWidth - strWidth) >> 1);
    int top = y + ((getIconHeight() + strHeight) >> 1);
    g.drawString( name, left, top );
  }
}