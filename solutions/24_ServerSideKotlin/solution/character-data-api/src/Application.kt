package com.bignerdranch

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    routing {
        get("/") {
            call.respondText(
                Character().toString(),
                contentType = ContentType.Text.Plain
            )
        }

        get("/html-dsl") {
            call.respondHtml {
                body {
                    h1 { +"Characters" }
                    ul {
                        for (n in 1..10) {
                            li { +"Character $n: ${Character()}" }
                        }
                    }
                }
            }
        }
    }
}
