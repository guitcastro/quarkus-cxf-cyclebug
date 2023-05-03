package com.simpleps.infrastructure.cxfCycle

import com.simpleps.domain.cxfCycle.Sample
import com.simpleps.domain.cxfCycle.SampleRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class SampleRepositoryImpl : SampleRepository {
    override fun createSample(sample: Sample): Sample {
        return sample.copy(id = "test")
    }
}
