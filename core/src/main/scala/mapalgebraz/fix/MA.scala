package core.fix

import scala.language.higherKinds

import scalaz._

case class Fix[T[_]](unfix: T[Fix[T]])
// case class Cofree[T[_], A](head: A, tail: T[Cofree[T, A]])
// case class Free[F[_], A](resume: A \/ F[Free[F, A]])

/**
 * T type parameter varries over the type of recursion rather than leaf type.
 * - If all we only care about type change at leaves, use Free ?
 */
trait MA[T]{
  def args: Seq[T]
}

final case class Var[T](name: Symbol) extends MA[T] { def args = Nil }
final case class Add[T](args: T*) extends MA[T]
final case class Sub[T](args: T*) extends MA[T]
final case class Mul[T](args: T*) extends MA[T]
final case class Div[T](args: T*) extends MA[T]

// should this ever actually need anything other than Symbol ref?


object FixNDVI {
  val nir: Fix[MA] = Fix[MA](Var('nir))
  val red: Fix[MA] = Fix[MA](Var('red))
  val sum: Fix[MA] = Fix(Add(nir, red))
  val diff: Fix[MA] = Fix(Sub(nir, red))
  val ndvi: Fix[MA] = Fix(Div(diff, sum))
  /* In order to get the type we had to wrap our symbol in MA subclass Var */
}

object CofreeNDVI {
  val nir: Cofree[MA, String] = Cofree[MA, String]("id-nir", Var('nir))
  val red: Cofree[MA, String] = Cofree[MA, String]("id-red", Var('red))
  val sum: Cofree[MA, String] = Cofree[MA, String]("id-sum", Add(nir, red))
  val diff: Cofree[MA, String] = Cofree[MA, String]("id-diff", Sub(nir, red))
  val ndvi: Cofree[MA, String] = Cofree[MA, String]("id-ndvi", Div(diff, sum))
  // That gave us labels for every node, thats useful
}

object FreeNDVI {
  // NDVI with Free
  val nir: Free[MA, Symbol] = Free.pure[MA, Symbol]('nir)
  val red: Free[MA, Symbol] = Free.pure[MA, Symbol]('red)
  val sum: Free[MA, Symbol] = Free(Add(nir, red))
  val diff: Free[MA, Symbol] = Free(Sub(nir, red))
  val ndvi: Free[MA, Symbol] = Free(Div(diff, sum))
  // We didn't have to use Var here
  // ... cool, what do we do with this?


  // Can I mix Free and Cofree to get labels on my DSL nodes ?
}

// TODO: Free with labels => Free with Cofree ?
