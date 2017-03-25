package core.func

trait MA[T]

final case class Var[T](value: T) extends MA[T] { def args = Nil }
final case class Add[T](args: MA[T]*) extends MA[T]
final case class Sub[T](args: MA[T]*) extends MA[T]
final case class Mul[T](args: MA[T]*) extends MA[T]
final case class Div[T](args: MA[T]*) extends MA[T]

/*
Because we recurse the ADT on MA[T] we don't have a way to associate each node with a label.
T is only going to be the type of the leaf.
Optional labels per level is a hard requirement, so this formulation does not play.
*/

object MA {
  val nir = Var('nir)
  val red = Var('red)

  val ndvi: Div[Symbol] = Div(
    Sub(red, nir),
    Add(red, nir))
}
