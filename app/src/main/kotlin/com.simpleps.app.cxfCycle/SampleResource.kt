package com.simpleps.app.cxfCycle

import com.simpleps.domain.cxfCycle.Sample
import com.simpleps.domain.cxfCycle.SampleRepository
import jakarta.transaction.Transactional
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/sample")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class SampleResource(
    private val sampleRepository: SampleRepository
) {
    @POST
    @Transactional
    fun createSample(): Sample {
        return sampleRepository.createSample(Sample())
    }
}
