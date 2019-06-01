package com.dimchel.annatest.data.api.schemes

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "Envelope", strict = false)
class EuroRatesResponseScheme(

    @field:Element(name = "Cube")
    @param:Element(name = "Cube")
    var cube: RatesListScheme

)