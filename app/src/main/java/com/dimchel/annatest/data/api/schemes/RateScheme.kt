package com.dimchel.annatest.data.api.schemes

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "Cube")
class RateScheme(

    @field:Attribute(name = "currency")
    @param:Attribute(name = "currency")
    val currency: String,

    @field:Attribute(name = "rate")
    @param:Attribute(name = "rate")
    val rate: Double

)