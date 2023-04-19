package de.goldkugel.etl.atom.global;

import lombok.NonNull;

/**
 * Here every message regarding warnings, errors, and information is
 * stored. Moreover some helpful methods are also implemented for
 * displaying text.
 *
 * @author Peter Pallaoro
 *
 */
public final class Messages {

  /**
   * Placeholder in output for some hash value.
   */
  public static final String NAME_SPACEHOLDER = "[NAME]";

  /**
   * Placeholder in output for some hash value.
   */
  public static final String INDEX_SPACEHOLDER = "[INDEX]";

  /**
   * Placeholder in output for some hash value.
   */
  public static final String TYPE_SPACEHOLDER = "[TYPE]";

  /**
   * Placeholder in output for some hash value.
   */
  public static final String CONTENT_SPACEHOLDER = "[CONTENT]";

  /**
   * Text used in the toString method of the Atom. It is possible to add
   * the index, the type, and the content to the string by replacing the
   * spaceholders.
   */
  public static final String ATOM_TYPE_TO_STRING =
      "AtomType (Name = ".concat(NAME_SPACEHOLDER).concat(")");

  /**
   * Text used in the toString method of the Atom. It is possible to add
   * the index, the type, and the content to the string by replacing the
   * spaceholders.
   */
  public static final String ATOM_TO_STRING = "ATOM (Index = "
      .concat(INDEX_SPACEHOLDER).concat(", Type = ").concat(TYPE_SPACEHOLDER)
      .concat(", Content = ").concat(CONTENT_SPACEHOLDER).concat(")");

  /**
   * Adds the " quote at the beginning and the end of the given string.
   * 
   * @param s: the string which shall be quoted.
   * @return the quoted string.
   */
  public static String quote(@NonNull String s) {
    return "\"".concat(s).concat("\"");
  }
}
