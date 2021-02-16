package example.model

import squants.{Dimension, Quantity}

case class Vec2[A <: Quantity[A]](x: A, y: A) {

  def +(other: Vec2[A]): Vec2[A] = Vec2(x + other.x, y + other.y)
  def -(other: Vec2[A]): Vec2[A] = Vec2(x - other.x, y - other.y)

  def map2[B <: Quantity[B]](funA: A => B, funB: A => B): Vec2[B] = Vec2(funA(x), funB(y))
  def map[B <: Quantity[B]](fun: A => B): Vec2[B] = Vec2(fun(x), fun(y))

}
