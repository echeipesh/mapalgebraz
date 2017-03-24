package core.func


trait MA[T]

final case class Var[T](value: T) extends MA[T] { def args = Nil }
final case class Add[T](args: MA[T]*) extends MA[T]
final case class Sub[T](args: MA[T]*) extends MA[T]
final case class Mul[T](args: MA[T]*) extends MA[T]
final case class Div[T](args: MA[T]*) extends MA[T]


object MA {
  val nir = Var('nir)
  val red = Var('red)

  val ndvi: Div[Symbol] = Div(
    Sub(red, nir),
    Add(red, nir))
}
