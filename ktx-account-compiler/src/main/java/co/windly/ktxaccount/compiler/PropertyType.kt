package co.windly.ktxaccount.compiler

import java.util.Arrays
import javax.lang.model.type.TypeMirror

enum class PropertyType constructor(

  //region Qualified Name

  val qualifiedName: String,

  //endregion

  //region Simple Name

  val simpleName: String,

  //endregion

  //region Method Name

  val methodName: String,

  //endregion

  //region Default Value

  val defaultValue: String

  //endregion

) {

  //region Supported Data Types

  BOOLEAN(
    qualifiedName = Boolean::class.java.name,
    simpleName = "Boolean",
    methodName = "Boolean",
    defaultValue = "false"
  ),

  DOUBLE(
    qualifiedName = Double::class.java.name,
    simpleName = "Double",
    methodName = "Double",
    defaultValue = "0.0"
  ),

  FLOAT(
    qualifiedName = Float::class.java.name,
    simpleName = "Float",
    methodName = "Float",
    defaultValue = "0.0f"
  ),

  INTEGER(
    qualifiedName = Int::class.java.name,
    simpleName = "Int",
    methodName = "Int",
    defaultValue = "0"
  ),

  LONG(
    qualifiedName = Long::class.java.name,
    simpleName = "Long",
    methodName = "Long",
    defaultValue = "0L"
  ),

  STRING(
    qualifiedName = String::class.java.name,
    simpleName = "String",
    methodName = "String",
    defaultValue = "\"\""
  );

  //endregion

  //region Compatibility

  fun isCompatible(type: TypeMirror): Boolean =
    qualifiedName == type.toString()

  //endregion

  //region Companion

  companion object {

    //region From

    fun from(fieldType: TypeMirror): PropertyType {

      // Retrieve qualified name.
      val qualifiedName = fieldType.toString()

      // Retrieve type for given qualified type.
      val type = Arrays
        .stream(values())
        .filter { it.qualifiedName == qualifiedName }
        .findFirst()

      // Return type for given type.
      if (type.isPresent) {
        return type.get()
      }

      // Throw an error in case if qualified type is not supported.
      throw IllegalArgumentException("Unsupported type: $qualifiedName.")
    }

    //endregion

    //region Allowed Type

    fun isAllowedType(fieldType: TypeMirror): Boolean {

      // Retrieve qualified name.
      val qualifiedName = fieldType.toString()

      // Return an information whether given type is supported.
      return Arrays
        .stream(values())
        .anyMatch { it.qualifiedName == qualifiedName }
    }

    //endregion
  }

  //endregion
}
