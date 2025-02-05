package com.example.composesignup.core.utils

class TextFieldException:Exception{
    constructor():super()
    constructor(message:String):super()
    constructor(message: String,cause:Throwable):super()
    constructor(cause: Throwable):super()

    override fun equals(other: Any?): Boolean {
        return other is TextFieldException && other.message == this.message
    }

    override fun hashCode(): Int {
        return message.hashCode()
    }

}