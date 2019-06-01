package com.dimchel.annatest.data.api.schemes

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "Cube")
class RatesListScheme(

    @field:ElementList(name = "Cube")
    @param:ElementList(name = "Cube")
    var cubes: List<RateScheme>

)
