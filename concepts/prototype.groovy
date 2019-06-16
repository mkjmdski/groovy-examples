// Prototype-based programming is a style of object-oriented programming in which behaviour reuse (known as inheritance) is performed via a process of reusing existing objects via delegation that serve as prototypes. This model can also be known as prototypal, prototype-oriented, classless, or instance-based programming
Number.metaClass {
  sqrt = {
    return Math.sqrt(delegate)
  }
  pow = {
    // in groovy if return statement is not specified, then last assignment is returned
    delegate * delegate
  }
}

assert 9.sqrt() == 3
assert 4.sqrt() == 2
println("9.sqrt(): ${9.sqrt()}")
println("9.pow(): ${9.pow()}")
assert 9.pow() == 81
enum Color {
  BLACK('#000000'), WHITE('#FFFFFF'), RED('#FF0000'), BLUE('#0000FF')
  String hex
  Color(String hex) {
    this.hex = hex
  }
}

String.metaClass.getProperty = { String property ->
  def stringColor = delegate
  if (property == 'hex') {
    Color.values().find { it.name().equalsIgnoreCase stringColor }?.hex
  }
}

colors = [
  "WHITE", "BLUE", "BLACK", "GREEN"
]
assert "WHITE".hex == "#FFFFFF"
assert "BLUE".hex == "#0000FF"
assert "BLACK".hex == "#000000"
assert "GREEN".hex == null

println(colors)
println(colors.collect{it.hex})
return colors.collectEntries{[
  "${it}": it.hex
]}.findAll{
  it.value
}