package de.goldkugel.etl.atom;

import java.io.Serializable;

import de.goldkugel.etl.atom.global.Messages;
import de.goldkugel.etl.atom.global.Values;
import lombok.NonNull;

/**
 * This class denotes a type of an atom for example column names of a
 * csv file.
 * 
 * The only attributes this class has, is the name. The only purpose
 * of an object of this class is to be able to be reused instead of
 * using multiple identifiers. Similar to a general column, the type
 * has a name and it should be unique to avoid ambiguity. Please use
 * the exact same object for the same kind of atoms. Objects of this
 * class can also be serialised.
 * 
 * @author Peter Pallaoro
 *
 */
public class AtomType implements Serializable {

  /**
   * The serialVersionUID for serialising objects in files.
   */
  private static final long serialVersionUID = -6808427998371142235L;

  /**
   * The name of the atom type. This should never be null.
   */
  protected final String NAME;

  /**
   * Constructor to create types in an easy way. There is no check if
   * the values or combination of values are unique but both, the name
   * and the index, are required. Moreover, it is not needed that the
   * index needs to be a positive or non-negative number, any integer
   * value is accepted.
   * 
   * @param name: the name of the type.
   */
  public AtomType(@NonNull String name) {
    this.NAME = name;
  }

  /**
   * The toString Method of this class.
   * 
   * @return a string containing all information about the object.
   */
  public String toString() {
    String ret = Messages.ATOM_TYPE_TO_STRING.replace(Messages.NAME_SPACEHOLDER,
        Messages.quote(this.getName()));

    return ret;
  }

  /**
   * The equals method of this class. Two types are equal if they have
   * the same name. The equals method of strings is used to check if the
   * atom types are the same.
   * 
   * @param type: the other type to compare this one to.
   * @return true if both types are equal, false if not.
   */
  public boolean equals(Object type) {
    boolean ret = Values.BOOLEAN_FALSE;

    if (type != Values.NULL && type instanceof AtomType) {

      AtomType t = (AtomType) type;

      ret = this.getName().equals(t.getName());

    }

    return ret;
  }

  /**
   * Compares an object to this. If the given object is an AtomType, the
   * return value is the same as the return value of the
   * NAME.compareTo(type.NAME) method implemented in the class string.
   * 
   * @param type: the other AtomType to compare this one to.
   * @return zero if the given object was not an AtomType. If the given
   *         object was an AtomType the return value is the same as
   *         atomtype.compareTo(type).
   */
  public Integer compareTo(Object type) {
    Integer ret = Values.DECIMAL_ZERO;

    if (type != Values.NULL && type instanceof AtomType) {

      AtomType t = (AtomType) type;

      ret = this.getName().compareTo(t.getName());

    }

    return ret;
  }

  /**
   * This method returns the atom type's name.
   * 
   * @return the name of the atom type.
   */
  public String getName() {
    return this.NAME;
  }
}
