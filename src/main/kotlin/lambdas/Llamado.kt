package org.javakotlin.lambdas

import lam.Lamb

class Llamado {
  fun invocacion() {

    val oneListener: OnOneListener = object : OnOneListener {
      override fun onOne(message: String) {
        println("Sin lambda con" + message)
      }
    }
    val oneListener2: OnOneListener =
      OnOneListener { message: String -> println("Usando lambdas con $message") }

    Lamb.me()
    oneListener.onOne(" Kotlin")
    oneListener2.onOne(" Kotlin")
  }
}
