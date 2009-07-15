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

    Copyright 2009 Paul Lorenz
 */
package com.googlecode.sarasvati.editor.model;

import java.util.LinkedList;
import java.util.List;

public class EditorNodeType
{
  private final String name;
  private final boolean allowNonSpecifiedAttributes;
  private final List<EditorNodeTypeAttribute> attributes;

  public EditorNodeType (final String name,
                         final boolean allowNonSpecifiedAttributes)
  {
    this.name = name;
    this.allowNonSpecifiedAttributes = allowNonSpecifiedAttributes;
    this.attributes = new LinkedList<EditorNodeTypeAttribute>();
  }

  public EditorNodeType (final String name,
                         final boolean allowNonSpecifiedAttributes,
                         final List<EditorNodeTypeAttribute> attributes)
  {
    this.name = name;
    this.allowNonSpecifiedAttributes = allowNonSpecifiedAttributes;
    this.attributes = attributes;
  }

  /**
   * @return the name
   */
  public String getName ()
  {
    return name;
  }

  /**
   * @return the allowNonSpecifiedAttributes
   */
  public boolean isAllowNonSpecifiedAttributes ()
  {
    return allowNonSpecifiedAttributes;
  }

  /**
   * @return the attributes
   */
  public List<EditorNodeTypeAttribute> getAttributes ()
  {
    return attributes;
  }

  @Override
  public String toString ()
  {
    return name;
  }

  public EditorNodeType copy ()
  {
    List<EditorNodeTypeAttribute> attributesCopy = new LinkedList<EditorNodeTypeAttribute>();
    for ( EditorNodeTypeAttribute attr : attributes )
    {
      attributesCopy.add( attr.copy() );
    }
    return new EditorNodeType( name, allowNonSpecifiedAttributes, attributesCopy );
  }
}