package de.goldkugel.etl.atom;

import java.io.Serializable;

import de.goldkugel.etl.atom.global.Messages;
import de.goldkugel.etl.atom.global.Values;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * This is a basic class to deal with tabular data in an etl process.
 * 
 * Every object of this class represents a cell in a table containing
 * some data. Column headers are stored in "AtomType"s and should not
 * be converted into objects of this class. Please note, that every
 * combination (type, index) should be unique to ensure further
 * processing. If some problems will be encountered because of
 * ignoring this hint it might not be able to be detected easily.
 * Moreover, to save time please ensure to use always the same set of
 * type objects. To deal with any inconvenient, this class can be
 * serialised in a file if some error and / or warnings occur or even
 * for testing.
 * 
 * @author Peter Pallaoro
 *
 */
public class Atom implements Serializable {

  /**
   * The serivalVersionUID to serialise the objects of this class.
   */
  private static final long serialVersionUID = 1973437748746859444L;

  /**
   * The type of the atom. Usually, in a tabular data structure the type
   * is already known while reading. Types can not change over time but
   * one atom can be splitted into more atoms if necessary. If an atom
   * needs to be splitted up into multiple atoms, this can be realized
   * by instantiating more atoms having the same index as this one but
   * different types. Instantiated atoms which are not explicitly stored
   * will not be saved. This should never be null.
   */
  protected final AtomType TYPE;

  /**
   * The data which this atom holds. The content of an atom can change
   * over time, since sometimes it is necessary to delete content parts
   * for example for data privacy reasons. No history of content is
   * provided.
   */
  @Getter
  @Setter
  protected String content;

  /**
   * The index of the line the atom belongs to. To avoid any index
   * problems while creating the atoms, the first row should always have
   * the line number zero. This should never be null.
   */
  protected final Long INDEX;

  /**
   * Sets the type and the index of the atom. These two parameters can
   * not be changed after initialisation and need to be non-null.
   * 
   * @param type: the AtomType. Not null.
   * @param index: the index of the line of the atom. To avoid any index
   *        problems while creating the atoms, the first row should
   *        always have the line number zero.
   */
  public Atom(@NonNull AtomType type, @NonNull Long index) {
    this.TYPE = type;
    this.INDEX = index;
  }

  /**
   * Returns the index of the line of the atom.
   * 
   * @return the index of the line of the atom.
   */
  public Long getIndex() {
    return this.INDEX;
  }

  /**
   * Returns the AtomType of the atom.
   * 
   * @return the AtomType of the atom.
   */
  public AtomType getType() {
    return this.TYPE;
  }

  /**
   * Returns the atom as a string.
   * 
   * @return a string containing every information about the atom.
   */
  public String toString() {
    String ret = Messages.ATOM_TO_STRING
        .replace(Messages.INDEX_SPACEHOLDER, this.INDEX.toString())
        .replace(Messages.TYPE_SPACEHOLDER, this.TYPE.toString());

    if (this.getContent() == Values.NULL) {
      ret.replace(Messages.CONTENT_SPACEHOLDER, Values.NULL_STRING);
    } else {
      ret.replace(Messages.CONTENT_SPACEHOLDER,
          Messages.quote(this.getContent()));
    }

    return ret;
  }

  /**
   * Checks if this object is equal to a given object. If the given
   * object is not an Atom the return value is false. Only if type,
   * index, and content is equal to each other, the return value is
   * true. The checks are being performed in exactly this order.
   * 
   * @param type: the Atom which equality with this object is being
   *        checked.
   * @return true if both objects have the same type, index, and
   *         content, false if not.
   */
  public boolean equals(Object atom) {
    boolean ret = Values.BOOLEAN_FALSE;

    if (atom != Values.NULL && atom instanceof Atom) {

      Atom a = (Atom) atom;

      if (a.getType().equals(this.getType())
          && a.getIndex().equals(this.getIndex())) {
        if (this.getContent() != Values.NULL && a.getContent() != Values.NULL) {
          ret = this.getContent().equals(a.getContent());
        }
      }

    }

    return ret;
  }

  /**
   * Compares an object to this. If the given object is an Atom, the
   * first thing that is checked is the index, afterwards the type, and
   * than the content. The value of the first comparison which does not
   * return zero will be returned. The used methods for comparison are
   * AtomType.equals(Object type), Integer.compareTo(Integer), and
   * String.compareTo(String).
   * 
   * @param type: the other AtomType to compare this one to.
   * @return zero if the given object was not an AtomType. If the given
   *         object was an AtomType the return value is the same as
   *         atomtype.compareTo(type).
   */
  public Integer compareTo(Object atom) {
    Integer ret = Values.DECIMAL_ZERO;

    if (atom != Values.NULL && atom instanceof Atom) {

      Atom a = (Atom) atom;
      ret = Math.round(Math.signum(this.getIndex() - a.getIndex()));

      if (ret == Values.DECIMAL_ZERO) {
        ret = this.getType().compareTo(a.getType());

        if (ret == Values.DECIMAL_ZERO) {
          if (this.getContent() != Values.NULL
              && a.getContent() != Values.NULL) {
            ret = this.getContent().compareTo(a.getContent());
          } else {
            if (this.getContent() == Values.NULL
                && a.getContent() == Values.NULL) {
              ret = Values.DECIMAL_ZERO;
            }
          }
        }
      }
    }

    return ret;
  }
}
